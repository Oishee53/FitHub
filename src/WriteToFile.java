import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {
    // Constructor
    public WriteToFile() {
    }
    public static void writeAttendance(String className, String presentIDs, List<Member> members) {
        String[] presentList = presentIDs.split(",");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Attendance.csv", true))) {
            for (Member member : members) {
                String status = "Absent";
                for (String ID : presentList) {
                    if (member.getId().equalsIgnoreCase(ID.trim())) {
                        status = "Present";
                        break;
                    }
                }
                bw.write(className + "," + member.getId() + "," + status);
                bw.newLine();
            }
            System.out.println("Attendance recorded successfully!");
        } catch (IOException e) {
            System.out.println("Error writing attendance.");
            e.printStackTrace();
        }
    }

    public static void writeMembers(ArrayList<Member> members,boolean append) {
       append = false;
        FileWriter memberFileWriter = null;
        try {
            // Open the file in append mode if specified
            memberFileWriter = new FileWriter("MemberFile.csv",append);


            // Write member details to the file
            for (Member member : members) {
                memberFileWriter.write(member.getId()+","+member.getFirstName() + "," + member.getLastName() + "," +
                        member.getEmailAddress() + "," + member.getPassword() + "," +
                        member.getPhoneNumber() + "," + member.getDateOfBirth() + "," +
                        member.getGender() + "," + member.getAddress() + "," +
                        member.getAge() + "," + member.getWeight() +
                        "," + member.getHeight() + "," + member.getTrainerChose() + "," + member.getGoal()+ "\n");
            }

            // Ensure data is written to the file
            memberFileWriter.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {

            try {
                if (memberFileWriter != null) {
                    memberFileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the file writer: " + e.getMessage());
            }
        }
    }
    public static void LoginFile(String loginEmail, String loginPassword) {
        try (FileWriter writer = new FileWriter("LoginFileInfo.csv")) {  // Append mode enabled
            writer.write("Email: " + loginEmail + "\n" + "Password: " + loginPassword + "\n\n");
            System.out.println("Login info saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing login info to file: " + e.getMessage());
        }
    }

    public static void writeTrainer(ArrayList<Trainer> trainers,boolean append) {
        FileWriter trainerFileWriter = null;
        try {
            // Open the file in append mode if specified
            trainerFileWriter = new FileWriter("TrainerFile.csv",append);


            // Write member details to the file
            for (Trainer trainer:trainers) {
                trainerFileWriter.write(trainer.getTrainerID() + "," + trainer.getFirstName() + "," + trainer.getLastName() + "," +
                        trainer.getEmailAddress() + "," + trainer.getPassword() + "," +
                        trainer.getPhoneNumber() + "," + trainer.getDateOfBirth() + "," +
                        trainer.getGender() + "," + trainer.getAddress() + "," + trainer.getSalary() + "," +
                        trainer.getAge() + "," + trainer.getAvailableSeats() + "\n");
            }

            // Ensure data is written to the file
            trainerFileWriter.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            // Always close the file writer in a finally block
            try {
                if (trainerFileWriter != null) {
                    trainerFileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the file writer: " + e.getMessage());
            }
        }
    }
    public static void writeInventory(ArrayList<Equipment> equipments, boolean append) {
        FileWriter equipmentFileWriter = null;
        try {
            // Open the file in append mode if specified
            equipmentFileWriter = new FileWriter("EquipmentFile.csv", append);

            // Write equipment details to the file
            for (Equipment equipment : equipments) {
                equipmentFileWriter.write(equipment.getEquipmentID() + "," +
                        equipment.getName() + "," +
                        equipment.getCondition() + "," +
                        equipment.getQuantity() + "," +
                        equipment.getCost() + "\n");
            }

            // Ensure data is written to the file
            equipmentFileWriter.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            try {
                if (equipmentFileWriter != null) {
                    equipmentFileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the file writer: " + e.getMessage());
            }
        }
    }
    public static void memberAndTrainer(Gym gym) {
        String fileName = "Member and Trainer.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for(Trainer trainer: gym.getTrainerList()) {
                for(Member member: trainer.getAssignedMembers()) {
                    writer.write(trainer.getTrainerID() + " assigned to " + member.getId());
                    writer.newLine();  // Add a new line after each entry
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing info to file: " + e.getMessage());
        }
    }
    public static void BalanceFile(double balance) {
        try (FileWriter writer = new FileWriter("BalanceFile.csv", false)) {  // Append mode enabled
            writer.write(String.valueOf(balance) + "\n");  // Convert balance to string and add a newline
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void initialAccountBalance(double balance){
        String filename="AccountsFile.txt";
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))){
            writer.write("Initial Balance: " + balance + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void memberAccount(String name, double amount){
        String filename="AccountsFile.txt";
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))){
            writer.write(name + " registered with " + amount + " taka!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void TrainerAccount(String ID,double salary){
        String filename="AccountsFile.txt";
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))){
            writer.write(salary + " taka salary paid to " + ID + "!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void EquipmentAccount(String name, double cost, int quantity) {
        String filename = "AccountsFile.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Purchased " + quantity + " " + name + " for " + (quantity * cost) + " taka.\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}