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
import uel.bd.Bulbapedia.models.LearnMove;

import java.util.List;

@Repository
public class LearnMoveJdbcDAO implements DAO<LearnMove>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO learn_move(id_pokemon, id_move)
                VALUES(?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM lean_move
                WHERE id_pokemon = ? AND id_move = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM learn_move
                WHERE id_pokemon = ? AND id_move = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM learn_move
            """;


    public LearnMoveJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(LearnMove learnMove) {
        try {
            return template.update(INSERT_QUERY, learnMove.getId_pokemon(), learnMove.getId_move());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public LearnMove get(Object key) {
        try {
            LearnMove learnMove = (LearnMove) key;
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(LearnMove.class), learnMove.getId_pokemon(),
                    learnMove.getId_move());
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<LearnMove> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(LearnMove.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(LearnMove learnMove) {
        // Não utilizada
        return 0;
    }

    public int delete(Object key) {
        LearnMove learnMove = (LearnMove) key;
        return template.update(DELETE_QUERY, learnMove.getId_pokemon(), learnMove.getId_move());
    }
}
