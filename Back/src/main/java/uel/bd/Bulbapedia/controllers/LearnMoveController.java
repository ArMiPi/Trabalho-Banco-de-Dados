package uel.bd.Bulbapedia.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.LearnMoveJdbcDAO;
import uel.bd.Bulbapedia.DAO.PokemonJdbcDAO;
import uel.bd.Bulbapedia.models.LearnMove;
import uel.bd.Bulbapedia.models.Pokemon;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/learn_move")
public class LearnMoveController {
    @Autowired
    private LearnMoveJdbcDAO learnMoveJdbcDAO;
    @Autowired
    PokemonJdbcDAO pokemonJdbcDAO;

    @PostMapping("/populate")
    public void populateLearnMove() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon");

            while(true){
                for(Object obj: (JSONArray) generalInfo.get("results")){
                    JSONObject result = (JSONObject) obj;

                    JSONObject pokemon = APIRequests.getAPIResponse((String) result.get("url"));
                    if(pokemon == null) continue;
                    int pokemon_id = ((Long) pokemon.get("id")).intValue();

                    try {
                        Pokemon pk_temp = pokemonJdbcDAO.get(pokemon_id);
                    } catch (Exception e) {
                        continue;
                    }

                    JSONArray moves = (JSONArray) pokemon.get("moves");
                    for(Object move: moves){
                        JSONObject mv = (JSONObject) move;
                        JSONObject mv_data = (JSONObject) mv.get("move");
                        try {
                            learnMoveJdbcDAO.create(new LearnMove(
                                    pokemon_id,
                                    APIRequests.getIDFromURL((String) mv_data.get("url"))));
                        } catch (Exception e) {
                            System.out.println("Pokemon = "+pokemon_id
                                                    + " Move = "+mv_data.get("url"));
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
