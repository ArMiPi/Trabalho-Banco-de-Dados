CREATE TABLE generation (
    id_generation INT,
    gen_number INT,
    region VARCHAR(100),
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
        ON DELETE SET NULL
);

CREATE TABLE ability (
    id_ability INT,
    name VARCHAR(100) NOT NULL,
    is_hidden BIT NOT NULL,
    CONSTRAINT pk_ability PRIMARY KEY(id_ability)
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
    raid_exclusive BIT NOT NULL,
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
    CONSTRAINT ck_flee_rate CHECK(flee_rate >= 0.0 AND flee_rate <= 1.0)
);

CREATE TABLE shiny (
    id_shiny INT,
    id_pokemon INT NOT NULL,
    egg BIT NOT NULL,
    raid BIT NOT NULL,
    wild BIT NOT NULL,
    sprite VARCHAR(200),
    CONSTRAINT pk_shiny PRIMARY KEY(id_shiny),
    CONSTRAINT fk_pokemon_go PRIMARY KEY(id_pokemon)
        REFERENCES pokemon_go(id_pokemon_go)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);