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
import uel.bd.Bulbapedia.models.TypeRelations;

import java.util.List;

@Repository
public class TypeRelationsJdbcDAO implements DAO<TypeRelations>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO type_relations(id_attacker, id_defender, multiplier)
                VALUES(?, ?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM type_relations
                WHERE id_attacker = ? AND id_defender = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM type_relations
                WHERE id_attacker = ? AND id_defender = ?
            """;
    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM type_relations
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE type_relations
                SET
                    multiplier
                WHERE id_attacker = ? AND id_defender = ?
            """;

    public TypeRelationsJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(TypeRelations typeRelations) {
        try {
            return template.update(
                    INSERT_QUERY, typeRelations.getId_attacker(), typeRelations.getId_defender(),
                    typeRelations.getMultiplier());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public TypeRelations get(Object key) {
        try {
            TypeRelations typeRelations = (TypeRelations) key;
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(TypeRelations.class),
                    typeRelations.getId_attacker(), typeRelations.getId_defender());
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<TypeRelations> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(TypeRelations.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(TypeRelations typeRelations) {
        try {
            return template.update(
                    UPDATE_QUERY, typeRelations.getMultiplier(), typeRelations.getId_attacker(),
                    typeRelations.getId_defender());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object key) {
        TypeRelations typeRelations = (TypeRelations) key;
        return template.update(DELETE_QUERY, typeRelations.getId_attacker(), typeRelations.getId_defender());
    }
}
