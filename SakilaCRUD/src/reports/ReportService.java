package reports;

import com.sakila.models.Inventory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReportService {

    /**
     * Genera un reporte de inventario como una cadena de texto.
     * 
     * @param inventoryList Lista de objetos Inventory para generar el reporte.
     * @return El reporte como String.
     */
    public String generateInventoryReport(List<Inventory> inventoryList) {
        // Si la lista está vacía o es nula, devuelve un mensaje indicando que no hay datos para el reporte.
        if (inventoryList == null || inventoryList.isEmpty()) {
            return "No hay datos de inventario para generar el reporte.";
        }

        StringBuilder report = new StringBuilder(); // Usamos StringBuilder para construir el reporte de forma eficiente
        // Itera sobre la lista de inventarios y construye la cadena del reporte
        for (Inventory inventory : inventoryList) {
            report.append("ID: ").append(inventory.getId())
                  .append(", Movie ID: ").append(inventory.getMovieId())
                  .append(", Store ID: ").append(inventory.getStoreId())
                  .append("\n");
        }
        // Devuelve el reporte como una cadena de texto
        return report.toString();
    }

    /**
     * Exporta el reporte de inventario a un archivo CSV.
     * 
     * @param inventoryList Lista de objetos Inventory para exportar.
     * @param filePath Ruta donde se guardará el archivo CSV.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public void exportInventoryReportToCSV(List<Inventory> inventoryList, String filePath) throws IOException {
        // Verifica si la lista de inventarios está vacía o es nula y lanza una excepción si es así
        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new IllegalArgumentException("La lista de inventarios está vacía o es nula.");
        }

        // Crear el directorio si no existe
        // Se obtiene la ruta del directorio padre y si no existe, se crea
        String parentDir = Paths.get(filePath).getParent().toString();
        if (parentDir != null) {
            Files.createDirectories(Paths.get(parentDir)); // Crea el directorio si no existe
        }

        // Escribir el archivo CSV
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribe los encabezados del CSV
            writer.write("Inventory ID, Movie ID, Store ID\n");

            // Itera sobre la lista de inventarios y escribe cada uno en el archivo CSV
            for (Inventory inventory : inventoryList) {
                writer.write(inventory.getId() + "," 
                            + inventory.getMovieId() + "," 
                            + inventory.getStoreId() + "\n");
            }
        } catch (IOException e) {
            // Si ocurre un error durante la escritura del archivo, se lanza una excepción con el mensaje de error
            throw new IOException("Error al exportar el reporte a CSV: " + e.getMessage(), e);
        }
    }
}


