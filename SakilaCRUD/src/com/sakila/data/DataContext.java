package com.sakila.data;

import java.util.List;

// Interfaz genérica que define las operaciones básicas de CRUD (Crear, Leer, Actualizar, Eliminar)
public interface DataContext<T> {

    // Método para obtener todos los registros
    List<T> getAll();

    // Método para obtener un registro por ID
    T get(int id);

    // Método para crear un nuevo registro
    boolean post(T entity);

    // Método para actualizar un registro existente
    boolean put(int id, T entity);

    // Método para eliminar un registro
    boolean delete(int id);
}
