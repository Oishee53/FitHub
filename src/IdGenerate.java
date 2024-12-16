import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IdGenerate {
    public String autogenerate(String filename) {
        String generatedID = "M-01"; // Default ID if file is empty
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            String lastID = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].contains("-")) { // Validate data
                    String[] partsOfId = data[0].split("-");
                    if (partsOfId.length == 2) { // Ensure correct ID format
                        lastID = partsOfId[1]; // Get the numeric part of the last ID
                    }
                }
            }

            if (lastID != null) {
                int parsedID = Integer.parseInt(lastID);
                int newID = parsedID + 1;
                generatedID = "M-" + String.format("%02d", newID); // Format ID as "M-XX"
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format in file: " + e.getMessage());
        }
        return generatedID;
    }
}
