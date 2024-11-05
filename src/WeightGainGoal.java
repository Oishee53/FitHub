import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WeightGainGoal {
    public void weightGainFileRead(Member member) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Weight Gain.csv"))) {
            String line;

            // Read each line from the file until the end
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into data columns
                String[] data = line.split(",");

                // Check if the member's specific goal matches the first column (goal)
                if (member.getSpecificGoal().equalsIgnoreCase(data[0])) {
                    // Print the entire line if the goal matches
                    System.out.println("Your goal is to gain " + data[0] + "\nYou need to do " + data[1] + "\nYou should daily consume " + data[2] + "\nFor protein you can have " + data[3] + "\nFor carb you can have " + data[4] + "\nFor fat you can have " + data[5]);
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
