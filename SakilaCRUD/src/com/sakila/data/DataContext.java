
package com.sakila.data;

import java.util.List;

public interface DataContext<T> {
    // Obtener todos los registros
    List<T> getAll();

    // Obtener un registro por ID
    T get(int id);

    // Crear un nuevo registro
    boolean post(T entity);

    // Actualizar un registro existente
    boolean put(int id, T entity);

    // Eliminar un registro
    boolean delete(int id);
}
