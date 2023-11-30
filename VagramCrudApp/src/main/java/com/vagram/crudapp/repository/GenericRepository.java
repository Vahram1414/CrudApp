package com.vagram.crudapp.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);

    List<T> getAll();

    T save(T t) throws IOException;

    T update(T t) throws IOException;

    boolean deleteById(ID id);
}
