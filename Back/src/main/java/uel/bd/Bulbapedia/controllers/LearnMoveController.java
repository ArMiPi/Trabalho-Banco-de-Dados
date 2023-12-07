package uel.bd.Bulbapedia.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.LearnMoveJdbcDAO;
import uel.bd.Bulbapedia.models.LearnMove;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/learn_move")
public class LearnMoveController {
    @Autowired
    private LearnMoveJdbcDAO learnMoveJdbcDAO;

    @PostMapping("/populate")
    public void populateLearnMove() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/move");

            while(true){
                for(Object obj: (JSONArray) generalInfo.get("results")){
                    JSONObject result = (JSONObject) obj;

                    JSONObject move = APIRequests.getAPIResponse((String) result.get("url"));
                    if(move == null) continue;

                    int move_id = ((Long) move.get("id")).intValue();

                    JSONArray pokemons = (JSONArray) move.get("learned_by_pokemon");
                    for(Object pokemon: pokemons){
                        JSONObject pk = (JSONObject) pokemon;
                        try {
                            learnMoveJdbcDAO.create(new LearnMove(
                                    APIRequests.getIDFromURL((String) pk.get("url")),
                                    move_id));
                        } catch (Exception e) {
                            System.out.println("Pokemon = "+pk.get("url")
                                                    + " Move = "+move_id);
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
