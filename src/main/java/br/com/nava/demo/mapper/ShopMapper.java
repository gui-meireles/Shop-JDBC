package br.com.nava.demo.mapper;

import br.com.nava.demo.model.ShopModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopMapper extends OptimizedResultSetMapper<ShopModel> {

    @Override
    public ShopModel map(ResultSet rs) throws SQLException {
        ShopModel shop = new ShopModel();
        shop.setShpId(getInteger(rs, "shp_id"));
        shop.setName(getString(rs, "name"));
        shop.setAddress(getString(rs, "address"));

        return shop;
    }
}
