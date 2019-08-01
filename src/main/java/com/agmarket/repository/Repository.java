package com.agmarket.repository;

import java.util.List;

public interface Repository<T> {
    T save(T t);

    boolean remove(T t);

    boolean removeById(Long id);

    void removeAll();

    int count();

    List<T> getAll();

    T get(Long id);
}
