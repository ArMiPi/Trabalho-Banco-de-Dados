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
import uel.bd.Bulbapedia.models.Shiny;

import java.util.List;

@Repository
public class ShinyJdbcDAO implements DAO<Shiny>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO shiny(id_shiny, id_pokemon, egg, raid, wild, sprite)
                VALUES(?, ?, ?, ?, ?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM shiny
                WHERE id_shiny = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM shiny
                WHERE id_shiny = ?
            """;
    private static final String SELECT_BY_POKEMON_QUERY =
            """
                SELECT * FROM shiny
                WHERE id_pokemon = ? 
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM shiny
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE shiny
                SET
                    egg = ?,
                    raid = ?,
                    wild = ?,
                    sprite = ?
                WHERE id_shiny = ?
            """;

    public ShinyJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(Shiny shiny) {
        try {
            return template.update(
                    INSERT_QUERY, shiny.getId_shiny(), shiny.getId_pokemon(), shiny.getEgg(), shiny.getRaid(),
                    shiny.getWild(), shiny.getSprite());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Shiny get(Object id_shiny) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(Shiny.class), id_shiny);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public Shiny getByPokemon(Object id_pokemon) {
        try {
            return template.queryForObject(
                    SELECT_BY_POKEMON_QUERY, BeanPropertyRowMapper.newInstance(Shiny.class), id_pokemon
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Shiny> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(Shiny.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(Shiny shiny) {
        try {
            return template.update(
                    UPDATE_QUERY, shiny.getEgg(), shiny.getRaid(), shiny.getWild(), shiny.getSprite(),
                    shiny.getId_shiny());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_shiny) {
        return template.update(DELETE_QUERY, id_shiny);
    }
}
