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
import uel.bd.Bulbapedia.models.BaseStats;

import java.util.List;


@Repository
public class BaseStatsJdbcDAO implements DAO<BaseStats>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                  INSERT INTO base_stats(id_stats, id_pokemon, hp, attack, defense, sp_attack, sp_defense, speed)
                  VALUES(?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String DELETE_QUERY =
                    """
                        DELETE FROM base_stats
                        WHERE id_stats = ?
                    """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM base_stats
                WHERE id_stats = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM base_stats
            """;

    private static final String SELECT_BY_POKEMON_QUERY =
            """
                SELECT * FROM base_stats
                WHERE id_pokemon = ?
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE base_stats SET
                    hp = ?,
                    attack = ?,
                    defense = ?,
                    sp_attack = ?,
                    sp_defense = ?,
                    speed = ?
                WHERE id_stats = ?
            """;


    public BaseStatsJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int create(BaseStats bs) {
        try {
            return template.update(
                    INSERT_QUERY, bs.getIdStats(), bs.getIdPokemon(), bs.getHp(), bs.getAttack(), bs.getDefense(),
                    bs.getSpAttack(), bs.getSpDefense(), bs.getSpeed());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public BaseStats get(Object id_stats) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(BaseStats.class), id_stats);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public BaseStats getByPokemon(Object id_pokemon) {
        try {
            return template.queryForObject(
                    SELECT_BY_POKEMON_QUERY, BeanPropertyRowMapper.newInstance(BaseStats.class), id_pokemon
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<BaseStats> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(BaseStats.class));
        }  catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(BaseStats bs) {
        try {
            return template.update(UPDATE_QUERY, bs.getHp(), bs.getAttack(), bs.getDefense(), bs.getSpAttack(),
                    bs.getSpDefense(), bs.getSpeed(), bs.getIdStats());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_stats) {
        return template.update(DELETE_QUERY, id_stats);
    }
}
