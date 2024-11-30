
package com.sakila.controllers;

import com.sakila.entities.StoreEntity;
import com.sakila.models.Store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StoreController {
    private final StoreEntity storeEntity;

    public StoreController(StoreEntity storeEntity) {
        this.storeEntity = storeEntity;
    }

    public boolean addStore(Store store, Connection conn) {
        try {
            return storeEntity.add(store, conn); // Llamada al método de la entidad
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStore(int storeId, Store store, Connection conn) {
        try {
            return storeEntity.update(storeId, store, conn); // Llamada al método de la entidad
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStore(int storeId, Connection conn) {
        try {
            return storeEntity.delete(storeId, conn); // Llamada al método de la entidad
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Store> getAllStores(int limit, int offset, Connection conn) {
        try {
            return storeEntity.getAll(limit, offset, conn); // Llamada al método de la entidad
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Store getStoreById(int storeId, Connection conn) {
        try {
            return storeEntity.getById(storeId, conn); // Llamada al método de la entidad
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

