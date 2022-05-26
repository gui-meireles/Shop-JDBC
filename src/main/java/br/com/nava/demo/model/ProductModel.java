package br.com.nava.demo.model;

import lombok.*;

@Data
public class ProductModel {
    private Integer prdId;
    private String name;
    private String description;
    private CategoryModel category;
}
