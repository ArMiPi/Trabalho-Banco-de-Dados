package uel.bd.Bulbapedia.DAO;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.web.server.ResponseStatusException;
import uel.bd.Bulbapedia.models.Pokemon;

import java.util.List;
import java.util.Map;


@Repository
public class PokemonJdbcDAO implements DAO<Pokemon>{
    @Autowired
    private final JdbcTemplate template;
    private static final String INSERT_QUERY =
            """
                  INSERT INTO pokemon(id_pokedex, name, height, weight, capture_rate, rarity, sprite, id_generation)
                  VALUES(?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String DELETE_QUERY =
            """
                DELETE FROM pokemon
                WHERE id_pokedex = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM pokemon
                WHERE id_pokedex = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM pokemon
            """;

    private static final String GET_POKEMON_GENERAL_DATA =
            """
                SELECT
                	pk.*,
                	tp.name AS type_name, tp.slot,
                	bs.hp, bs.attack, bs.defense, bs.sp_attack, bs.sp_defense, bs.speed,
                	hab.name AS ability_name, hab.is_hidden,
                	pk_go.raid_exclusive, pk_go.max_cp, pk_go.buddy_distance, pk_go.candy_to_evolve,
                	shiny.egg, shiny.raid, shiny.wild, shiny.sprite AS sprite_shiny,
                	bs_go.stamina AS go_stamina, bs_go.defense AS go_defense, bs_go.attack AS go_attack
                FROM pokemon AS pk
                LEFT JOIN (
                	SELECT iot.id_pokemon, type.name, iot.slot
                	FROM is_of_type AS iot
                	JOIN type ON iot.id_type = type.id_type
                ) AS tp ON pk.id_pokedex = tp.id_pokemon
                LEFT JOIN base_stats AS bs ON pk.id_pokedex = bs.id_pokemon
                LEFT JOIN (
                	SELECT ha.id_pokemon, ab.name, ha.is_hidden
                	FROM have_ability AS ha
                	LEFT JOIN ability AS ab ON ha.id_ability = ab.id_ability
                ) AS hab ON pk.id_pokedex = hab.id_pokemon
                LEFT JOIN pokemon_go AS pk_go ON pk.id_pokedex = pk_go.id_pokemon
                LEFT JOIN shiny ON pk.id_pokedex = shiny.id_pokemon
                LEFT JOIN base_go_stats AS bs_go ON pk.id_pokedex = bs_go.id_pokemon
                WHERE id_pokedex = ?    
            """;
    private static final String UPDATE_QUERY =
            """
                UPDATE pokemon SET
                    name = ?,
                    height = ?,
                    weight = ?,
                    capture_rate = ?,
                    rarity = ?,
                    sprite = ?,
                    id_generation = ?,
                    evolves_from = ?
                WHERE id_pokedex = ?
            """;

    private static final String UPDATE_EVOLUTION =
            """
                UPDATE pokemon SET evolves_from = ? WHERE id_pokedex = ?
            """;


    public PokemonJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int create(Pokemon pokemon) {
        try {
            return template.update(INSERT_QUERY, pokemon.getIdPokedex(), pokemon.getName(), pokemon.getHeight(),
                    pokemon.getWeight(), pokemon.getCaptureRate(), pokemon.getRarity(), pokemon.getSprite(),
                    pokemon.getIdGeneration());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Pokemon get(Object id_pokedex) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(Pokemon.class), id_pokedex);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Map<String, Object>> getPokemonGeneralData(Object id_pokedex) {
        try {
            return template.queryForList(GET_POKEMON_GENERAL_DATA, id_pokedex);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Pokemon> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(Pokemon.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(Pokemon pokemon) {
        try {
            return template.update(UPDATE_QUERY, pokemon.getName(), pokemon.getHeight(), pokemon.getWeight(),
                    pokemon.getCaptureRate(), pokemon.getRarity(), pokemon.getSprite(), pokemon.getIdGeneration(),
                    pokemon.getEvolvesFrom(), pokemon.getIdPokedex());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int update(Integer update, int id_pokedex) {
        try {
            return template.update(UPDATE_EVOLUTION, update, id_pokedex);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_pokedex) {
        return template.update(DELETE_QUERY, id_pokedex);
    }
}
