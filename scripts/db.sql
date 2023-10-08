CREATE TABLE generation (
    id_generation INT,
    gen_number INT,
    region VARCHAR(100) NOT NULL,
    CONSTRAINT pk_generation PRIMARY KEY (id_generation)
);

CREATE TABLE pokemon (
    id_pokedex INT,
    name VARCHAR(100) NOT NULL,
    height INT,
    weight INT,
    capture_rate INT,
    rarity CHAR,
    sprite VARCHAR(200),
    id_generation INT,
    CONSTRAINT pk_pokemon PRIMARY KEY(id_pokedex),
    CONSTRAINT fk_generation FOREIGN KEY(id_generation)
        REFERENCES generation(id_generation)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    CONSTRAINT unique_name_pokemon UNIQUE(name),
    CONSTRAINT ck_height CHECK(height > 0),
    CONSTRAINT ck_weight CHECK(weight > 0),
    CONSTRAINT ck_capture_rate CHECK (capture_rate > 0)
);

CREATE TABLE ability (
    id_ability INT,
    name VARCHAR(100) NOT NULL,
    is_hidden BIT DEFAULT '0'::BIT NOT NULL,
    CONSTRAINT pk_ability PRIMARY KEY(id_ability),
    CONSTRAINT unique_name_ability UNIQUE(name)
);

CREATE TABLE have_ability (
    id_pokemon INT,
    id_ability INT,
    CONSTRAINT pk_have_ability PRIMARY KEY(id_pokemon, id_ability),
    CONSTRAINT fk_pokemon FOREIGN KEY(id_pokemon)
        REFERENCES pokemon(id_pokedex)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_ability FOREIGN KEY(id_ability)
        REFERENCES ability(id_ability)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE pokemon_go (
    id_pokemon_go INT,
    id_pokemon INT NOT NULL,
    raid_exclusive BIT DEFAULT '0'::BIT NOT NULL,
    max_cp INT,
    buddy_distance INT,
    candy_to_evolve INT,
    capture_rate FLOAT,
    flee_rate FLOAT,
    CONSTRAINT pk_pokemon_go PRIMARY KEY(id_pokemon_go),
    CONSTRAINT fk_pokemon FOREIGN KEY(id_pokemon)
        REFERENCES pokemon(id_pokedex)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT ck_capture_rate CHECK(capture_rate >= 0.0 AND capture_rate <= 1.0),
    CONSTRAINT ck_flee_rate CHECK(flee_rate >= 0.0 AND flee_rate <= 1.0),
    CONSTRAINT ck_max_cp CHECK(max_cp >= 0),
    CONSTRAINT ck_buddy_distance CHECK(buddy_distance >= 0),
    CONSTRAINT ck_candy_to_evolve CHECK(candy_to_evolve >= 0)
);

CREATE TABLE shiny (
    id_shiny INT,
    id_pokemon INT NOT NULL,
    egg BIT DEFAULT '0'::BIT NOT NULL,
    raid BIT DEFAULT '0'::BIT NOT NULL,
    wild BIT DEFAULT '0'::BIT NOT NULL,
    sprite VARCHAR(200),
    CONSTRAINT pk_shiny PRIMARY KEY(id_shiny),
    CONSTRAINT fk_pokemon_go FOREIGN KEY(id_pokemon)
        REFERENCES pokemon_go(id_pokemon_go)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE base_go_stats (
    id_stats_go INT,
    id_pokemon INT NOT NULL,
    stamina INT,
    defense INT,
    attack INT,
    CONSTRAINT pk_go_stats PRIMARY KEY(id_stats_go),
    CONSTRAINT fk_pokemon_go FOREIGN KEY(id_pokemon)
        REFERENCES pokemon_go(id_pokemon_go)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT ck_stamina CHECK(stamina >= 0),
    CONSTRAINT ck_defense CHECK(defense >= 0),
    CONSTRAINT ck_attack CHECK(attack >= 0)
);

CREATE TABLE type (
    id_type INT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_type PRIMARY KEY(id_type),
    CONSTRAINT unique_name_type UNIQUE(name)
);

CREATE TABLE go_move (
    id_move INT,
    category VARCHAR(10),
    name VARCHAR(50) NOT NULL,
    power INT,
    id_type INT NOT NULL,
    CONSTRAINT pk_id_go_move PRIMARY KEY(id_move),
    CONSTRAINT fk_type FOREIGN KEY(id_type)
        REFERENCES type(id_type)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    CONSTRAINT ck_power CHECK(power > 0),
    CONSTRAINT unique_name_go_move UNIQUE(name)
);

CREATE TABLE go_learn (
    id_pokemon INT,
    id_move INT,
    CONSTRAINT pk_go_learn PRIMARY KEY(id_pokemon, id_move),
    CONSTRAINT fk_pokemon_go FOREIGN KEY(id_pokemon)
        REFERENCES pokemon_go(id_pokemon_go)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_move FOREIGN KEY(id_move)
        REFERENCES go_move(id_move)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE evolves (
    original_form INT,
    evolution INT,
    CONSTRAINT pk_evolves PRIMARY KEY(original_form, evolution),
    CONSTRAINT fk_original_id FOREIGN KEY(original_form)
        REFERENCES pokemon(id_pokedex)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_evolution FOREIGN KEY(evolution)
        REFERENCES pokemon(id_pokedex)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT ck_evolution CHECK(original_form <> evolution)
);

CREATE TABLE base_stats (
    id_stats INT,
    id_pokemon INT NOT NULL,
    hp INT,
    attack INT,
    defense INT,
    sp_attack INT,
    sp_defense INT,
    speed INT,
    CONSTRAINT pk_base_stats PRIMARY KEY(id_stats),
    CONSTRAINT fk_pokemon FOREIGN KEY(id_pokemon)
        REFERENCES pokemon(id_pokedex)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT ck_hp CHECK(hp >= 0),
    CONSTRAINT ck_attack CHECK(attack >= 0),
    CONSTRAINT ck_defense CHECK(defense >= 0),
    CONSTRAINT ck_sp_attack CHECK(sp_attack >= 0),
    CONSTRAINT ck_sp_defense CHECK(sp_defense >= 0),
    CONSTRAINT ck_speed CHECK(speed >= 0)
);

CREATE TABLE move (
    id_move INT,
    name VARCHAR(100) NOT NULL,
    accuracy INT,
    class VARCHAR(100),
    power INT,
    pp INT,
    id_type INT NOT NULL,
    CONSTRAINT pk_id_move PRIMARY KEY(id_move),
    CONSTRAINT fk_type FOREIGN KEY(id_type)
        REFERENCES type(id_type)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    CONSTRAINT ck_accuracy CHECK(accuracy >= 0),
    CONSTRAINT ck_power CHECK(power >= 0),
    CONSTRAINT ck_pp CHECK(pp >= 0),
    CONSTRAINT unique_name_move UNIQUE(name)
);

CREATE TABLE learn_move (
    id_pokemon INT,
    id_move INT,
    CONSTRAINT pk_learn PRIMARY KEY(id_pokemon, id_move),
    CONSTRAINT fk_pokemon_id FOREIGN KEY(id_pokemon)
        REFERENCES pokemon(id_pokedex)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_move_id FOREIGN KEY(id_move)
        REFERENCES move(id_move)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE type_relations (
    id_stronger INT,
    id_weaker INT,
    multiplier FLOAT,
    CONSTRAINT pk_type_relations PRIMARY KEY(id_stronger, id_weaker),
    CONSTRAINT ck_multiplier CHECK(multiplier >= 0)
);
