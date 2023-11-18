# Trabalho 1: Coleta e Análise de Dados Abertos
<p style="font-size: 10pt;">Aluno:  Arthur Misato Pimont</p>

## APIs

### [PokéAPI](https://pokeapi.co/docs/v2#info)

É uma API gratuita apenas com métodos HTTP GET. Esta API fornece diversas informações relacionadas ao mundo de Pokémon, com informações sobre os Pokémons, Movimentos, Itens, Localizações, Berries e muito mais. É dessa API que fornecerá a maioria dos dados utilizados nesse trabalho.

#### Exempos de requisição

1. Informações sobre um determinado Pokémon

    > https://pokeapi.co/api/v2/pokemon/35/

    ```json
    {
    "id": 35,
    "name": "clefairy",
    "base_experience": 113,
    "height": 6,
    "is_default": true,
    "order": 56,
    "weight": 75,
    "abilities": [
        {
        "is_hidden": true,
        "slot": 3,
        "ability": {
            "name": "friend-guard",
            "url": "https://pokeapi.co/api/v2/ability/132/"
        }
        }
    ],
    "forms": [
        {
        "name": "clefairy",
        "url": "https://pokeapi.co/api/v2/pokemon-form/35/"
        }
    ],
    "game_indices": [
        {
        "game_index": 35,
        "version": {
            "name": "white-2",
            "url": "https://pokeapi.co/api/v2/version/22/"
        }
        }
    ],
    "held_items": [
        {
        "item": {
            "name": "moon-stone",
            "url": "https://pokeapi.co/api/v2/item/81/"
        },
        "version_details": [
            {
            "rarity": 5,
            "version": {
                "name": "ruby",
                "url": "https://pokeapi.co/api/v2/version/7/"
            }
            }
        ]
        }
    ],
    "location_area_encounters": "/api/v2/pokemon/35/encounters",
    "moves": [
        {
        "move": {
            "name": "pound",
            "url": "https://pokeapi.co/api/v2/move/1/"
        },
        "version_group_details": [
            {
            "level_learned_at": 1,
            "version_group": {
                "name": "red-blue",
                "url": "https://pokeapi.co/api/v2/version-group/1/"
            },
            "move_learn_method": {
                "name": "level-up",
                "url": "https://pokeapi.co/api/v2/move-learn-method/1/"
            }
            }
        ]
        }
    ],
    "species": {
        "name": "clefairy",
        "url": "https://pokeapi.co/api/v2/pokemon-species/35/"
    },
    "sprites": {
        "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/35.png",
        "back_female": null,
        "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/35.png",
        "back_shiny_female": null,
        "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
        "front_female": null,
        "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/35.png",
        "front_shiny_female": null,
        "other": {
        "dream_world": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/35.svg",
            "front_female": null
        },
        "home": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/35.png",
            "front_shiny_female": null
        },
        "official-artwork": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/35.png"
        }
        },
        "versions": {
        "generation-i": {
            "red-blue": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/35.png",
            "back_gray": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/gray/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/35.png",
            "front_gray": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/gray/35.png"
            },
            "yellow": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/35.png",
            "back_gray": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/gray/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/35.png",
            "front_gray": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/gray/35.png"
            }
        },
        "generation-ii": {
            "crystal": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/35.png",
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/shiny/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/35.png",
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/shiny/35.png"
            },
            "gold": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/35.png",
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/shiny/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/35.png",
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/shiny/35.png"
            },
            "silver": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/35.png",
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/shiny/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/35.png",
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/35.png"
            }
        },
        "generation-iii": {
            "emerald": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/35.png",
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/35.png"
            },
            "firered-leafgreen": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/35.png",
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/shiny/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/35.png",
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/shiny/35.png"
            },
            "ruby-sapphire": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/35.png",
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/shiny/35.png",
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/35.png",
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/shiny/35.png"
            }
        },
        "generation-iv": {
            "diamond-pearl": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/35.png",
            "back_female": null,
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/shiny/35.png",
            "back_shiny_female": null,
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/shiny/35.png",
            "front_shiny_female": null
            },
            "heartgold-soulsilver": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/35.png",
            "back_female": null,
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/shiny/35.png",
            "back_shiny_female": null,
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/shiny/35.png",
            "front_shiny_female": null
            },
            "platinum": {
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/35.png",
            "back_female": null,
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/shiny/35.png",
            "back_shiny_female": null,
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/shiny/35.png",
            "front_shiny_female": null
            }
        },
        "generation-v": {
            "black-white": {
            "animated": {
                "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/35.gif",
                "back_female": null,
                "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/35.gif",
                "back_shiny_female": null,
                "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/35.gif",
                "front_female": null,
                "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/35.gif",
                "front_shiny_female": null
            },
            "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/35.png",
            "back_female": null,
            "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/35.png",
            "back_shiny_female": null,
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/35.png",
            "front_shiny_female": null
            }
        },
        "generation-vi": {
            "omegaruby-alphasapphire": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/shiny/35.png",
            "front_shiny_female": null
            },
            "x-y": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/shiny/35.png",
            "front_shiny_female": null
            }
        },
        "generation-vii": {
            "icons": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/35.png",
            "front_female": null
            },
            "ultra-sun-ultra-moon": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/35.png",
            "front_female": null,
            "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/shiny/35.png",
            "front_shiny_female": null
            }
        },
        "generation-viii": {
            "icons": {
            "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/35.png",
            "front_female": null
            }
        }
        }
    },
    "stats": [
        {
        "base_stat": 35,
        "effort": 0,
        "stat": {
            "name": "speed",
            "url": "https://pokeapi.co/api/v2/stat/6/"
        }
        }
    ],
    "types": [
        {
        "slot": 1,
        "type": {
            "name": "fairy",
            "url": "https://pokeapi.co/api/v2/type/18/"
        }
        }
    ],
    "past_types": [
        {
        "generation": {
            "name": "generation-v",
            "url": "https://pokeapi.co/api/v2/generation/5/"
        },
        "types": [
            {
            "slot": 1,
            "type": {
                "name": "normal",
                "url": "https://pokeapi.co/api/v2/type/1/"
            }
            }
        ]
        }
    ]
    }
    ```

