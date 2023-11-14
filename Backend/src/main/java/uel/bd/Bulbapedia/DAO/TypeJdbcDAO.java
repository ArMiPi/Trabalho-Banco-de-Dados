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
import uel.bd.Bulbapedia.models.Type;

import java.util.List;


@Repository
public class TypeJdbcDAO implements DAO<Type>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                  INSERT INTO type(id_type, name)
                  VALUES(?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM type
                WHERE id_type = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM type
                WHERE id_type = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM type
            """;
    private static final String UPDATE_QUERY =
            """
                UPDATE type SET name = ? WHERE id_type = ?
            """;


    public TypeJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(Type type) {
        try {
            return template.update(INSERT_QUERY, type.getIdType(), type.getName());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Type get(Object id_type) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(Type.class), id_type);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Type> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(Type.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(Type type) {
        try {
            return template.update(UPDATE_QUERY, type.getName(), type.getIdType());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_type) {
        return template.update(DELETE_QUERY, id_type);
    }
}
