
package com.sakila.data;

import java.util.List;

public interface iDataPost<T> {
    void post(T entity);  // Crear (Insertar)
    List<T> get();        // Obtener todos los registros
    T getById(int id);    // Obtener un registro por su ID
    void put(T entity);   // Actualizar
    void delete(int id);  // Eliminar (Marcar como inactivo)
}