2. Informações sobre uma determinada Ability

    > https://pokeapi.co/api/v2/abilities/1/

    ```json
    {
    "id": 1,
    "name": "stench",
    "is_main_series": true,
    "generation": {
        "name": "generation-iii",
        "url": "https://pokeapi.co/api/v2/generation/3/"
    },
    "names": [
        {
        "name": "Stench",
        "language": {
            "name": "en",
            "url": "https://pokeapi.co/api/v2/language/9/"
        }
        }
    ],
    "effect_entries": [
        {
        "effect": "This Pokémon's damaging moves have a 10% chance to make the target [flinch]{mechanic:flinch} with each hit if they do not already cause flinching as a secondary effect.\n\nThis ability does not stack with a held item.\n\nOverworld: The wild encounter rate is halved while this Pokémon is first in the party.",
        "short_effect": "Has a 10% chance of making target Pokémon [flinch]{mechanic:flinch} with each hit.",
        "language": {
            "name": "en",
            "url": "https://pokeapi.co/api/v2/language/9/"
        }
        }
    ],
    "effect_changes": [
        {
        "version_group": {
            "name": "black-white",
            "url": "https://pokeapi.co/api/v2/version-group/11/"
        },
        "effect_entries": [
            {
            "effect": "Has no effect in battle.",
            "language": {
                "name": "en",
                "url": "https://pokeapi.co/api/v2/language/9/"
            }
            }
        ]
        }
    ],
    "flavor_text_entries": [
        {
        "flavor_text": "è‡­ãã¦ã€€ç›¸æ‰‹ãŒ\nã²ã‚‹ã‚€ã€€ã“ã¨ãŒã‚ã‚‹ã€‚",
        "language": {
            "name": "ja-kanji",
            "url": "https://pokeapi.co/api/v2/language/11/"
        },
        "version_group": {
            "name": "x-y",
            "url": "https://pokeapi.co/api/v2/version-group/15/"
        }
        }
    ],
    "pokemon": [
        {
        "is_hidden": true,
        "slot": 3,
        "pokemon": {
            "name": "gloom",
            "url": "https://pokeapi.co/api/v2/pokemon/44/"
        }
        }
    ]
    }
    ```

