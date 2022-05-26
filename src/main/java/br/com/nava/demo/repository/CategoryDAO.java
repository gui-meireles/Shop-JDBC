package br.com.nava.demo.repository;

import br.com.nava.demo.mapper.CategoryMapper;
import br.com.nava.demo.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CategoryDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<CategoryModel> listAll() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT cat_id, ");
        query.append("        name ");
        query.append(" FROM category ");

        return jdbcTemplate.query(query.toString(), new CategoryMapper());
    }

    public CategoryModel getById(Integer catId) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT cat_id, ");
        query.append("        name ");
        query.append(" FROM category ");
        query.append(" WHERE cat_id = :catId ");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("catId", catId);

        List<CategoryModel> category = namedParameterJdbcTemplate.query(query.toString(), params, new CategoryMapper());

        if (category != null && category.size() > 0) {
            return category.get(0);
        }
        return null;
    }

    public void save(CategoryModel category) {
        String query = "INSERT INTO category (name) VALUE (:name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", category.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, params, keyHolder);

        log.info("Generated key: " + keyHolder.getKey());
    }

    public void update(CategoryModel category, Integer catId) {
        String query = "UPDATE category SET name = :name WHERE cat_id = :catId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", category.getName());
        params.addValue("catId", catId);

        namedParameterJdbcTemplate.update(query, params);
    }

    public void delete(Integer catId) {
        String query = "DELETE FROM category WHERE cat_id = :catId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("catId", catId);

        namedParameterJdbcTemplate.update(query, params);
    }
}