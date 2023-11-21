package uel.bd.Bulbapedia.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.ShinyJdbcDAO;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/shiny")
public class ShinyController {
    @Autowired
    private ShinyJdbcDAO shinyJdbcDAO;

    @PostMapping("/populate")
    public void populateShiny() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon");

            while(true){
                for(Object obj: (JSONArray) generalInfo.get("results")){
                    JSONObject result = (JSONObject) obj;

                    JSONObject pokemon = APIRequests.getAPIResponse((String) result.get("url"));
                    int pokemon_id = APIRequests.getIDFromURL((String) result.get("url"));

                    JSONObject sprites = (JSONObject) pokemon.get("sprites");
                    String front_shiny = (String) sprites.get("front_shiny");



                   // shinyJdbcDAO.create(id_shiny, pokemon_id, egg, raid, wild, front_shiny);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
