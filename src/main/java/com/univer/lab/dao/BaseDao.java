package com.univer.lab.dao;

import java.util.List;

public interface BaseDao<T> {

    T findById(Long id);

    List<T> findAll();

    Long save(T name);

    void deleteById(Long id);
}
