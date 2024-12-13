import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
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
                if(filename.equals("TrainerFile.csv")) {
                    storedEmail = details[3];
                    storedPassword = details[4];
                }
                if(filename.equals("MemberFile.csv")) {
                    storedEmail = details[2];
                    storedPassword = details[3];
                }

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


    public static void ReadMemberDetails(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("MemberFile.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String storedEmail = details[2];
                String storedPassword = details[3];
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    System.out.println("Name: " + details[0] + " " + details[1]);
                    System.out.println("Contact Info: " + details[4]);
                    System.out.println("Date of Birth: " + details[5]);
                    System.out.println("Gender:" + details[6]);
                    System.out.println("Address:" + details[7]);
                    System.out.println("Weight: " + details[8]);
                    System.out.println("Height: " + details[9]);
                    System.out.println("Age: " + details[10]);
                    System.out.println("Assigned Trainer: " + details[11]);;
                    System.out.println("Goal: " + details[12]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    public static void resetMemberData(int attributeChoice, String newData,
                                       String email, String password, ArrayList<Member> members) {
        boolean memberFound = false;
        for (Member member : members) {
            System.out.println("Checking member: " + member.getEmailAddress());

            if (member.getEmailAddress().equals(email) && member.getPassword().equals(password)) {
                memberFound = true;
                switch (attributeChoice) {
                    case 1 -> member.setFirstName(newData);
                    case 2 -> member.setLastName(newData);
                    //case 3 -> member.setEmailAddress(newData);
                    case 4 -> member.setPassword(newData);
                    case 5 -> member.setDateOfBirth(newData);
                    case 6 -> member.setGender(newData);
                    case 7 -> member.setAddress(newData);
                    case 8 -> member.setWeight(Integer.parseInt(newData));
                    case 9 -> member.setHeight(Double.parseDouble(newData));
                    case 10 -> member.setAge(Integer.parseInt(newData));
                    case 11 -> member.setGoal(newData);
                    case 12 -> member.setSpecificGoal(newData);
                    default -> System.out.println("Invalid attribute choice.");
                }
                WriteToFile.writeMembers(members, false);  // Save updated members to the file
                System.out.println("Member data updated successfully.");
                return;
            }
        }
        if (!memberFound) {
            System.out.println("Member not found.");
        }
    }


    public static void ReadTrainerDetails(String email, String password) {
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
                    System.out.println("Shift: " + details[9]);
                    System.out.println("Salary: " + details[10]);
                    System.out.println("Age: " + details[11]);
                    System.out.println("Available seats: " + details[12]);
                    printMembersForTrainer(details[0]);



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
                    //  case 3 -> trainer.setEmailAddress(newData);
                    case 4 -> trainer.setPassword(newData);
                    case 5 -> trainer.setDateOfBirth(newData);  // Ensure proper format handling
                    case 6 -> trainer.setGender(newData);
                    case 7 -> trainer.setAddress(newData);
                    case 8 -> trainer.setShift(newData);
                    case 9 -> trainer.setAge(Integer.parseInt(newData));
                    default -> System.out.println("Invalid attribute choice.");
                }
                WriteToFile.writeTrainer(trainers, false);  // Save updated trainers to the file

                System.out.println("Trainer data updated successfully.");
                return;
            }
        }
        System.out.println("Trainer not found");
    }

    public static void printMembersForTrainer(String trainerID) {
        String fileName = "Member and Trainer.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean hasAssignments = false;

            System.out.println("Members assigned to Trainer " + trainerID + ":");

            while ((line = reader.readLine()) != null) {
                // Check if the line contains the specified trainerID
                if (line.startsWith(trainerID + " assigned to")) {
                    hasAssignments = true;
                    // Extract the member name from the line and print it
                    String memberName = line.substring(line.indexOf("assigned to") + 11);
                    System.out.println(memberName);
                }
            }

            if (!hasAssignments) {
                System.out.println("No members assigned to Trainer " + trainerID + ".");
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

}