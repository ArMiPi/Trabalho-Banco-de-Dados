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
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon");

            while(true){
                for(Object obj: (JSONArray) generalInfo.get("results")){
                    JSONObject result = (JSONObject) obj;

                    JSONObject pokemon = APIRequests.getAPIResponse((String) result.get("url"));
                    int pokemon_id = APIRequests.getIDFromURL((String) result.get("url"));

                    JSONArray moves = (JSONArray) pokemon.get("moves");
                    for(Object move: moves){
                        JSONObject m = (JSONObject) ((JSONObject) move).get("move");
                        learnMoveJdbcDAO.create(new LearnMove(
                                pokemon_id,
                                APIRequests.getIDFromURL((String) m.get("url"))));
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
