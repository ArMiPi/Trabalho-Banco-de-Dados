package uel.bd.Bulbapedia.DAO;

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


@Repository
public class PokemonJdbcDAO implements DAO<Pokemon>{
    @Autowired
    private final JdbcTemplate template;
    private static final String INSERT_QUERY =
            """
                  INSERT INTO pokemon(id_pokedex, name, height, weight, capture_rate, rarity, sprite, id_generation, 
                  evolves_from)
                  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)
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


    public PokemonJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int create(Pokemon pokemon) {
        try {
            return template.update(INSERT_QUERY, pokemon.getIdPokedex(), pokemon.getName(), pokemon.getHeight(),
                    pokemon.getWeight(), pokemon.getCaptureRate(), pokemon.getRarity(), pokemon.getSprite(),
                    pokemon.getIdGeneration(), pokemon.getEvolvesFrom());
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

    public int delete(Object id_pokedex) {
        return template.update(DELETE_QUERY, id_pokedex);
    }
}
