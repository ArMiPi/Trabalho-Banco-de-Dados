package uel.bd.Bulbapedia.controllers;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.ShinyJdbcDAO;
import uel.bd.Bulbapedia.models.Shiny;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.util.Iterator;

@RestController
@RequestMapping("/shiny")
public class ShinyController {
    @Autowired
    private ShinyJdbcDAO shinyJdbcDAO;

    @PostMapping("/populate")
    public void populateShiny() {
        try {
            JSONObject generalInfo = APIRequests.getGoAPIResponse("https://pokemon-go1.p.rapidapi.com/shiny_pokemon.json");
            if(generalInfo == null) {
                return;
            }
            Iterator<String> keys = generalInfo.keySet().iterator();
            while(keys.hasNext()) {
                String key = keys.next();
                JSONObject result = (JSONObject) generalInfo.get(key);
                int pokemon_id = Integer.parseInt(key);
                int egg =  ((boolean) result.get("found_egg"))? 1: 0;
                int raid = ((boolean) result.get("found_raid"))? 1: 0;
                int wild = ((boolean) result.get("found_wild"))? 1: 0;
                JSONObject Pokemon = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon/"+pokemon_id);
                JSONObject spriteObj = (JSONObject) Pokemon.get("sprites");
                String sprite = (String) spriteObj.get("front_shiny");

                shinyJdbcDAO.create(new Shiny(
                        pokemon_id,
                        pokemon_id,
                        egg, raid,
                        wild,
                        sprite));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
