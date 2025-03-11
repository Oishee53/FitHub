import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    static Scanner scanner = new Scanner(System.in);
    Dashboard dashboard = new Dashboard();
    public static boolean authenticateLogin(String email, String password, String filename) {
        String storedEmail = null;
        String storedPassword = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length < 4) {
                    continue;
                }
                    storedEmail = details[3];
                    storedPassword = details[4];



                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    WriteToFile.LoginFile(email,password);
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void viewAttendance(String email) {
        System.out.println("Attendance Records for: " + email.trim());
        try (BufferedReader reader = new BufferedReader(new FileReader("Attendance.csv"))) {
            String line;
            boolean hasAttendance = false;

            boolean firstLine = true; // Skip header if any
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (header)
                }

                // Split line by comma to separate columns
                String[] data = line.split(",");

                // Check if the email matches the passed email
                if (data[1].trim().equals(email.trim())) {  // Email is in the second column (data[1])
                    // Print only the records for the specific member
                    System.out.println("Class: " + data[0] + ", Status: " + data[2]);
                    hasAttendance = true;
                }
            }

            // If no attendance found for the member, inform the user
            if (!hasAttendance) {
                System.out.println("No attendance records found for this member.");
            }
        } catch (IOException e) {
            System.out.println("Error reading attendance file: " + e.getMessage());
        }
    }


    /*public static void ReadMemberDetails(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("MemberFile.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String storedEmail = details[3];
                String storedPassword = details[4];
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    System.out.println("Member ID: "+details[0]);
                    System.out.println("Name: " + details[1] + " " + details[2]);
                    System.out.println("Contact Info: " + details[5]);
                    System.out.println("Date of Birth: " + details[6]);
                    System.out.println("Gender:" + details[7]);
                    System.out.println("Address:" + details[8]);
                    System.out.println("Weight: " + details[10]);
                    System.out.println("Height: " + details[11]);
                    System.out.println("Age: " + details[9]);
                    System.out.println("Assigned Trainer: " + details[12]);;
                    System.out.println("Goal: " + details[13]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/
    public static void ReadMemberDetails(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("MemberFile.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String storedEmail = details[3];
                String storedPassword = details[4];
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    String[][] table = {
                            {"Field", "Value"},
                            {"Member ID", details[0]},
                            {"Name", details[1] + " " + details[2]},
                            {"Contact Info", details[5]},
                            {"Date of Birth", details[6]},
                            {"Gender", details[7]},
                            {"Address", details[8]},
                            {"Weight", details[10]},
                            {"Height", details[11]},
                            {"Age", details[9]},
                            {"Assigned Trainer", details[12]},
                            {"Goal", details[13]},
                    };

// Calculate column widths
                    int fieldWidth = 0, valueWidth = 0;
                    for (String[] row : table) {
                        fieldWidth = Math.max(fieldWidth, row[0].length());
                        valueWidth = Math.max(valueWidth, row[1].length());
                    }

// Print the table with proper alignment
                    String format = "| %-" + fieldWidth + "s | %-" + valueWidth + "s |%n";
                    System.out.println("-".repeat(fieldWidth + valueWidth + 7));
                    for (String[] row : table) {
                        System.out.printf(format, row[0], row[1]);
                        System.out.println("-".repeat(fieldWidth + valueWidth + 7));
                    }

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void resetMemberData(int attributeChoice, String newData,
                                       String email, String password, ArrayList<Member> members) throws IOException {
        boolean memberFound = false;
        for (Member member : members) {
            if (member.getEmailAddress().equals(email) && member.getPassword().equals(password)) {
                memberFound = true;

                try {
                    switch (attributeChoice) {
                        case 1 -> member.setFirstName(newData);
                        case 2 -> member.setLastName(newData);
                        case 3 -> member.setPassword(newData);
                        case 4 -> member.setDateOfBirth(newData);
                        case 5 -> member.setGender(newData);
                        case 6 -> member.setAddress(newData);
                        case 7 -> member.setWeight(Integer.parseInt(newData));
                        case 8 -> member.setHeight(Double.parseDouble(newData));
                        case 9 -> member.setAge(Integer.parseInt(newData));
                        case 10 -> member.setPhoneNumber(newData);
                        default -> {
                            System.out.println("Invalid attribute choice.");
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for the chosen attribute. Please enter a valid value.");
                }

                WriteToFile.writeMembers(members, false);

                System.out.println("Member data updated successfully.");
            }
        }
        if (!memberFound) {
            System.out.println("Member not found.");
        }
    }


    public void ReadTrainerDetails(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("TrainerFile.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String storedEmail = details[3];
                String storedPassword = details[4];
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    System.out.println("TrainerID: " + details[0]);
                    System.out.println("Name: " + details[1] + " " + details[2]);
                    System.out.println("Contact Info: " + details[5]);
                    System.out.println("Date of Birth: " + details[6]);
                    System.out.println("Gender:" + details[7]);
                    System.out.println("Address:" + details[8]);
                    System.out.println("Salary: " + details[9]);
                    System.out.println("Age: " + details[10]);
                    System.out.println("Available seats: " + details[11]);
                    printMembersForTrainer(details[0]);
                    System.out.println("View progress of member");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    int viewChoice = scanner.nextInt();
                    if(viewChoice == 1){
                        System.out.println("Enter memberID: ");
                        String memberID = scanner.next();
                        boolean foundEmail = false;
                        for(Member member : Gym.getMemberList()){
                            if(memberID.equals(member.getId())){
                                foundEmail = true;
                                dashboard.graph(member);
                            }
                        }
                        if(foundEmail == false){
                            System.out.println("Invalid email address");
                        }
                    }
                    else if(viewChoice>2){
                        System.out.println("Inavlid choice");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void resetTrainerData(int attributeChoice, String newData,
                                        String email, String password, ArrayList<Trainer> trainers) {
        for (Trainer trainer:trainers) {
            if (trainer.getEmailAddress().equals(email) && trainer.getPassword().equals(password)) {
                switch (attributeChoice) {
                    case 1 -> trainer.setFirstName(newData);
                    case 2 -> trainer.setLastName(newData);
                    case 3 -> trainer.setPassword(newData);
                    case 4 -> trainer.setDateOfBirth(newData);
                    case 5 -> trainer.setGender(newData);
                    case 6 -> trainer.setAddress(newData);
                    case 7 -> trainer.setAge(Integer.parseInt(newData));
                    case 8 -> trainer.setPhoneNumber(newData);
                    default -> System.out.println("Invalid attribute choice.");
                }
                WriteToFile.writeTrainer(trainers, false);  // Save updated trainers to the file

                System.out.println("Trainer data updated successfully.");
            }
        }
        System.out.println("Trainer not found");
    }

    /*public static void printMembersForTrainer(String trainerID) {
        String fileName = "Member and Trainer.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean hasAssignments = false;
            int count = 0;

            System.out.println("Members assigned to Trainer " + trainerID + ":");

            while ((line = reader.readLine()) != null) {
                // Check if the line contains the specified trainerID
                if (line.startsWith(trainerID + " assigned to")) {
                    hasAssignments = true;
                    count++;
                    String memberID = line.substring(line.indexOf("assigned to") + 11);
                    for (Member member : Gym.getMemberList()) {
                        if (memberID.equals(member.getId())) {
                            System.out.printf(count + "." + "%-30s %-15s%n", memberID, "Workout Goal: " + member.getGoal());

                        }
                    }
                }

                if (!hasAssignments) {
                    System.out.println("No members assigned to Trainer " + trainerID + ".");
                }

            }
        }catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }*/
    public static void printMembersForTrainer(String trainerID) {
        String fileName = "Member and Trainer.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean hasAssignments = false;
            int count = 0;

            System.out.println("Members assigned to Trainer " + trainerID + ":");

            // Loop through the file line by line
            while ((line = reader.readLine()) != null) {
                // Check if the line starts with trainerID followed by " assigned to"
                if (line.startsWith(trainerID + " assigned to")) {
                    hasAssignments = true; // Found at least one assignment
                    String memberID = line.substring(line.indexOf("assigned to") + 11).trim();

                    // Find the member in the member list
                    for (Member member : Gym.getMemberList()) {
                        if (memberID.equals(member.getId())) {
                            count++;
                            System.out.printf("%d. %-10s %-15s%n", count, memberID, "Workout Goal: " + member.getGoal());
                            break; // Break after finding the matching member
                        }
                    }
                }
            }

            // Print message if no members are assigned
            if (!hasAssignments) {
                System.out.println("No members assigned to Trainer " + trainerID + ".");
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


}