import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public void readFile(String filename)  {
        if(filename.equals("MemberFile.csv")){
            System.out.println("All Member Details: ");
        }
        else if(filename.equals("TrainerFile.csv")){
            System.out.println("All Trainer Details: ");
        }
        else if(filename.equals("Member and Trainer.csv")){
            System.out.println("Trainer Assignment Information: ");
        }
        else {
            System.out.println("Unknown File Type:");
        }
        // Try-with-resources to ensure the file is closed automatically
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read each line from the file until the end
            while ((line = bufferedReader.readLine()) != null) {
                // Process each line as needed; here, we just print it
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public void readCSVFile(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                // Split the line by comma, preserving values inside quotes
                String[] values = line.split(",");
                for (String value : values) {
                    System.out.print(value.trim() + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    public static double readBalanceFile(String filename) {
        double balance = 0.0;  // Default value for an empty or non-existent file

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line != null && !line.trim().isEmpty()) {
                balance = Double.parseDouble(line);  // Parse balance if there's data
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing balance from file: " + e.getMessage());
        }

        return balance;  // Return the balance (0.0 if file is empty or an error occurs)
    }
}
