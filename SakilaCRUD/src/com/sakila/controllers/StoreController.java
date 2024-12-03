package com.sakila.controllers;

import com.sakila.entities.StoreEntity;
import com.sakila.models.Store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StoreController {
    // Instancia de StoreEntity para interactuar con los datos de las tiendas
    private final StoreEntity storeEntity;

    // Constructor que inicializa la entidad StoreEntity
    public StoreController(StoreEntity storeEntity) {
        this.storeEntity = storeEntity;
    }

    // Método para agregar una nueva tienda
    public boolean addStore(Store store, Connection conn) {
        try {
            // Llama al método add de StoreEntity para agregar la tienda a la base de datos
            return storeEntity.add(store, conn);
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error si ocurre una excepción SQL
            return false;  // Retorna false si ocurre un error
        }
    }

    // Método para actualizar una tienda existente
    public boolean updateStore(int storeId, Store store, Connection conn) {
        try {
            // Llama al método update de StoreEntity para actualizar la tienda con el ID proporcionado
            return storeEntity.update(storeId, store, conn);
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error si ocurre una excepción SQL
            return false;  // Retorna false si ocurre un error
        }
    }

    // Método para eliminar una tienda por ID
    public boolean deleteStore(int storeId, Connection conn) {
        try {
            // Llama al método delete de StoreEntity para eliminar la tienda con el ID proporcionado
            return storeEntity.delete(storeId, conn);
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error si ocurre una excepción SQL
            return false;  // Retorna false si ocurre un error
        }
    }

    // Método para obtener todas las tiendas con paginación
    public List<Store> getAllStores(int limit, int offset, Connection conn) {
        try {
            // Llama al método getAll de StoreEntity para obtener todas las tiendas con los límites y desplazamientos indicados
            return storeEntity.getAll(limit, offset, conn);
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error si ocurre una excepción SQL
            return null;  // Retorna null si ocurre un error
        }
    }

    // Método para obtener una tienda por ID
    public Store getStoreById(int storeId, Connection conn) {
        try {
            // Llama al método getById de StoreEntity para obtener la tienda con el ID proporcionado
            return storeEntity.getById(storeId, conn);
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error si ocurre una excepción SQL
            return null;  // Retorna null si ocurre un error
        }
    }
}

