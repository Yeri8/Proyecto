
package reports;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExportService {

    public boolean exportToCSV(List<String> headers, List<List<String>> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribe encabezados
            writer.write(String.join(",", headers) + "\n");
            
            // Escribe datos
            for (List<String> row : data) {
                writer.write(String.join(",", row) + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}


