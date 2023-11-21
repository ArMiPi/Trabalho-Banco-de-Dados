package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.BaseGoStatsJdbcDAO;
import uel.bd.Bulbapedia.models.BaseGoStats;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/base_go_stats")
public class BaseGoStatsController {
    @Autowired
    public BaseGoStatsJdbcDAO baseGoStatsJdbcDAO;

    public void populateBaseGoStats() {
        try {
            JSONArray stats =
                    APIRequests.getGoAPIResponseAsArray("https://pokemon-go1.p.rapidapi.com/pokemon_stats.json");

            for(Object o: stats) {
                JSONObject stats_info = (JSONObject) o;

                if(!((String) stats_info.get("form")).equals("Normal")) { continue; }

                baseGoStatsJdbcDAO.create(new BaseGoStats(
                        ((Long) stats_info.get("pokemon_id")).intValue(),
                        ((Long) stats_info.get("pokemon_id")).intValue(),
                        ((Long) stats_info.get("base_stamina")).intValue(),
                        ((Long) stats_info.get("base_defense")).intValue(),
                        ((Long) stats_info.get("base_attack")).intValue()
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
