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
import uel.bd.Bulbapedia.models.Ability;

import java.util.List;


@Repository
public class AbilityJdbcDAO implements DAO<Ability>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                  INSERT INTO ability(id_ability, name)
                  VALUES(?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM ability
                WHERE id_ability = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM ability
                WHERE id_ability = ?
            """;

    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM ability
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE ability SET name = ? WHERE id_ability = ?
            """;


    public AbilityJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int create(Ability ability) {
        try {
            return template.update(INSERT_QUERY, ability.getIdAbility(), ability.getName());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Ability get(Object id_ability) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(Ability.class), id_ability);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Ability> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(Ability.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(Ability ability) {
        try {
            return template.update(UPDATE_QUERY, ability.getName(), ability.getIdAbility());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_ability) {
        return template.update(DELETE_QUERY, id_ability);
    }
}
