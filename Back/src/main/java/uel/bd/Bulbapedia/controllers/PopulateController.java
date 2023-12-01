package uel.bd.Bulbapedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/populate")
public class PopulateController {
    @Autowired
    GenerationController generationController;

    @Autowired
    PokemonController pokemonController;

    @Autowired
    AbilityController abilityController;

    @Autowired
    HaveAbilityController haveAbilityController;

    @Autowired
    PokemonGoController pokemonGoController;

    @Autowired
    ShinyController shinyController;

    @Autowired
    BaseGoStatsController baseGoStatsController;

    @Autowired
    TypeController typeController;

    @Autowired
    BaseStatsController baseStatsController;

    @Autowired
    PokeClassController pokeClassController;

    @Autowired
    MoveController moveController;

    @Autowired
    LearnMoveController learnMoveController;

    @Autowired
    TypeRelationsController typeRelationsController;

    @Autowired
    IsOfTypeController isOfTypeController;

    @PostMapping("")
    public void populate() {
        System.out.print("Populating generation...      ");
        generationController.populateGeneration();
        System.out.println("Done");

        System.out.print("Populating pokemon...      ");
        pokemonController.populatePokemon();
        System.out.println("Done");

        System.out.print("Populating ability...      ");
        abilityController.populateAbility();
        System.out.println("Done");

        System.out.print("Populating have_ability...      ");
        haveAbilityController.populateHaveAbility();
        System.out.println("Done");

        System.out.print("Populating pokemon_go...      ");
        pokemonGoController.populatePokemonGo();
        System.out.println("Done");

        System.out.print("Populating shiny...      ");
        shinyController.populateShiny();
        System.out.println("Done");

        System.out.print("Populating base_go_stats...      ");
        baseGoStatsController.populateBaseGoStats();
        System.out.println("Done");

        System.out.print("Populating type...      ");
        typeController.populateType();
        System.out.println("Done");

        System.out.print("Populating base_stats...      ");
        baseStatsController.populateBaseStats();
        System.out.println("Done");

        System.out.print("Populating class...      ");
        pokeClassController.populatePokeClass();
        System.out.println("Done");

        System.out.print("Populating move...      ");
        moveController.populateMove();
        System.out.println("Done");

        System.out.print("Populating learn_move...      ");
        learnMoveController.populateLearnMove();
        System.out.println("Done");

        System.out.print("Populating type_relations...      ");
        typeRelationsController.populateTypeRelations();
        System.out.println("Done");

        System.out.print("Populating is_of_type...      ");
        isOfTypeController.populateIsOfType();
        System.out.println("Done");
    }
}
