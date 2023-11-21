package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.PokemonGoJdbcDAO;
import uel.bd.Bulbapedia.models.PokemonGo;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/pokemon_go")
public class PokemonGoController {
    @Autowired
    public PokemonGoJdbcDAO pokemonGoJdbcDAO;

    @PostMapping("/populate")
    public void populatePokemonGo() {
        try {
            JSONObject pokemons =
                    APIRequests.getGoAPIResponse("https://pokemon-go1.p.rapidapi.com/pokemon_names.json");

            JSONObject raid_exclusive =
                    APIRequests.getGoAPIResponse("https://pokemon-go1.p.rapidapi.com/raid_exclusive_pokemon.json");

            JSONArray max_cp =
                    APIRequests.getGoAPIResponseAsArray("https://pokemon-go1.p.rapidapi.com/pokemon_max_cp.json");

            JSONObject buddy_distances =
                    APIRequests.getGoAPIResponse("https://pokemon-go1.p.rapidapi.com/pokemon_buddy_distances.json");

            JSONObject candy_to_evolve =
                    APIRequests.getGoAPIResponse("https://pokemon-go1.p.rapidapi.com/pokemon_candy_to_evolve.json");

            Map<String, PokemonGo> pkmns = new HashMap<String, PokemonGo>();

            Iterator<String> keys = pokemons.keySet().iterator();
            while(keys.hasNext()) {
                String key = keys.next();

                int id = Integer.parseInt(key);

                PokemonGo pkGo = new PokemonGo();
                pkGo.setId_pokemon_go(id);
                pkGo.setId_pokemon(id);
                pkGo.setRaid_exclusive((raid_exclusive.get(key) == null)? 0 : 1);

                pkmns.put(key, pkGo);
            }

            for(Object o: max_cp) {
                JSONObject cp_info = (JSONObject) o;

                if(!((String) cp_info.get("form")).equals("Normal")) { continue; }

                if(pkmns.containsKey(String.valueOf((Long) cp_info.get("pokemon_id")))) {
                    PokemonGo pk = pkmns.get(String.valueOf((Long) cp_info.get("pokemon_id")));

                    pk.setMax_cp(((Long) cp_info.get("max_cp")).intValue());
                }
            }

            Iterator<String> bDistances = buddy_distances.keySet().iterator();
            while(bDistances.hasNext()) {
                String key = bDistances.next();

                JSONArray dist_array = (JSONArray) buddy_distances.get(key);

                for(Object o: dist_array) {
                    JSONObject dist_info = (JSONObject) o;

                    if(!((String) dist_info.get("form")).equals("Normal")) { continue; }

                    if(pkmns.containsKey(String.valueOf((Long) dist_info.get("pokemon_id")))) {
                        PokemonGo pkGo = pkmns.get(String.valueOf((Long) dist_info.get("pokemon_id")));

                        pkGo.setBuddy_distance(Integer.parseInt(key));
                    }
                }
            }

            Iterator<String> candy = candy_to_evolve.keySet().iterator();
            while(candy.hasNext()) {
                String key = candy.next();

                JSONArray candy_array = (JSONArray) candy_to_evolve.get(key);

                for(Object o: candy_array) {
                    JSONObject candy_info = (JSONObject) o;

                    if(!((String) candy_info.get("form")).equals("Normal")) { continue; }

                    if(pkmns.containsKey(String.valueOf((Long) candy_info.get("pokemon_id")))) {
                        PokemonGo pkGo = pkmns.get(String.valueOf((Long) candy_info.get("pokemon_id")));

                        pkGo.setCandy_to_evolve(Integer.parseInt(key));
                    }
                }
            }

            for(PokemonGo pkm: pkmns.values()) {
                pokemonGoJdbcDAO.create(pkm);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
