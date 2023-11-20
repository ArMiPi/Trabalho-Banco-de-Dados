package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.AbilityJdbcDAO;
import uel.bd.Bulbapedia.models.Ability;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/ability")
public class AbilityController {
    @Autowired
    private AbilityJdbcDAO abilityJdbcDAO;

    @PostMapping("/populate")
    public void populateAbility() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/ability");

            while(true) {
                for(Object o: (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    abilityJdbcDAO.create(new Ability(
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
