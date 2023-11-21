package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.HaveAbilityJdbcDAO;
import uel.bd.Bulbapedia.models.HaveAbility;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/have_ability")
public class HaveAbilityController {
    @Autowired
    private HaveAbilityJdbcDAO haveAbilityJdbcDAO;

    @PostMapping("/populate")
    public void populateHaveAbility() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/ability");

            while(true) {
                for(Object o: (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    JSONObject ability = APIRequests.getAPIResponse((String) result.get("url"));
                    if(ability == null) continue;

                    int id = ((Long) ability.get("id")).intValue();
                    JSONArray pokemons = (JSONArray) ability.get("pokemon");

                    for(Object pokemon: pokemons) {
                        JSONObject pkmn = (JSONObject) ((JSONObject) pokemon).get("pokemon");

                        try {
                            int is_hidden = (boolean) ((JSONObject) pokemon).get("is_hidden") ? 1 : 0;
                            haveAbilityJdbcDAO.create(new HaveAbility(
                                APIRequests.getIDFromURL((String) pkmn.get("url")),
                                id,
                                is_hidden
                            ));
                        } catch (Exception e) {
                            System.out.println(
                                    "Não foi possível inserir valores para id_pokedex = " +
                                            APIRequests.getIDFromURL((String) pkmn.get("url")) +
                                    " e id_ability = " + id);
                        }
                    }
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
