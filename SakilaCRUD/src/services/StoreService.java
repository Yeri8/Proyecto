
package services;

import com.sakila.models.Store;
import com.sakila.entities.StoreEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StoreService {

    private final StoreEntity storeEntity;

    public StoreService() {
        this.storeEntity = new StoreEntity();
    }

    // Obtener todas las tiendas con paginación
    public List<Store> getAllStores(int limit, int offset, Connection conn) {
        try {
            return storeEntity.getAll(limit, offset, conn);  // Verifica que getAll() maneje la paginación correctamente
        } catch (SQLException e) {
            e.printStackTrace();  // Considera usar un logger
            return null;  // Puede mejorarse con manejo de excepciones más específico
        }
    }

    // Obtener una tienda por ID
    public Store getStoreById(int storeId, Connection conn) {
        try {
            return storeEntity.getById(storeId, conn);  // Verifica que getById() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Considera usar un logger
            return null;  // Puede mejorarse con manejo de excepciones más específico
        }
    }

    // Agregar una tienda
    public boolean addStore(Store store, Connection conn) {
        try {
            return storeEntity.add(store, conn);  // Verifica que add() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Considera usar un logger
            return false;  // Puede mejorarse con manejo de excepciones más específico
        }
    }

    // Actualizar una tienda
    public boolean updateStore(int storeId, Store store, Connection conn) {
        try {
            return storeEntity.update(storeId, store, conn);  // Verifica que update() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Considera usar un logger
            return false;  // Puede mejorarse con manejo de excepciones más específico
        }
    }

    // Eliminar una tienda
    public boolean deleteStore(int storeId, Connection conn) {
        try {
            return storeEntity.delete(storeId, conn);  // Verifica que delete() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Considera usar un logger
            return false;  // Puede mejorarse con manejo de excepciones más específico
        }
    }
}


