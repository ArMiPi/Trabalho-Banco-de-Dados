package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.IsOfTypeJdbcDAO;
import uel.bd.Bulbapedia.models.IsOfType;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/is_of_type")
public class IsOfTypeController {
    @Autowired
    private IsOfTypeJdbcDAO isOfTypeJdbcDAO;

    @PostMapping("/populate")
    public void populateIsOfType() {
        try{
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon");

            while(true){
                for(Object obj: (JSONArray) generalInfo.get("results")){
                    JSONObject result = (JSONObject) obj;

                    JSONObject pokemon = APIRequests.getAPIResponse((String) result.get("url"));
                    if(pokemon == null) continue;
                    int pokemon_id = ((Long) pokemon.get("id")).intValue();

                    JSONArray types = (JSONArray) pokemon.get("types");
                    for(Object type: types){
                        JSONObject tp = (JSONObject) type;
                        JSONObject t = (JSONObject) tp.get("type");
                        try {
                            isOfTypeJdbcDAO.create(new IsOfType(
                                    pokemon_id,
                                    APIRequests.getIDFromURL((String) t.get("url")),
                                    ((Long) tp.get("slot")).intValue()
                            ));
                        } catch (Exception e) {
                            if(pokemon_id > 10000) {
                                System.out.println("pokemon = " + pokemon_id + " type = " +
                                        APIRequests.getIDFromURL((String) t.get("url"))
                                        + " slot = " + tp.get("slot"));
                            } else {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                }
                if(generalInfo.get("next") == null) {
                    break;
                } else {
                    generalInfo = APIRequests.getAPIResponse((String) generalInfo.get("next"));
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
