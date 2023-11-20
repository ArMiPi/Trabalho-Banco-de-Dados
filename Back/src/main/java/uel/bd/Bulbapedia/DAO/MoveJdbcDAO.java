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
import uel.bd.Bulbapedia.models.Move;

import java.util.List;


@Repository
public class MoveJdbcDAO implements DAO<Move>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                  INSERT INTO move(id_move, name, accuracy, id_class, power, pp, id_type)
                  VALUES(?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM move
                WHERE id_move = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM move
                WHERE id_move = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM move
            """;
    private static final String UPDATE_QUERY =
            """
                UPDATE move SET
                    name = ?,
                    accuracy = ?,
                    id_class = ?,
                    power = ?,
                    pp = ?,
                    id_type = ?
                WHERE id_move = ?
            """;


    public MoveJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int create(Move move) {
        try {
            return template.update(INSERT_QUERY, move.getIdMove(), move.getName(), move.getAccuracy(),
                    move.getIdClass(), move.getPower(), move.getPp(), move.getIdType());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Move get(Object id_move) {
        try {
            return template.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(Move.class), id_move);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Move> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(Move.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(Move move) {
        try {
            return template.update(UPDATE_QUERY, move.getName(), move.getAccuracy(), move.getIdClass(), move.getPower(),
                    move.getPp(), move.getIdType(), move.getIdMove());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object id_move) {
        return template.update(DELETE_QUERY, id_move);
    }
}
