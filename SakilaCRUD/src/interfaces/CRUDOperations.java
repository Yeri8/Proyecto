package interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUDOperations<T> {

    /**
     * Recupera todos los elementos de la base de datos con paginación.
     * 
     * @param limit El número máximo de registros a recuperar.
     * @param offset El desplazamiento desde donde comenzar a recuperar los registros.
     * @param conn La conexión a la base de datos.
     * @return Una lista de elementos recuperados.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    ArrayList<T> getAll(int limit, int offset, Connection conn) throws SQLException;

    /**
     * Recupera un elemento de la base de datos utilizando su ID.
     * 
     * @param id El ID del elemento a recuperar.
     * @param conn La conexión a la base de datos.
     * @return El objeto correspondiente al ID, o null si no se encuentra.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    T getById(int id, Connection conn) throws SQLException;

    /**
     * Agrega un nuevo elemento a la base de datos.
     * 
     * @param t El objeto a agregar a la base de datos.
     * @param conn La conexión a la base de datos.
     * @return true si el objeto se agregó correctamente, false si hubo un error.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    boolean add(T t, Connection conn) throws SQLException;

    /**
     * Actualiza un elemento existente en la base de datos.
     * 
     * @param id El ID del elemento a actualizar.
     * @param t El objeto con los nuevos valores para actualizar.
     * @param conn La conexión a la base de datos.
     * @return true si el elemento se actualizó correctamente, false si hubo un error.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    boolean update(int id, T t, Connection conn) throws SQLException;

    /**
     * Elimina un elemento de la base de datos utilizando su ID.
     * 
     * @param id El ID del elemento a eliminar.
     * @param conn La conexión a la base de datos.
     * @return true si el elemento se eliminó correctamente, false si hubo un error.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
    boolean delete(int id, Connection conn) throws SQLException;
}
