package br.com.nava.demo.service;

import java.util.List;

public interface ICrudService<T> {

    List<T> listAll();

    T getById(Integer id);

    void save(T model);

    void update(T model, Integer id);

    void delete(Integer id);
}
