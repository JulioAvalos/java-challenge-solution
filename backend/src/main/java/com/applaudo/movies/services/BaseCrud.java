package com.applaudo.movies.services;

import java.util.List;

public interface BaseCrud<T> {
    List<T> listAll();
    T add(T obj);
    T modify(Long id, T obj);
    T getById(Long id);
    boolean remove(Long id);
}
