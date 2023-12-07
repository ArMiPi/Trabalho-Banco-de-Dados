package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uel.bd.Bulbapedia.DAO.*;
import uel.bd.Bulbapedia.models.*;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000") //serve para O react se comunicar corretamente com o Spring
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    private PokemonJdbcDAO pokemonJdbcDAO;
    @Autowired
    private BaseStatsJdbcDAO baseStatsJdbcDAO;
    @Autowired
    private AbilityJdbcDAO abilityJdbcDAO;
    @Autowired
    private HaveAbilityJdbcDAO haveAbilityJdbcDAO;
    @Autowired
    private IsOfTypeJdbcDAO isOfTypeJdbcDAO;
    @Autowired
    private TypeJdbcDAO typeJdbcDAO;
    @Autowired
    private PokemonGoJdbcDAO pokemonGoJdbcDAO;
    @Autowired
    private BaseGoStatsJdbcDAO baseGoStatsJdbcDAO;
    @Autowired
    private ShinyJdbcDAO shinyJdbcDAO;


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
        JSONObject json = new JSONObject();

        // Dados gerais do Pokémon
        Pokemon pokemonData = pokemonJdbcDAO.get(id);

        json.put("id", pokemonData.getIdPokedex());
        json.put("name", pokemonData.getName());
        json.put("height", pokemonData.getHeight());
        json.put("weight", pokemonData.getWeight());
        json.put("capture_rate", pokemonData.getCaptureRate());
        json.put("rarity", pokemonData.getRarity());
        json.put("sprite", pokemonData.getSprite());
        json.put("generation", pokemonData.getIdGeneration());

        // Status base do Pokémon
        BaseStats baseStats = baseStatsJdbcDAO.getByPokemon(id);
        JSONObject status = new JSONObject();

        status.put("base_hp", baseStats.getHp());
        status.put("base_attack", baseStats.getAttack());
        status.put("base_defense", baseStats.getDefense());
        status.put("base_sp_attack", baseStats.getSpAttack());
        status.put("base_sp_defense", baseStats.getSpDefense());
        status.put("base_speed", baseStats.getSpeed());

        json.put("status", status);

        // Dados das habilidades do Pokémon
        List<HaveAbility> abilities = haveAbilityJdbcDAO.getByPokemon(id);
        JSONArray abilitiesData = new JSONArray();
        for(HaveAbility ability: abilities) {
            JSONObject abilityData = new JSONObject();

            abilityData.put("hidden", ability.getIs_hidden() == 1);
            abilityData.put("name", abilityJdbcDAO.get(ability.getId_ability()).getName());

            abilitiesData.add(abilityData);
        }

        json.put("abilities", abilitiesData);

        // Informações de tipo
        List<IsOfType> types = isOfTypeJdbcDAO.getByPokemon(id);
        List<String> typeNames = new ArrayList<String>();
        for(IsOfType type: types) {
            typeNames.add(typeJdbcDAO.get(type.getId_type()).getName());
        }

        json.put("types", typeNames);

        // Dados Pokémon Go
        PokemonGo pokemonGoData;
        try {
            pokemonGoData = pokemonGoJdbcDAO.getByPokedexId(id);
        } catch (Exception e) {
            return json;
        }

        BaseGoStats baseGoStats = baseGoStatsJdbcDAO.getByPokemon(id);

        Shiny shiny;
        try {
            shiny = shinyJdbcDAO.getByPokemon(id);
        } catch (Exception e) {
            shiny = null;
        }

        JSONObject goInfo = new JSONObject();

        goInfo.put("raid_exclusive", pokemonGoData.getRaid_exclusive() == 1);
        goInfo.put("max_cp", pokemonGoData.getMax_cp());
        goInfo.put("buddy_distance", pokemonGoData.getBuddy_distance());
        goInfo.put("candy_to_evolve", pokemonGoData.getCandy_to_evolve());

        // ----------------------------------------------------------------
        JSONObject goStats = new JSONObject();

        goStats.put("stamina", baseGoStats.getStamina());
        goStats.put("defense", baseGoStats.getDefense());
        goStats.put("attack", baseGoStats.getAttack());

        goInfo.put("stats", goStats);

        // ----------------------------------------------------------------
        if(shiny != null) {
            JSONObject shinyInfo = new JSONObject();

            shinyInfo.put("egg", shiny.getEgg() == 1);
            shinyInfo.put("raid", shiny.getRaid() == 1);
            shinyInfo.put("wild", shiny.getWild() == 1);
            shinyInfo.put("sprite", shiny.getSprite());

            goInfo.put("shiny", shinyInfo);
        }

        json.put("pokemon_go_info", goInfo);

        return json;
    }

    @GetMapping("/teste/{id}")
    public Map<String, Object> getPokemonTest(@PathVariable int id) {
        Map<String, Object> teste = null;
        try {
            teste = pokemonJdbcDAO.getTest(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("oi");

        return teste;
    }
}
