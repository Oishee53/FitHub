import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class LoadData {
    public static void LoadMemberDetails(Gym gym) {
        String memberFilePath = "MemberFile.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(memberFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");

                // Check the array length to prevent out-of-bounds errors
                if (details.length < 14) {
                    System.out.println("Skipping invalid member record: " + line);
                    continue;
                }

                try {
                    // Parse numeric values with error handling
                    int age = Integer.parseInt(details[10]);
                    int weight = Integer.parseInt(details[8]);
                    double height = Double.parseDouble(details[9]);

                    // Create the Member object
                    Member member = new Member(details[0], details[1], details[2],
                            details[3], details[4], details[5], details[6],
                            details[7], age, weight, height, details[11],details[12],details[13]);
                    gym.addMembers(member);

                } catch (NumberFormatException ex) {
                    System.out.println("Skipping member with invalid numeric data: " + line);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading member data file: " + ex.getMessage());
        }
    }

    public static void LoadTrainerDetails(Gym gym) {
        String trainerFilePath = "TrainerFile.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(trainerFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");

                // Check the array length to prevent out-of-bounds errors
                if (details.length < 13) {
                    continue;
                }

                try {
                    // Parse numeric values with error handling
                    int age = Integer.parseInt(details[11]);
                    int availableSeats = Integer.parseInt(details[12]);
                    double salary = Double.parseDouble(details[10]);

                    // Create the Trainer object
                    Trainer trainer = new Trainer(details[0], details[1],
                            details[2], details[3], details[4], details[5],
                            details[6], details[7],details[8], age, details[9], salary, availableSeats);
                    gym.addTrainer(trainer);

                } catch (NumberFormatException ex) {
                    System.out.println("Skipping trainer with invalid numeric data: " + line);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading trainer data file: " + ex.getMessage());
        }
    }


    public static void LoadEquipmentDetails(Gym gym) {
        try (BufferedReader reader = new BufferedReader(new FileReader("EquipmentFile.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                // Convert details[2] to integers
                int details2 = Integer.parseInt(details[2]);
                // Create the Member object using the parsed values
                Equipment equipment = new Equipment(details[0], details[1],details2,details[3]);
                gym.addEquipment(equipment);


            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Print stack trace for debugging
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing numeric values: " + ex.getMessage());
        }
    }


}
