package org.example.repository.Interfaces;

import java.util.List;

public interface Repository <T,V,Z,W>{
    List<T> list();
    T byId(Long id);
    void save(T nombre, V precio, Z fecha , W category);
    void delete(Long id);
    void Update(Long id,String newValue);
}
