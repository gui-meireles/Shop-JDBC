package br.com.nava.demo.repository;

import br.com.nava.demo.mapper.ProductMapper;
import br.com.nava.demo.model.ProductModel;
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
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ProductModel> listAll() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT p.prd_id, ");
        query.append("        p.name, ");
        query.append("        p.description, ");
        query.append("        p.cat_id, ");
        query.append("        c.name AS cat_name ");
        query.append(" FROM product p ");
        query.append(" INNER JOIN category c ");
        query.append(" ON p.cat_id = c.cat_id ");
        query.append(" ORDER BY p.prd_id ASC ");

        return jdbcTemplate.query(query.toString(), new ProductMapper());
    }

    public ProductModel getById(Integer prdId) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT p.prd_id, ");
        query.append("        p.name, ");
        query.append("        p.description, ");
        query.append("        p.cat_id, ");
        query.append("        c.name AS cat_name ");
        query.append(" FROM product p ");
        query.append(" INNER JOIN category c ");
        query.append(" ON p.cat_id = c.cat_id ");
        query.append(" WHERE prd_id = :prdId ");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("prdId", prdId);

        List<ProductModel> product = namedParameterJdbcTemplate.query(query.toString(), params, new ProductMapper());

        if (product != null && product.size() > 0) {
            return product.get(0);
        }
        return null;
    }

    public void save(ProductModel product) {
        String query = "INSERT INTO product (name, description, cat_id) VALUE (:name, :description, :catId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("description", product.getDescription());
        params.addValue("catId", product.getCategory().getCatId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, params, keyHolder);

        log.info("Generated key: " + keyHolder.getKey());
    }

    public void update(ProductModel product, Integer prdId) {
        String query = "UPDATE product SET name = :name, description = :description, cat_id = :catId WHERE prd_id = :prdId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("description", product.getDescription());
        params.addValue("catId", product.getCategory().getCatId());
        params.addValue("prdId", prdId);

        namedParameterJdbcTemplate.update(query, params);
    }

    public void delete(Integer prdId) {
        String query = "DELETE FROM product WHERE prd_id = :prdId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("prdId", prdId);

        namedParameterJdbcTemplate.update(query, params);
    }
}