
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
     * @param inventoryList Lista de objetos Inventory para generar el reporte.
     * @return El reporte como String.
     */
    public String generateInventoryReport(List<Inventory> inventoryList) {
        if (inventoryList == null || inventoryList.isEmpty()) {
            return "No hay datos de inventario para generar el reporte.";
        }

        StringBuilder report = new StringBuilder();
        for (Inventory inventory : inventoryList) {
            report.append("ID: ").append(inventory.getId())
                  .append(", Movie ID: ").append(inventory.getMovieId())
                  .append(", Store ID: ").append(inventory.getStoreId())
                  .append("\n");
        }
        return report.toString();
    }

    /**
     * Exporta el reporte de inventario a un archivo CSV.
     * @param inventoryList Lista de objetos Inventory para exportar.
     * @param filePath Ruta donde se guardará el archivo CSV.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public void exportInventoryReportToCSV(List<Inventory> inventoryList, String filePath) throws IOException {
        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new IllegalArgumentException("La lista de inventarios está vacía o es nula.");
        }

        // Crear el directorio si no existe
        String parentDir = Paths.get(filePath).getParent().toString();
        if (parentDir != null) {
            Files.createDirectories(Paths.get(parentDir));
        }

        // Escribir el archivo CSV
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Inventory ID, Movie ID, Store ID\n");

            for (Inventory inventory : inventoryList) {
                writer.write(inventory.getId() + "," 
                            + inventory.getMovieId() + "," 
                            + inventory.getStoreId() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Error al exportar el reporte a CSV: " + e.getMessage(), e);
        }
    }
}


