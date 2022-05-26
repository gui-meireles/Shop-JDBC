package br.com.nava.demo.repository;

import br.com.nava.demo.mapper.ShopMapper;
import br.com.nava.demo.model.ShopModel;
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
public class ShopDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ShopModel> listAll() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT shp_id, ");
        query.append("        name AS name, ");
        query.append("        address ");
        query.append(" FROM shop ");
        query.append(" ORDER BY shp_id ASC ");

        return jdbcTemplate.query(query.toString(), new ShopMapper());
    }

    public ShopModel getById(Integer shpId) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT shp_id, ");
        query.append("        name AS name, ");
        query.append("        address ");
        query.append(" FROM shop ");
        query.append(" WHERE shp_id = :shpId ");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shpId", shpId);

        List<ShopModel> shops = namedParameterJdbcTemplate.query(query.toString(), params, new ShopMapper());

        if (shops != null && shops.size() > 0) {
            return shops.get(0);
        }

        return null;
    }

    public void save(ShopModel shop) {
        String query = "INSERT INTO shop (name, address) VALUE (:name, :address)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", shop.getName());
        params.addValue("address", shop.getAddress());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(query, params, keyHolder);

        log.info("Generated key: " + keyHolder.getKey());
    }

    public void update(ShopModel shop, Integer shpId) {
        String query = "UPDATE shop SET name = :name, address = :address WHERE shp_id = :shpId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", shop.getName());
        params.addValue("address", shop.getAddress());
        params.addValue("shpId", shpId);

        namedParameterJdbcTemplate.update(query, params);
    }

    public void delete(Integer shpId) {
        String query = "DELETE FROM shop WHERE shp_id = :shpId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shpId", shpId);

        namedParameterJdbcTemplate.update(query, params);
    }
}
