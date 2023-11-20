package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.GenerationJdbcDAO;
import uel.bd.Bulbapedia.models.Generation;
import uel.bd.Bulbapedia.utils.APIRequests;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/generation")
public class GenerationController {
    @Autowired
    private GenerationJdbcDAO generationJdbcDAO;

    @PostMapping("/populate")
    public void populateGeneration() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/generation");

            while(true) {

                for(Object o : (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    JSONObject generation = APIRequests.getAPIResponse((String) result.get("url"));
                    if(generation != null) {
                        JSONObject main_region = (JSONObject) generation.get("main_region");
                        generationJdbcDAO.create(new Generation(
                                ((Long) generation.get("id")).intValue(),
                                ((Long) generation.get("id")).intValue(),
                                (String) main_region.get("name")
                        ));
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
