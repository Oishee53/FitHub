import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Workouts {
    public void NewMaleWorkout(Member member,Scanner scanner){
        double metValue = 0;
        String exercise = null;

        System.out.println("Enter Todays date\n");
        String date =scanner.next();

        System.out.println("Enter new workout:\n");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Male Exercise.csv"))) {
                String line;

                // Read each line from the file until the end
                while ((line = bufferedReader.readLine()) != null) {
                    // Split the line into data columns
                    String[] data = line.split(",");

                    // Check if the member's specific goal matches the first column (goal)
                    if (member.getGoal().equalsIgnoreCase(data[0])) {
                        // Print the entire line if the goal matches
                        System.out.println("1."+data[1]+"\n2."+data[2]+"\n3."+data[3]+"\n");
                        int Workout=scanner.nextInt();
                        if (Workout==1 && data[1].equalsIgnoreCase("Running (6 mph or 10 km/h) for 45 minutes")){
                             metValue=9.8;
                             exercise = data[1];
                        }
                         else if (Workout==1 && data[1].equalsIgnoreCase("Heavy Weightlifting (Compound Movements) for 45 minutes")){
                            metValue=6.5;
                            exercise = data[1];
                        }

                         else if (Workout==2 && data[2].equalsIgnoreCase("HIIT for 20-30 minutes")){
                            metValue=10;
                            exercise = data[2];
                        }

                         else if (Workout==2 && data[2].equalsIgnoreCase("Bodyweight Strength Training (Push-ups, Pull-ups, Dips) for 30 minutes")){
                            metValue=5;
                            exercise = data[2];
                        }
                         else if (Workout==3 && data[3].equalsIgnoreCase("Rowing for 30 minutes")){
                            metValue=7;
                            exercise = data[3];
                        }

                         else if (Workout==3 && data[3].equalsIgnoreCase("Push/Pull Strength Routine for 45 minutes")){
                            metValue=5.5;
                            exercise = data[3];
                        }


                        System.out.println("Enter the duration in hours");
                        int duration=scanner.nextInt();
                        double burnedCalorie= member.getWeight()*duration*metValue;

                        FileWriter WorkoutFilewriter = null;
                        try{
                            WorkoutFilewriter = new FileWriter("WorkoutFile.csv",true);
                            WorkoutFilewriter.write(member.getEmailAddress() + "," +date+","+ exercise + "," +
                                    duration + "," + burnedCalorie + "\n");
                            WorkoutFilewriter.flush();
                        }
                        catch (IOException e) {
                            System.out.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                        finally {
                            try {
                                if (WorkoutFilewriter != null) {
                                    WorkoutFilewriter.close();
                                }
                            } catch (IOException e) {
                                System.out.println("Error closing the file writer: " + e.getMessage());
                            }
                        }
                        //break;
                    }
                }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }





    }
    public void NewFemaleWorkout(Member member,Scanner scanner){
        double metValue = 0;
        String exercise = null;
        System.out.println("Enter Todays date\n");
        String date =scanner.next();
        System.out.println("Enter new workout:\n");
        String filename = "Female Exercise.csv";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read each line from the file until the end
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into data columns
                String[] data = line.split(",");

                // Check if the member's specific goal matches the first column (goal)
                if (member.getGoal().equalsIgnoreCase(data[0])) {
                    // Print the entire line if the goal matches
                    System.out.println("1."+data[1]+"\n2."+data[2]+"\n3."+data[3]+"\n");
                    int Workout=scanner.nextInt();
                    if (Workout==1 && data[1].equalsIgnoreCase("Running (6 mph or 10 km/h) for 30 minutes")){
                        metValue=9.8;
                        exercise = data[1];
                    }
                    else if (Workout==1 && data[1].equalsIgnoreCase("Moderate Weightlifting (Full Body) for 30 minutes")){
                        metValue=4;
                        exercise = data[1];
                    }
                    else if (Workout==2 && data[2].equalsIgnoreCase("Jump rope for 15 minutes")){
                        metValue=11.8;
                        exercise = data[2];
                    }
                    else if (Workout==2 && data[2].equalsIgnoreCase("Bodyweight Exercises (Squats, Lunges, Push-ups) for 30 minutes")){
                        metValue=5;
                        exercise = data[2];
                    }
                    else if (Workout==3 && data[3].equalsIgnoreCase("HIIT for 20-30 minutes")) {
                        metValue = 10;
                        exercise = data[3];
                    }
                    else if (Workout==3 && data[3].equalsIgnoreCase("Pilates or Yoga (Strength Focus) for 45 minutes")){
                        metValue=5.5;
                        exercise = data[3];
                    }
                    System.out.println("Enter the duration in hours");
                    int duration=scanner.nextInt();
                    double burnedCalorie= member.getWeight()*duration*metValue;


                    FileWriter WorkoutFilewriter = null;
                    try{
                        WorkoutFilewriter = new FileWriter("WorkoutFile.csv",true);
                    WorkoutFilewriter.write(member.getEmailAddress() + "," +date+","+ exercise + "," +
                            duration + "," + burnedCalorie + "\n");
                    WorkoutFilewriter.flush();
                      }
                    catch (IOException e) {
                    System.out.println("An error occurred while writing to the file: " + e.getMessage());
                }
                    finally {
                    try {
                        if (WorkoutFilewriter != null) {
                            WorkoutFilewriter.close();
                        }
                    } catch (IOException e) {
                        System.out.println("Error closing the file writer: " + e.getMessage());
                    }
                }
                    //break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public void pastWorkout(Member member){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("WorkoutFile.csv"))) {
            String line;

            // Read each line from the file until the end
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into data columns
                String[] data = line.split(",");

                // Check if the member's specific goal matches the first column (goal)
                if (member.getEmailAddress().equalsIgnoreCase(data[0])) {
                    // Print the entire line if the goal matches
                    System.out.println("Date:"+data[1]+",Exercise:"+data[2]+",Duration:"+data[3]+",BurnedCalories:"+data[4]);

                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

}
