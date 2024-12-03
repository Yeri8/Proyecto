
package com.sakila.data;

import java.util.List;

// Interfaz para las operaciones básicas de acceso a datos, con un enfoque más simplificado
public interface iDataPost<T> {

    // Método para crear o insertar un nuevo registro
    void post(T entity);

    // Método para obtener todos los registros
    List<T> get();

    // Método para obtener un registro por su ID
    T getById(int id);

    // Método para actualizar un registro existente
    void put(T entity);

    // Método para eliminar un registro (o marcarlo como inactivo)
    void delete(int id);
}
