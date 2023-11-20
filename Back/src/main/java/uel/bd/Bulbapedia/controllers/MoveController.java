package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.MoveJdbcDAO;
import uel.bd.Bulbapedia.models.Move;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/move")
public class MoveController {
    @Autowired
    private MoveJdbcDAO moveJdbcDAO;

    @PostMapping("/populate")
    public void populateMove() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/move");

            while(true) {
                for(Object o: (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    int move_id = APIRequests.getIDFromURL((String) result.get("url"));
                    String name = (String) result.get("name");

                    JSONObject move = APIRequests.getAPIResponse((String) result.get("url"));

                    JSONArray learned_by = (JSONArray) move.get("learned_by_pokemon");
                    if(learned_by.size() == 0) { continue; }

                    JSONObject move_class = (JSONObject) move.get("damage_class");
                    JSONObject type = (JSONObject) move.get("type");

                    int accuracy;
                    if((Long) move.get("accuracy") == null) {
                        accuracy = 0;
                    } else {
                        accuracy = ((Long) move.get("accuracy")).intValue();
                    }

                    int power;
                    if((Long) move.get("power") == null) {
                        power = 0;
                    } else {
                        power = ((Long) move.get("power")).intValue();
                    }

                    moveJdbcDAO.create(new Move(
                        move_id,
                        name,
                        accuracy,
                        APIRequests.getIDFromURL((String) move_class.get("url")),
                        power,
                        ((Long) move.get("pp")).intValue(),
                        APIRequests.getIDFromURL((String) type.get("url"))
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
