package uel.bd.Bulbapedia.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import uel.bd.Bulbapedia.models.IsOfType;

import java.util.List;

@Repository
public class IsOfTypeJdbcDAO implements DAO<IsOfType> {
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO is_of_type(id_pokemon, id_type, slot)
                VALUES (?, ?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM is_of_type
                WHERE id_pokemon = ? AND id_type = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM is_of_type
                WHERE id_pokemon = ? AND id_type = ?
            """;
    private static final String SELECT_BY_POKEMON_QUERY =
            """
                SELECT * FROM is_of_type
                WHERE id_pokemon = ?        
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM is_of_type
            """;

    public IsOfTypeJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(IsOfType ofType) {
        try {
            return template.update(INSERT_QUERY, ofType.getId_pokemon(), ofType.getId_type(), ofType.getSlot());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public IsOfType get(Object key) {
        try {
            IsOfType ofType = (IsOfType) key;
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(IsOfType.class), ofType.getId_pokemon(),
                    ofType.getId_type());
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<IsOfType> getByPokemon(Object key) {
        try {
            return template.query(
                    SELECT_BY_POKEMON_QUERY, BeanPropertyRowMapper.newInstance(IsOfType.class), key);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<IsOfType> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(IsOfType.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(IsOfType ofType) {
        // Método não utilizado
        return 0;
    }

    public int delete(Object key) {
        IsOfType ofType = (IsOfType) key;
        return template.update(DELETE_QUERY, ofType.getId_pokemon(), ofType.getId_type());
    }
}
