import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AssignTrainer {
    static Scanner scanner= new Scanner(System.in);

    public static boolean AvailableTrainerList(Gym gym) {
        System.out.println("Available trainers: ");
        // Print the headings with specific formatting
        System.out.printf("%-12s %-10s %-15s%n", "TrainerID", "Shift", "Available Seats");
        boolean availableTrainer = false;

        // Loop through each trainer in the gym's trainer list
        for (Trainer trainer : gym.getTrainerList()) {
            if (trainer.getAvailableSeats() > 0) {
                availableTrainer = true;
                // Print the trainer's details with the same formatting
                System.out.printf("%-12s %-10s %-15d%n",
                        trainer.getTrainerID(), trainer.getShift(), trainer.getAvailableSeats());
            }
        }

        if (!availableTrainer) {
            return false;
        }

        return availableTrainer;
    }



    public static void trainerAssigned(Gym gym, String trainerID, Member member) {
        for (Trainer trainer : gym.getTrainerList()) {
            if (trainerID.equals(trainer.getTrainerID())) {
                int remainingSeats = trainer.getAvailableSeats() - 1;
                trainer.setAvailableSeats(remainingSeats);
                trainer.addAssignedMember(member);
            }
        }
    }
    public void memberUnassigned(Gym gym, String trainerID,Member member) {
        for (Trainer trainer : gym.getTrainerList()) {
            if (trainerID.equals(trainer.getTrainerID())) {
                int remainingSeats = trainer.getAvailableSeats() + 1;
                trainer.setAvailableSeats(remainingSeats);
                trainer.removeAssignedMember(member);
            }
        }
        String fileName = "Member and Trainer.csv";
        ArrayList<String> lines = new ArrayList<>();

        // Read the file and load each line into a list
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if this line contains the trainer ID and member name
                if (!line.contains(trainerID + " assigned to " + member.getFirstName() + " " + member.getLastName())) {
                    // Add only lines that don't match the unassigned member
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Rewrite the file with the modified list of lines (excluding the removed assignment)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }// after calling this method rewrite the trainer file
    }
    public static void trainerReassign(Gym gym, Member member){
        if(AvailableTrainerList(gym)){
            System.out.println("Select a new trainerID for " + member.getFirstName()+" "
                    + member.getLastName()+": ");
            String trainerID = scanner.next();
            member.setTrainerChose(trainerID);
            trainerAssigned(gym,trainerID,member);
            WriteToFile.writeTrainer(gym.getTrainerList(),false);

        }
        else{
            member.setTrainerChose("NULL");
        }
        WriteToFile.writeMembers(gym.getMemberList(),false);
        WriteToFile.memberAndTrainer(gym);
    }
}
