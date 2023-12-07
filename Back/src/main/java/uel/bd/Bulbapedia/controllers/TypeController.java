package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.TypeJdbcDAO;
import uel.bd.Bulbapedia.models.Type;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeJdbcDAO typeJdbcDAO;

    @PostMapping("/populate")
    public void populateType() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/type");

            while(true) {
                for(Object o: (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    typeJdbcDAO.create(new Type(
                            APIRequests.getIDFromURL((String) result.get("url")),
                            (String) result.get("name")
                    ));
                }

                if(generalInfo.get("next") == null) {
                    break;
                } else {
                    generalInfo = APIRequests.getAPIResponse((String) generalInfo.get("next"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/pokemon_distribution")
    public List<Map<String, Object>> getPokemonDistribution() {
        return typeJdbcDAO.getPokemonDistribution();
    }
}
