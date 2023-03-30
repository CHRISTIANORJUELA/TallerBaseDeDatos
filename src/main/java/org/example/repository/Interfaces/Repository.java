package org.example.repository.Interfaces;

import java.util.List;

public interface Repository <T,V,Z>{
    List<T> list();
    T byId(Long id);
    void save(T nombre, V precio, Z fecha );
    void delete(Long id);
    void Update(Long id,String newValue);
}
