package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uel.bd.Bulbapedia.DAO.PokemonJdbcDAO;
import uel.bd.Bulbapedia.models.Pokemon;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.util.ArrayList;
import java.util.List;


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
    public Pokemon getPokemon(@PathVariable int id) {
        return pokemonJdbcDAO.get(id);
    }
}
