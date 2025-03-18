import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WeightLoseGoal extends FitnessGoal{
    @Override
    public void suggestion(Member member, TDEECalculator tdeeCalculator) {
        tdeeCalculator.calculator(member);
        String fileName = member.getGender().equalsIgnoreCase("Male") ? "Male Exercise.csv" : "Female Exercise.csv";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (member.getGoal().equalsIgnoreCase(data[0])) {
                    // Print workout plan in table format
                    System.out.println("-------------------------------------------------------------------------------------------------------");
                    System.out.printf("\n| %-25s | %-70s | ", "Exercise", "Details");
                    System.out.println("\n------------------------------------------------------------------------------------------------------");
                    System.out.printf("\n| %-25s | %-70s | ", "Workout 1", data[1]);
                    System.out.printf("\n| %-25s | %-70s | ", "Workout 2", data[2]);
                    System.out.printf("\n| %-25s | %-70s | ", "Workout 3", data[3]);
                    System.out.println("\n------------------------------------------------------------------------------------------------------");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

