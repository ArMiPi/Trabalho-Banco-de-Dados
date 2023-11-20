package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.PokeClassJdbcDAO;
import uel.bd.Bulbapedia.models.PokeClass;
import uel.bd.Bulbapedia.models.Type;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/class")
public class PokeClassController {
    @Autowired
    private PokeClassJdbcDAO pokeClassJdbcDAO;

    @PostMapping("/populate")
    public void populatePokeClass() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/move-damage-class");

            while(true) {
                for(Object o: (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    pokeClassJdbcDAO.create(new PokeClass(
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
}
