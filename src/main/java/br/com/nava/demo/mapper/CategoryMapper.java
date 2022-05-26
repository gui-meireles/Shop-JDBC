package br.com.nava.demo.mapper;

import br.com.nava.demo.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper extends OptimizedResultSetMapper<CategoryModel> {

    @Override
    public CategoryModel map(ResultSet rs) throws SQLException {
        CategoryModel category = new CategoryModel();
        category.setCatId(getInteger(rs, "cat_id"));
        category.setName(getString(rs, "name"));

        return category;
    }
}
