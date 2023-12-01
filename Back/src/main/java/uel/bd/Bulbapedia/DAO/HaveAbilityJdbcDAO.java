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
import uel.bd.Bulbapedia.models.HaveAbility;

import java.util.List;

@Repository
public class HaveAbilityJdbcDAO implements DAO<HaveAbility>{
    @Autowired
    private final JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                INSERT INTO have_ability(id_pokemon, id_ability, is_hidden)
                VALUES (?, ?, ?)
            """;
    private static final String DELETE_QUERY =
            """
                DELETE FROM have_ability
                WHERE id_pokemon = ? AND id_ability = ?
            """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM have_ability
                WHERE id_pokemon = ? AND id_ability = ?
            """;

    private static final String SELECT_BY_POKEMON_QUERY =
            """
                SELECT * FROM have_ability
                WHERE id_pokemon = ?
            """;

    private static final String SELECT_QUERY_ALL =
            """
                SELECT * FROM have_ability
            """;

    private static final String UPDATE_QUERY =
            """
                UPDATE have_ability SET is_hidden = ? WHERE id_pokemon = ? AND id_ability = ?
            """;

    public HaveAbilityJdbcDAO(JdbcTemplate template) { this.template = template; }

    public int create(HaveAbility haveAbility) {
        try {
            return template.update(
                    INSERT_QUERY, haveAbility.getId_pokemon(), haveAbility.getId_ability(), haveAbility.getIs_hidden());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public HaveAbility get(Object key) {
        try {
            HaveAbility haveAbility = (HaveAbility) key;
            return template.queryForObject(
                    SELECT_QUERY, BeanPropertyRowMapper.newInstance(HaveAbility.class), haveAbility.getId_pokemon(),
                    haveAbility.getId_ability());
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<HaveAbility> getByPokemon(Object key) {
        try {
            return template.query(
                    SELECT_BY_POKEMON_QUERY, BeanPropertyRowMapper.newInstance(HaveAbility.class), key
            );
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<HaveAbility> getAll() {
        try {
            return template.query(SELECT_QUERY_ALL, BeanPropertyRowMapper.newInstance(HaveAbility.class));
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public int update(HaveAbility haveAbility) {
        try {
            return template.update(
                    UPDATE_QUERY, haveAbility.getIs_hidden(), haveAbility.getId_pokemon(), haveAbility.getId_ability());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public int delete(Object key) {
        HaveAbility haveAbility = (HaveAbility) key;
        return template.update(DELETE_QUERY, haveAbility.getId_pokemon(), haveAbility.getId_ability());
    }
}
