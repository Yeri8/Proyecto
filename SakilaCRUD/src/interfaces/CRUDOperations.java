
package interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUDOperations<T> {

    ArrayList<T> getAll(int limit, int offset, Connection conn) throws SQLException;

    T getById(int id, Connection conn) throws SQLException;

    boolean add(T t, Connection conn) throws SQLException;

    boolean update(int id, T t, Connection conn) throws SQLException;

    boolean delete(int id, Connection conn) throws SQLException;
}