3. Informações sobre um determinado Move

    > https://pokeapi.co/api/v2/move/1

    ```json
    {
    "id": 1,
    "name": "pound",
    "accuracy": 100,
    "effect_chance": null,
    "pp": 35,
    "priority": 0,
    "power": 40,
    "contest_combos": {
        "normal": {
        "use_before": [
            {
            "name": "double-slap",
            "url": "https://pokeapi.co/api/v2/move/3/"
            },
            {
            "name": "headbutt",
            "url": "https://pokeapi.co/api/v2/move/29/"
            },
            {
            "name": "feint-attack",
            "url": "https://pokeapi.co/api/v2/move/185/"
            }
        ],
        "use_after": null
        },
        "super": {
        "use_before": null,
        "use_after": null
        }
    },
    "contest_type": {
        "name": "tough",
        "url": "https://pokeapi.co/api/v2/contest-type/5/"
    },
    "contest_effect": {
        "url": "https://pokeapi.co/api/v2/contest-effect/1/"
    },
    "damage_class": {
        "name": "physical",
        "url": "https://pokeapi.co/api/v2/move-damage-class/2/"
    },
    "effect_entries": [
        {
        "effect": "Inflicts [regular damage]{mechanic:regular-damage}.",
        "short_effect": "Inflicts regular damage with no additional effect.",
        "language": {
            "name": "en",
            "url": "https://pokeapi.co/api/v2/language/9/"
        }
        }
    ],
    "effect_changes": [],
    "generation": {
        "name": "generation-i",
        "url": "https://pokeapi.co/api/v2/generation/1/"
    },
    "meta": {
        "ailment": {
        "name": "none",
        "url": "https://pokeapi.co/api/v2/move-ailment/0/"
        },
        "category": {
        "name": "damage",
        "url": "https://pokeapi.co/api/v2/move-category/0/"
        },
        "min_hits": null,
        "max_hits": null,
        "min_turns": null,
        "max_turns": null,
        "drain": 0,
        "healing": 0,
        "crit_rate": 0,
        "ailment_chance": 0,
        "flinch_chance": 0,
        "stat_chance": 0
    },
    "names": [
        {
        "name": "Pound",
        "language": {
            "name": "en",
            "url": "https://pokeapi.co/api/v2/language/9/"
        }
        }
    ],
    "past_values": [],
    "stat_changes": [],
    "super_contest_effect": {
        "url": "https://pokeapi.co/api/v2/super-contest-effect/5/"
    },
    "target": {
        "name": "selected-pokemon",
        "url": "https://pokeapi.co/api/v2/move-target/10/"
    },
    "type": {
        "name": "normal",
        "url": "https://pokeapi.co/api/v2/type/1/"
    },
    "learned_by_pokemon": [
        {
        "name": "clefairy",
        "url": "https://pokeapi.co/api/v2/pokemon/35/"
        }
    ],
    "flavor_text_entries": [
        {
        "flavor_text": "Pounds with fore­\nlegs or tail.",
        "language": {
            "url": "https://pokeapi.co/api/v2/language/9/",
            "name": "en"
        },
        "version_group": {
            "url": "https://pokeapi.co/api/v2/version-group/3/",
            "name": "gold-silver"
        }
        }
    ]
    }
    ```

