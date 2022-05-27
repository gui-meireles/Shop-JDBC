package br.com.nava.demo.service;

import br.com.nava.demo.exceptions.BadRequestException;
import br.com.nava.demo.exceptions.NotFoundException;

import java.util.List;

public interface ICrudService<T> {

    List<T> listAll();

    T getById(Integer id) throws NotFoundException;

    void save(T model) throws BadRequestException;

    void update(T model, Integer id) throws BadRequestException;

    void delete(Integer id) throws NotFoundException;
}
