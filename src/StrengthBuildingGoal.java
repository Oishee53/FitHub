import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StrengthBuildingGoal extends FitnessGoal{
    @Override
    public void suggestion(Member member,TDEECalculator tdeeCalculator) {
        String fileName = null;
        tdeeCalculator.calculator(member);//diet
        if(member.getGender().equalsIgnoreCase("Male")) {
            fileName = "Male Exercise.csv";
        }
        else if(member.getGender().equalsIgnoreCase("Female")) {
            fileName = "Female Exercise.csv";
        }
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                String line;

                // Read each line from the file until the end
                while ((line = bufferedReader.readLine()) != null) {
                    // Split the line into data columns
                    String[] data = line.split(",");

                    // Check if the member's specific goal matches the first column (goal)
                    if (member.getGoal().equalsIgnoreCase(data[0])) {
                        // Print the entire line if the goal matches
                        System.out.println("Your suggested workout plan:\n"+data[1]+"\n"+data[2]+"\n"+data[3]+"\n");
                        break;
                    }
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

    }

}

