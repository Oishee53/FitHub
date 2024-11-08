import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {

    // Constructor
    public WriteToFile() {
    }

    public static void writeMembers(ArrayList<Member> members,boolean append) {
        append = false;
        FileWriter memberFileWriter = null;
        try {
            // Open the file in append mode if specified
            memberFileWriter = new FileWriter("MemberFile.csv",append);


            // Write member details to the file
            for (Member member : members) {
                memberFileWriter.write(member.getFirstName() + "," + member.getLastName() + "," +
                        member.getEmailAddress() + "," + member.getPassword() + "," +
                        member.getPhoneNumber() + "," + member.getDateOfBirth() + "," +
                        member.getGender() + "," + member.getAddress() + "," +
                        member.getWeight() + "," + member.getHeight() +
                        "," + member.getAge() + "," + member.getTrainerChose() + "," + member.getGoal()+ ","+ member.getSpecificGoal()+ "\n");
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
                        trainer.getGender() + "," + trainer.getAddress() + "," +
                        trainer.getShift() + "," + trainer.getSalary() + "," +
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
    public static void writeInventory(ArrayList<Equipment> equipments,boolean append){
        FileWriter EquipmentFileWriter = null;
        try {
            // Open the file in append mode if specified
            EquipmentFileWriter = new FileWriter("EquipmentFile.csv",true);


            // Write member details to the file
            for (Equipment equipment : equipments) {
                EquipmentFileWriter.write(equipment.getEquipmentID() + "," + equipment.getName() + "," +
                        equipment.getCondition() + "," + equipment.getQuantity() + "," + "\n");
            }

            // Ensure data is written to the file
            EquipmentFileWriter.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            try {
                if (EquipmentFileWriter != null) {
                    EquipmentFileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the file writer: " + e.getMessage());
            }
        }
    }
    public static void memberAndTrainer(Gym gym, Member member, String trainerID) {
        String fileName = "Member and Trainer.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {  // Append mode enabled
            writer.write(trainerID + " assigned to " + member.getFirstName() + " " + member.getLastName());
            writer.newLine();  // Add a new line after each entry
        } catch (IOException e) {
            System.err.println("Error writing info to file: " + e.getMessage());
        }
    }


}