4. Informações sobre uma determinada geração

    > https://pokeapi.co/api/v2/generation/1

    ```json
    {
    "id": 1,
    "name": "generation-i",
    "abilities": [],
    "main_region": {
        "name": "kanto",
        "url": "https://pokeapi.co/api/v2/region/1/"
    },
    "moves": [
        {
        "name": "pound",
        "url": "https://pokeapi.co/api/v2/move/1/"
        }
    ],
    "names": [
        {
        "name": "Generation I",
        "language": {
            "name": "de",
            "url": "https://pokeapi.co/api/v2/language/6/"
        }
        }
    ],
    "pokemon_species": [
        {
        "name": "bulbasaur",
        "url": "https://pokeapi.co/api/v2/pokemon-species/1/"
        }
    ],
    "types": [
        {
        "name": "normal",
        "url": "https://pokeapi.co/api/v2/type/1/"
        }
    ],
    "version_groups": [
        {
        "name": "red-blue",
        "url": "https://pokeapi.co/api/v2/version-group/1/"
        }
    ]
    }
    ```

### [Pokémon Go API](https://rapidapi.com/Chewett/api/pokemon-go1)

É uma api gratuita apneas com métodos HTTP GET. Esta API fornece diversas informações realacionadas aos Pokémons existente no jogo Pokémon GO, informações como status dos Pokémons, valor máximo de CP por Pokémon, informações sobre shinies e como obtê-los, Pokémons exclusivos de raid e mais.

Para utilizar essa API é necessário obter um token de acesso fornecido pelo site onde a API está hospedada. O header da requisição deve conter também os seguintes valores: "X-RapidAPI-Key": chave_fornecida e "X-RapidAPI-Host": pokemon-go1.p.rapidapi.com

#### Exemplos de requisição

1. Pokémons Shiny

    > https://pokemon-go1.p.rapidapi.com/shiny_pokemon.json

    ```json
    {
        1: {
            name: "Bulbasaur",
            found_wild: true,
            found_raid: false,
            found_egg: false,
            found_evolution: false,
            id: 1
        },
        2: {
            name: "Ivysaur",
            found_wild: false,
            found_raid: false,
            found_egg: false,
            found_evolution: true,
            id: 2
        }, ...
    }
    ```

2. Status dos Pokémons

    > https://pokemon-go1.p.rapidapi.com/pokemon_stats.json

    ```json
    [
        {
            base_stamina: "90",
            base_defense: "118",
            base_attack: "118",
            name: "Bulbasaur",
            id: 1
        },
        {
            base_stamina: "120",
            base_defense: "151",
            base_attack: "151",
            name: "Ivysaur",
            id: 2
        },
        {
            base_stamina: "160",
            base_defense: "198",
            base_attack: "198",
            name: "Venusaur",
            id: 3
        }, ...
    ]
    ```

3. Dados dos golpes

    > https://pokemon-go1.p.rapidapi.com/fast_moves.json

    ```json
    [
        {
            "stamina_loss_scaler": "0.01",
            "name": "Fury Cutter",
            "power": 3,
            "duration": 400,
            "energy_delta": 6,
            "type": "Bug"
        }, {
            "stamina_loss_scaler": "0.01",
            "name": "Bug Bite",
            "power": 5,
            "duration": 500,
            "energy_delta": 6,
            "type": "Bug"
        }, {
            "stamina_loss_scaler": "0.01",
            "name": "Bite",
            "power": 6,
            "duration": 500,
            "energy_delta": 4,
            "type": "Dark"
        }, ...
    ]
    ```

## Ideias de Relatórios

1. Quantidade Pokémons por geração
1. Tipos mais comuns de Pokémons
1. Ranking com base em cada um dos status base
1. Ranking com base na dificuldade de captura
1. Distribuição de movimentos por tipo
1. Ranking por CP máximo
1. Apresentação de status base, tipo, possíveis ataques, evoluções e outras informações relacionadas por Pokémon