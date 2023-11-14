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
import uel.bd.Bulbapedia.models.Generation;

import java.util.List;


@Repository
public class GenerationJdbcDAO implements DAO<Generation>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                  INSERT INTO generation(id_generation, gen_number, region)
                  VALUES(?, ?, ?)
            """;
    private static final String DELETE_QUERY =
                    """
                        DELETE FROM generation
                        WHERE id_generation = ?
                    """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM generation
                WHERE id_generation = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM generation
            """;
    private static final String UPDATE_QUERY =
            """
                UPDATE generation SET region = ? WHERE id_generation = ? 
            """;


    public GenerationJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int create(Generation gen) {
        try {
            return template.update(INSERT_QUERY, gen.getIdGeneration(), gen.getGenNumber(), gen.getRegion());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Generation get(Object id_generation) {
        try {
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(Generation.class), id_generation);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Generation> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(Generation.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(Generation gen) {
        try {
            return template.update(UPDATE_QUERY, gen.getRegion(), gen.getIdGeneration());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_generation) {
        return template.update(DELETE_QUERY, id_generation);
    }
}
