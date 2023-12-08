package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uel.bd.Bulbapedia.DAO.*;
import uel.bd.Bulbapedia.models.*;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.util.*;


@CrossOrigin(origins = "http://localhost:3000") //serve para O react se comunicar corretamente com o Spring
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    private PokemonJdbcDAO pokemonJdbcDAO;


    @PostMapping("/populate")
    public void populatePokemon() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon");

            ArrayList<Integer> previous_form = new ArrayList<Integer>();

            while(true) {
                for(Object o : (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    JSONObject pokemon = APIRequests.getAPIResponse((String) result.get(("url")));
                    if(pokemon == null) continue;

                    int pokemon_id = ((Long) pokemon.get("id")).intValue();

                    JSONObject pokemon_species = APIRequests.getAPIResponse(
                            String.format("https://pokeapi.co/api/v2/pokemon-species/" + pokemon_id)
                    );
                    if(pokemon_species == null) { break; }

                    char rarity;

                    if((boolean) pokemon_species.get("is_legendary")) {
                        rarity = 'L';
                    } else if((boolean) pokemon_species.get("is_mythical")) {
                        rarity = 'M';
                    } else {
                        rarity = 'N';
                    }

                    JSONObject sprites = (JSONObject) pokemon.get("sprites");

                    JSONObject generation = (JSONObject) pokemon_species.get("generation");

                    Object evolves_from = pokemon_species.get("evolves_from_species");
                    if(evolves_from != null) {
                        evolves_from = ((String) ((JSONObject) evolves_from).get("url"));
                    }

                    pokemonJdbcDAO.create(new Pokemon(
                            pokemon_id,
                            (String) pokemon.get("name"),
                            ((Long) pokemon.get("height")).intValue(),
                            ((Long) pokemon.get("weight")).intValue(),
                            ((Long) pokemon_species.get("capture_rate")).intValue(),
                            rarity,
                            (String) sprites.get("front_default"),
                            APIRequests.getIDFromURL((String) generation.get("url"))
                    ));

                    previous_form.add(evolves_from == null ? null : APIRequests.getIDFromURL((String) evolves_from));
                }

                if(generalInfo.get("next") == null) {
                    break;
                } else {
                    generalInfo = APIRequests.getAPIResponse((String) generalInfo.get("next"));
                }
            }

            for(int i = 0; i < previous_form.size(); i++) {
                if(previous_form.get(i) != null) {
                    pokemonJdbcDAO.update(previous_form.get(i), i + 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<Pokemon> getAllPokemon() {
        return pokemonJdbcDAO.getAll();
    }

    @GetMapping("/{id}")
    public JSONObject getPokemon(@PathVariable int id) {
        List<Map<String, Object>> pokemon_full_data = null;
        try {
            pokemon_full_data = pokemonJdbcDAO.getPokemonGeneralData(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Set<List<String>> abilities_data = new HashSet<List<String>>();
        Set<List<String>> tipos_data = new HashSet<List<String>>();

        for(Map<String, Object> pokemon: pokemon_full_data) {
            List<String> lst_ability = new ArrayList<String>();
            List<String> lst_type = new ArrayList<String>();

            lst_ability.add((String) pokemon.get("ability_name"));
            lst_ability.add(String.valueOf((int) pokemon.get("is_hidden")));

            lst_type.add((String) pokemon.get("type_name"));
            lst_type.add(String.valueOf((int) pokemon.get("slot")));

            abilities_data.add(lst_ability);
            tipos_data.add(lst_type);
        }

        Map<String, Object> pokemon_main_data = pokemon_full_data.get(0);
        JSONObject pokemon_data = new JSONObject();
        // Informação da geração
        pokemon_data.put("generation", pokemon_main_data.get("id_generation"));
        pokemon_data.put("evolves_from", pokemon_main_data.get("evolves_from"));
        // Informação das habilidades
        JSONArray abilities = new JSONArray();
        for(List<String> ability_info: abilities_data) {
            JSONObject ability = new JSONObject();

            ability.put("hidden", Integer.parseInt(ability_info.get(1)) == 1);
            ability.put("name", ability_info.get(0));

            abilities.add(ability);
        }

        pokemon_data.put("abilities", abilities);

        // Informação dos tipos
        JSONArray types = new JSONArray();
        for(List<String> tipos_info: tipos_data) {
            JSONObject type = new JSONObject();

            type.put("slot", Integer.parseInt(tipos_info.get(1)));
            type.put("name", tipos_info.get(0));

            types.add(type);
        }

        pokemon_data.put("types", types);

        // Informações o Pokemon GO
        JSONObject pokemon_go_data = new JSONObject();

        // -- Pokemon GO stats
        JSONObject go_stats = new JSONObject();

        go_stats.put("defense", pokemon_main_data.get("go_defense"));
        go_stats.put("attack", pokemon_main_data.get("go_attack"));
        go_stats.put("stamina", pokemon_main_data.get("go_stamina"));

        pokemon_go_data.put("stats", go_stats);
        pokemon_go_data.put("max_cp", pokemon_main_data.get("max_cp"));
        pokemon_go_data.put("candy_to_evolve", pokemon_main_data.get("candy_to_evolve"));

        // -- Pokemon GO shiny data
        JSONObject shiny = new JSONObject();

        shiny.put("egg", (int) pokemon_main_data.get("egg") == 1);
        shiny.put("wild", (int) pokemon_main_data.get("wild") == 1);
        shiny.put("raid", (int) pokemon_main_data.get("raid") == 1);
        shiny.put("sprite", pokemon_main_data.get("sprite_shiny"));

        pokemon_go_data.put("shiny", shiny);
        pokemon_go_data.put("raid_exclusive", (int) pokemon_main_data.get("raid_exclusive") == 1);
        pokemon_go_data.put("buddy_distance", pokemon_main_data.get("buddy_distance"));

        pokemon_data.put("pokemon_go_info", pokemon_go_data);
        pokemon_data.put("name", pokemon_main_data.get("name"));
        pokemon_data.put("sprite", pokemon_main_data.get("sprite"));
        pokemon_data.put("weight", pokemon_main_data.get("weight"));
        pokemon_data.put("id", pokemon_main_data.get("id_pokedex"));
        pokemon_data.put("capture_rate", pokemon_main_data.get("capture_rate"));
        pokemon_data.put("height", pokemon_main_data.get("height"));
        pokemon_data.put("rarity", pokemon_main_data.get("rarity"));

        // Pokemon Stats
        JSONObject status = new JSONObject();

        status.put("base_sp_defense", pokemon_main_data.get("sp_defense"));
        status.put("base_speed", pokemon_main_data.get("speed"));
        status.put("base_attack", pokemon_main_data.get("attack"));
        status.put("base_defense", pokemon_main_data.get("defense"));
        status.put("base_sp_attack", pokemon_main_data.get("sp_attack"));
        status.put("base_hp", pokemon_main_data.get("hp"));

        pokemon_data.put("status", status);

        return pokemon_data;
    }

    @GetMapping("/ranking/")
    public List<Map<String, Object>> getPokemonRanking() {
        return pokemonJdbcDAO.getPokemonRankingByStats();
    }

    @GetMapping("/ranking/normal")
    public List<Map<String, Object>> getPokemonRankingNormalOnly() {
        return pokemonJdbcDAO.getPokemonRankingByStatsNormalOnly();
    }
}
