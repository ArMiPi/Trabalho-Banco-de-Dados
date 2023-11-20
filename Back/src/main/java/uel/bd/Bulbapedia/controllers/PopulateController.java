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
    TypeController typeController;

    @Autowired
    BaseStatsController baseStatsController;

    @Autowired
    PokeClassController pokeClassController;

    @Autowired
    MoveController moveController;

    @PostMapping("/")
    public void populate() {
        generationController.populateGeneration();

        pokemonController.populatePokemon();

        abilityController.populateAbility();

        haveAbilityController.populateHaveAbility();

        // Populate pokemon_go

        // Populate shiny

        // Populate base_go_stats

        typeController.populateType();

        baseStatsController.populateBaseStats();

        pokeClassController.populatePokeClass();

        moveController.populateMove();

        // Populate learn_move

        // Populate type_relations

        // Populate is_of_type
    }
}
