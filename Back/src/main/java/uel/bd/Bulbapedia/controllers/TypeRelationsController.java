package uel.bd.Bulbapedia.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uel.bd.Bulbapedia.DAO.TypeRelationsJdbcDAO;
import uel.bd.Bulbapedia.models.TypeRelations;
import uel.bd.Bulbapedia.utils.APIRequests;

@RestController
@RequestMapping("/type_relations")
public class TypeRelationsController {
    @Autowired
    private TypeRelationsJdbcDAO typeRelationsJdbcDAO;

    @PostMapping("/populate")
    public void populateTypeRelations() {
        try {
            JSONObject generalInfo = APIRequests.getAPIResponse("https://pokeapi.co/api/v2/type");

            for (Object obj : (JSONArray) generalInfo.get("results")) {
                JSONObject result = (JSONObject) obj;

                JSONObject type = APIRequests.getAPIResponse((String) result.get("url"));
                int type_id = APIRequests.getIDFromURL((String) result.get("url"));
                JSONObject damage_relations = (JSONObject) type.get("damage_relations");
                JSONArray double_damage_from = (JSONArray) damage_relations.get("double_damage_from");
                for(Object dr: double_damage_from){
                    JSONObject d = (JSONObject) dr;
                    typeRelationsJdbcDAO.create(new TypeRelations(
                            APIRequests.getIDFromURL((String) d.get("url")),
                            type_id,
                            2.0F
                    ));
                }
                JSONArray half_damage_from = (JSONArray) damage_relations.get("half_damage_from");
                for(Object dr: half_damage_from){
                    JSONObject d = (JSONObject) dr;
                    typeRelationsJdbcDAO.create(new TypeRelations(
                            APIRequests.getIDFromURL((String) d.get("url")),
                            type_id,
                            0.5F
                    ));
                }
                JSONArray no_damage_from = (JSONArray) damage_relations.get("no_damage_from");
                for(Object dr: no_damage_from){
                    JSONObject d = (JSONObject) dr;
                    typeRelationsJdbcDAO.create(new TypeRelations(
                            APIRequests.getIDFromURL((String) d.get("url")),
                            type_id,
                            0.0F
                    ));
                }
                JSONArray double_damage_to = (JSONArray) damage_relations.get("double_damage_to");
                for(Object dr: double_damage_to){
                    JSONObject d = (JSONObject) dr;
                    typeRelationsJdbcDAO.create(new TypeRelations(
                            type_id,
                            APIRequests.getIDFromURL((String) d.get("url")),
                            2.0F
                    ));
                }
                JSONArray half_damage_to = (JSONArray) damage_relations.get("half_damage_to");
                for(Object dr: half_damage_to){
                    JSONObject d = (JSONObject) dr;
                    typeRelationsJdbcDAO.create(new TypeRelations(
                            type_id,
                            APIRequests.getIDFromURL((String) d.get("url")),
                            0.5F
                    ));
                }
                JSONArray no_damage_to = (JSONArray) damage_relations.get("no_damage_to");
                for(Object dr: no_damage_to){
                    JSONObject d = (JSONObject) dr;
                    typeRelationsJdbcDAO.create(new TypeRelations(
                            type_id,
                            APIRequests.getIDFromURL((String) d.get("url")),
                            0.0F
                    ));
                }





             }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
