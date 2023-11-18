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
import uel.bd.Bulbapedia.models.BaseGoStats;

import java.util.List;

@Repository
public class BaseGoStatsJdbcDAO implements DAO<BaseGoStats> {
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO base_go_stats(id_stats_go, id_pokemon, stamina, defense, attack)
                VALUES (?, ?, ?, ?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM base_go_stats
                WHERE id_stats_go = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM base_stats_go
                WHERE id_stats_go = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM base_stats_go
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE base_stats_go
                SET
                    stamina = ?,
                    defense = ?,
                    attack = ?
                WHERE  = ?
            """;

    public BaseGoStatsJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(BaseGoStats bsgo) {
        try {
            return template.update(
                    INSERT_QUERY, bsgo.getId_stats_go(), bsgo.getId_pokemon(), bsgo.getStamina(), bsgo.getDefense(),
                    bsgo.getAttack());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public BaseGoStats get(Object id_stats_go) {
        try {
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(BaseGoStats.class), id_stats_go);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<BaseGoStats> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(BaseGoStats.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(BaseGoStats bsgo) {
        try {
            return template.update(
                    UPDATE_QUERY, bsgo.getStamina(), bsgo.getDefense(), bsgo.getAttack(), bsgo.getId_stats_go());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_stats_go) {
        return template.update(DELETE_QUERY, id_stats_go);
    }
}
