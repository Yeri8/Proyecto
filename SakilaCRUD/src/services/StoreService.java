
package services;

import com.sakila.models.Store;
import com.sakila.entities.StoreEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StoreService {

    // Instancia de StoreEntity que maneja las operaciones de la tienda en la base de datos.
    private final StoreEntity storeEntity;

    // Constructor que inicializa la instancia de StoreEntity
    public StoreService() {
        this.storeEntity = new StoreEntity();
    }

    // Obtener todas las tiendas con paginación
    public List<Store> getAllStores(int limit, int offset, Connection conn) {
        try {
            // Llamada al método getAll() de StoreEntity que devuelve las tiendas con limitación y desplazamiento
            return storeEntity.getAll(limit, offset, conn);  // Verifica que getAll() maneje la paginación correctamente
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace en caso de error, pero se recomienda un logger para producción
            return null;  // Retorna null en caso de error, podría mejorarse con un manejo de excepciones más específico
        }
    }

    // Obtener una tienda por ID
    public Store getStoreById(int storeId, Connection conn) {
        try {
            // Llamada al método getById() de StoreEntity que devuelve una tienda por su ID
            return storeEntity.getById(storeId, conn);  // Verifica que getById() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace en caso de error
            return null;  // Retorna null en caso de error, sería mejor usar un manejo de excepciones más específico
        }
    }

    // Agregar una tienda
    public boolean addStore(Store store, Connection conn) {
        try {
            // Llamada al método add() de StoreEntity que agrega una nueva tienda a la base de datos
            return storeEntity.add(store, conn);  // Verifica que add() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace en caso de error
            return false;  // Retorna false en caso de error, podría mejorarse con manejo de excepciones más específico
        }
    }

    // Actualizar una tienda
    public boolean updateStore(int storeId, Store store, Connection conn) {
        try {
            // Llamada al método update() de StoreEntity que actualiza los datos de una tienda existente
            return storeEntity.update(storeId, store, conn);  // Verifica que update() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace en caso de error
            return false;  // Retorna false en caso de error, podría mejorarse con manejo de excepciones más específico
        }
    }

    // Eliminar una tienda
    public boolean deleteStore(int storeId, Connection conn) {
        try {
            // Llamada al método delete() de StoreEntity que elimina una tienda por su ID
            return storeEntity.delete(storeId, conn);  // Verifica que delete() maneje correctamente la conexión
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el stack trace en caso de error
            return false;  // Retorna false en caso de error, podría mejorarse con manejo de excepciones más específico
        }
    }
}


