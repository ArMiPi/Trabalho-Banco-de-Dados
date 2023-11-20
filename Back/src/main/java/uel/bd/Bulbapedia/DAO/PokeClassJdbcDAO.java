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
import uel.bd.Bulbapedia.models.PokeClass;

import java.util.List;


@Repository
public class PokeClassJdbcDAO implements DAO<PokeClass>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                  INSERT INTO class(id_class, type)
                  VALUES(?, ?)
            """;
    private static final String DELETE_QUERY =
                    """
                        DELETE FROM class
                        WHERE id_class = ?
                    """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM class
                WHERE id_class = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELEC * FROM class
            """;
    private static final String UPDATE_QUERY =
            """
                UPDATE class SET type = ? WHERE id_class = ?
            """;


    public PokeClassJdbcDAO(JdbcTemplate tmeplate) {
        this.template = tmeplate;
    }

    public int create(PokeClass pokeClass) {
        try {
            return template.update(INSERT_QUERY, pokeClass.getIdClass(), pokeClass.getType());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public PokeClass get(Object id_class) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(PokeClass.class), id_class);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<PokeClass> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(PokeClass.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(PokeClass pokeClass) {
        try {
            return template.update(UPDATE_QUERY, pokeClass.getType(), pokeClass.getIdClass());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_class) {
        return template.update(DELETE_QUERY, id_class);
    }
}
