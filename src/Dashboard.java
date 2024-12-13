import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dashboard {
    public void graph(Member member){
        //Total calorie burn for suggested exercises
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("WorkoutFile.csv"))) {
            String line;
            // Read each line from the file until the end
            double calorie=0;
            while ((line = bufferedReader.readLine()) != null) {

                // Split the line into data columns
                String[] data = line.split(",");

                //matches with members mail
                if (member.getEmailAddress().equalsIgnoreCase(data[0]))
                    calorie=calorie+Double.parseDouble(data[4]);

            }
            System.out.println("Total calorie burned:"+calorie);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


        System.out.println(" _   _   _   _   _   _   _   _   _   _   _   _   \nJan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec");





    }


}
