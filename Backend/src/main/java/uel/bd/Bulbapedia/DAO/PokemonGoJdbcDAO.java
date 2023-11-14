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
import uel.bd.Bulbapedia.models.PokemonGo;

import java.util.List;

@Repository
public class PokemonGoJdbcDAO implements DAO<PokemonGo>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO pokemon_go(id_pokemon_go, id_pokemon, raid_exclusive, max_cp, buddy_distance, 
                candy_to_evolve)
                VALUES(?, ?, ?, ?, ?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM pokemon_go
                WHERE id_pokemon_go = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM pokemon_go
                WHERE id_pokemon_go = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM id_pokemon_go
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE pokemon_go
                SET
                    raid_exclusive = ?,
                    max_cp = ?,
                    buddy_cp = ?,
                    candy_to_evolve = ?
                WHERE id_pokemon_go = ?
            """;

    public PokemonGoJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(PokemonGo pokemon) {
        try {
            return template.update(
                    INSERT_QUERY, pokemon.getId_pokemon_go(), pokemon.getId_pokemon(), pokemon.getRaid_exclusive(),
                    pokemon.getMax_cp(), pokemon.getBuddy_distance(), pokemon.getCandy_to_evolve());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public PokemonGo get(Object id_pokemon_go) {
        try {
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(PokemonGo.class), id_pokemon_go);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<PokemonGo> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(PokemonGo.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(PokemonGo pokemon) {
        try {
            return template.update(
                    UPDATE_QUERY, pokemon.getRaid_exclusive(), pokemon.getMax_cp(), pokemon.getBuddy_distance(),
                    pokemon.getCandy_to_evolve(), pokemon.getId_pokemon_go());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_pokemon_go) {
        return template.update(DELETE_QUERY, id_pokemon_go);
    }
}
