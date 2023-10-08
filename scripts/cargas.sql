-- Cargas generation
INSERT INTO generation(id_generation, gen_number, region)
VALUES
    (1, 1, 'kanto'),
    (2, 2, 'johto'),
    (3, 3, 'hoenn');

-- Cargas pokemon
INSERT INTO pokemon(id_pokedex, name, height, weight, capture_rate, rarity, sprite, id_generation, evolves_from)
VALUES
    (1, 'bulbasaur', 7, 69, 45, 'N', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png', 1, NULL),
    (2, 'ivysaur', 10, 130, 45, 'N', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png', 1, 1),
    (3, 'venusaur', 20, 1000, 45, 'N', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png', 1, 2);

-- Cargas ability
INSERT INTO ability(id_ability, name, is_hidden)
VALUES
    (65, 'overgrow', 0),
    (34, 'chlorophyll', 1);

-- Cargas have_ability
INSERT INTO have_ability(id_pokemon, id_ability)
VALUES
    (1, 65),
    (2, 65),
    (3, 65),
    (1, 34),
    (2, 34),
    (3, 34);

-- Cargas pokemon_go
INSERT INTO pokemon_go(id_pokemon_go, id_pokemon, raid_exclusive, max_cp, buddy_distance, candy_to_evolve)
VALUES
    (1, 1, 0, 981, 3, 25),
    (2, 2, 0, 1943, 3, 100),
    (3, 3, 0, 3112, 3, NULL);

-- Cargas shiny
INSERT INTO shiny(id_shiny, id_pokemon, egg, raid, wild, sprite)
VALUES
    (1, 1, 1, 1, 1, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png'),
    (2, 2, 0, 1, 1, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png'),
    (3, 3, 0, 1, 1, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/3.png');

-- Cargas base_go_stats
INSERT INTO base_go_stats(id_stats_go, id_pokemon, stamina, defense, attack)
VALUES
    (1, 1, 128, 111, 118),
    (2, 2, 155, 143, 151),
    (3, 3, 190, 189, 198);

-- Cargas type
INSERT INTO type(id_type, name)
VALUES
    (1, 'normal'),
    (12, 'grass'),
    (4, 'poison');

-- Cargas base_stats
INSERT INTO base_stats(id_stats, id_pokemon, hp, attack, defense, sp_attack, sp_defense, speed)
VALUES
    (1, 1, 45, 49, 49, 65, 65, 45),
    (2, 2, 60, 62, 63, 80, 80, 60),
    (3, 3, 80, 82, 83, 100, 100, 80);

-- Cargas move
INSERT INTO move(id_move, name, accuracy, class, power, pp, id_type)
VALUES
    (13, 'razor-wind', 100, 'special', 80, 10, 1),
    (15, 'cut', 95, 'physical', 50, 30, 1),
    (72, 'mega-drain', 100, 'special', 40, 15, 12);

-- Cargas learn_move
INSERT INTO learn_move(id_pokemon, id_move)
VALUES
    (1, 15),
    (1, 13),
    (3, 72);

-- Cargas type_relations
INSERT INTO type_relations(id_attacker, id_defender, multiplier)
VALUES
    (12, 4, 0.5),
    (4, 12, 2);

INSERT INTO is_of_type(id_pokemon, id_type)
VALUES
    (1, 12),
    (1, 4);
