package br.com.nava.demo.mapper;

import br.com.nava.demo.model.CategoryModel;
import br.com.nava.demo.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper extends OptimizedResultSetMapper<ProductModel> {


    @Override
    public ProductModel map(ResultSet rs) throws SQLException {
        ProductModel product = new ProductModel();
        product.setPrdId(getInteger(rs, "prd_id"));
        product.setName(getString(rs, "name"));
        product.setDescription(getString(rs, "description"));

        CategoryModel category = new CategoryModel();
        category.setCatId(getInteger(rs, "cat_id"));
        category.setName(getString(rs, "cat_name"));

        product.setCategory(category);

        return product;
    }
}
