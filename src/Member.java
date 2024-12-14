import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Member extends User {

    private int weight;
    private double height;
    private String trainerChose;
    private String goal;
    private String specificGoal;

    public Member(String firstName, String lastName, String emailAddress, String password,
                  String phoneNumber, String DOB, String gender, String address, int age,
                  int weight, double height, String trainerChose, String goal, String specificGoal) {

        super(firstName, lastName, emailAddress, password, phoneNumber, DOB, gender, address, age);

        this.weight = weight;
        this.height = height;
        this.trainerChose = trainerChose;
        this.goal = goal;
        this.specificGoal = specificGoal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getTrainerChose() {
        return trainerChose;
    }

    public void setTrainerChose(String trainerChose) {
        this.trainerChose = trainerChose;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getSpecificGoal() {
        return specificGoal;
    }

    public void setSpecificGoal(String specificGoal) {
        this.specificGoal = specificGoal;
    }

    @Override
    public void showDetails() {
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Contact Info: " + getPhoneNumber());
        System.out.println("Gender: " + getGender());
        System.out.println("Address: " + getAddress());
        System.out.println("Age: " + getAge());
        System.out.println("Weight: " + getWeight());
        System.out.println("Height: " + getHeight());
    }

    // New method to view attendance
    public void viewAttendance() {

        String fileName = "Attendance.csv";
        String email = this.getEmailAddress(); // Email of the logged-in member
        int totalClasses = 24; // Fixed quarterly classes
        int classesAttended = 0;

        System.out.println("Attendance Summary for " + email + ":");
        System.out.println("-----------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Trim any extra spaces before or after the line

                // Skip empty lines or lines that are just spaces
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line by commas
                String[] data = line.split(",");

                // Ensure there are exactly 3 elements (class name, email, and status)
                if (data.length != 3) {
                    System.out.println("Skipping malformed line: " + line);
                    continue; // Skip lines that don't have exactly 3 elements
                }

                // Extract the class name, member email, and attendance status
                String className = data[0].trim(); // e.g., "Class 7"
                String memberEmail = data[1].trim(); // Trim spaces from email
                String status = data[2].trim(); // Trim spaces from status

                // Debug prints to check data
                System.out.println("Checking attendance for member: " + email);
                System.out.println("Class: " + className + ", Member: " + memberEmail + ", Status: " + status);

                // Only process lines that match the logged-in member's email
                if (memberEmail.equalsIgnoreCase(email)) {
                    System.out.println(className + ": " + status);
                    if (status.equalsIgnoreCase("Present")) {
                        classesAttended++;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading attendance file.");
            e.printStackTrace();
        }

        // Calculate percentage (with safeguard for division by zero)
        if (totalClasses > 0) {
            double attendancePercentage = (classesAttended / (double) totalClasses) * 100;
            System.out.println("-----------------------------------");
            System.out.println("Total Classes Attended: " + classesAttended + " out of " + totalClasses);
            System.out.printf("Attendance Percentage: %.2f%%\n", attendancePercentage);
            System.out.println("-----------------------------------");
        } else {
            System.out.println("Error: Total classes is zero, cannot calculate attendance.");
        }
    }



}