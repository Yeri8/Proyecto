
package reports;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExportService {

    /**
     * Exporta los datos a un archivo CSV.
     * 
     * @param headers Lista de encabezados que se escribirán en la primera fila del CSV.
     * @param data Lista de listas, donde cada lista interna representa una fila de datos en el CSV.
     * @param filePath Ruta del archivo donde se guardará el CSV.
     * @return true si el archivo CSV se creó correctamente, false si ocurrió un error.
     */
    public boolean exportToCSV(List<String> headers, List<List<String>> data, String filePath) {
        // Utiliza FileWriter para escribir en el archivo CSV
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribe los encabezados, uniéndolos con comas y agregando un salto de línea al final
            writer.write(String.join(",", headers) + "\n");
            
            // Escribe los datos fila por fila, uniéndolos con comas y agregando un salto de línea después de cada fila
            for (List<String> row : data) {
                writer.write(String.join(",", row) + "\n");
            }
            // Retorna true si todo se escribió correctamente
            return true;
        } catch (IOException e) {
            // Si ocurre una excepción (por ejemplo, error al escribir en el archivo), se captura y se imprime el error
            e.printStackTrace();
            return false; // Retorna false si hubo un error
        }
    }
}

