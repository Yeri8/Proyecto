
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASSWORD = "Enmanuel0712";

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
