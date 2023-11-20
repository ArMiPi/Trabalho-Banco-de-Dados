package uel.bd.Bulbapedia.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.BaseStatsJdbcDAO;
import uel.bd.Bulbapedia.models.BaseStats;
import uel.bd.Bulbapedia.models.Type;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("base_stats")
public class BaseStatsController {
    @Autowired
    private BaseStatsJdbcDAO baseStatsJdbcDAO;

    @PostMapping("/populate")
    public void populateBaseStats() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/pokemon");

            while(true) {
                for(Object o: (JSONArray) generalInfo.get("results")) {
                    JSONObject result = (JSONObject) o;

                    JSONObject pokemon = APIRequests.getAPIResponse((String) result.get("url"));

                    JSONArray stats = (JSONArray) pokemon.get("stats");

                    int hp = 0, attack = 0, defense = 0, spAttack = 0, spDefense = 0, speed = 0;
                    for(Object ob: stats) {
                        JSONObject status = (JSONObject) ob;

                        JSONObject stat = (JSONObject) status.get("stat");
                        String stat_name = (String) stat.get("name");

                        if(stat_name.equals("hp")) {
                            hp = ((Long) status.get("base_stat")).intValue();
                        } else if(stat_name.equals("attack")) {
                            attack = ((Long) status.get("base_stat")).intValue();
                        } else if(stat_name.equals("defense")) {
                            defense = ((Long) status.get("base_stat")).intValue();
                        } else if(stat_name.equals("special-attack")) {
                            spAttack = ((Long) status.get("base_stat")).intValue();
                        } else if(stat_name.equals("special-defense")) {
                            spDefense = ((Long) status.get("base_stat")).intValue();
                        } else if(stat_name.equals("speed")) {
                            speed = ((Long) status.get("base_stat")).intValue();
                        }
                    }

                    baseStatsJdbcDAO.create(new BaseStats(
                        ((Long) pokemon.get("id")).intValue(),
                        ((Long) pokemon.get("id")).intValue(),
                        hp,
                        attack,
                        defense,
                        spAttack,
                        spDefense,
                        speed
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
