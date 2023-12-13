package com.alura.aluraAPI.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrud<T> {
    T insert(T t);
    Boolean delete(Long id);
    T findByName(String name);
    T updateByName(T t, String name);
    Page<T> paginate(Pageable pageable);
}
