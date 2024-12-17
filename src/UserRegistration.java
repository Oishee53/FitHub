import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserRegistration {
    Scanner scanner = new Scanner(System.in);
    AssignTrainer assignTrainer = new AssignTrainer();
    public UserRegistration() {

    }
    public void memberRegistration(Gym gym) throws IOException {

        String memberId=autogenerate("MemberFile.csv");
        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Last Name: ");
        String lastName = scanner.next();
        System.out.println("Enter email address:");
        String emailAddress = scanner.next();
        while (!canUseEmail(emailAddress,"MemberFile.csv")) {
            System.out.println("Email already exists.\nEnter a new email");
            emailAddress = scanner.next();
        }
        System.out.println("Enter Password:");
        String password = scanner.next();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.next();
        while(!validPhoneNumber(phoneNumber)){
            System.out.println("Invalid phone number.\nEnter Phone Number:");
            phoneNumber=scanner.next();
        }
        System.out.println("Enter Date of Birth(dd/MM/YYYY):");
        String DOB = scanner.next();
        while(!validDate(DOB)){
            System.out.println("Invalid Date Format.\nWrite Date in dd/MM/yyyy.");
            DOB=scanner.next();
        }
        System.out.println("Select Gender: \n1.Male\n2.Female");
        int selectGender = scanner.nextInt();
        String gender = null;
        while(selectGender!=1 && selectGender!=2){
            System.out.println("Invalid choice!");
            System.out.println("Select Gender: \n1.Male\n2.Female");
            selectGender = scanner.nextInt();
        }
        if(selectGender == 1){
            gender = "Male";
        }
        else if(selectGender == 2){
            gender = "Female";
        }

        System.out.println("Enter Address:");
        String address = scanner.next();
        System.out.println("Enter Weight: ");
        int weight = scanner.nextInt();
        System.out.println("Enter Height: ");
        double height = scanner.nextDouble();
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        //Available trainer
        boolean isAvailable = assignTrainer.AvailableTrainerList(gym);
        if(isAvailable == false){
            System.out.println("No trainers are available at the moment. Registration cannot proceed.");
            return;
        }
        else {
            System.out.println("Enter TrainerID: ");
            String trainerID = scanner.next();
            System.out.println("Select goal\n1.Strength Building\n2.Weightloss");
            int selectGoal = scanner.nextInt();
            String goal = null;
            while(selectGoal!=1 && selectGoal!=2){
                System.out.println("Invalid choice!");
                System.out.println("Select goal\n1.Strength Building\n2.Weightloss");
                selectGoal = scanner.nextInt();
            }
            if(selectGoal==1){
                goal = "StrengthBuilding";
            }
            if(selectGoal==2){
                goal = "WeightLoss";
            }
            System.out.println("Enter your Specific goal");
            String specificGoal=scanner.next();
            Member member = new Member(memberId,firstName, lastName, emailAddress, password, DOB, phoneNumber, gender,
                    address, age, weight, height, trainerID,goal,specificGoal);
            gym.addMembers(member);
            assignTrainer.trainerAssigned(gym, member.getTrainerChose(), member);
            String fullname = firstName + " " + lastName;
            Account.memberPayment(6000);
            WriteToFile.memberAccount(fullname,6000);
            WriteToFile.writeMembers(gym.getMemberList(), false);
            WriteToFile.writeTrainer(gym.getTrainerList(), false);
            WriteToFile.memberAndTrainer(gym);
            System.out.println("Member is registered successfully!!");
        }

    }

    public void trainerRegistration(Gym gym) throws IOException {

        String trainerID = autogenerate("TrainerFile.csv");

        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter email address:");
        String emailAddress = scanner.next();
        while (!canUseEmail(emailAddress,"TrainerFile.csv")) {
            System.out.println("Email already exists.\nEnter a new email");
            emailAddress = scanner.next();
        }
        System.out.println("Enter Password:");
        String password = scanner.next();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.next();
        while(!validPhoneNumber(phoneNumber)){
            System.out.println("Invalid phone number.\nEnter Phone Number:");
            phoneNumber=scanner.next();
        }

        System.out.println("Enter Date of Birth(dd/MM/YYYY):");
        String DOB = scanner.next();
        while(!validDate(DOB)){
            System.out.println("Invalid Date Format.\nWrite Date in dd/MM/yyyy.");
            DOB=scanner.next();
        }
        System.out.println("Select Gender: \n1.Male\n2.Female");
        int selectGender = scanner.nextInt();
        String gender = null;
        while(selectGender!=1 && selectGender!=2){
            System.out.println("Invalid choice!");
            System.out.println("Select Gender: \n1.Male\n2.Female");
            selectGender = scanner.nextInt();
        }
        if(selectGender == 1){
            gender = "Male";
        }
        else if(selectGender == 2){
            gender = "Female";
        }
        System.out.println("Enter Address:");
        String address = scanner.next();
        System.out.println("Enter Shift: ");
        String shift;
        shift = String.valueOf(scanner.next());
        System.out.println("Enter Salary: ");
        int salary = scanner.nextInt();
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        System.out.println("How many members do they want to train?");
        int availableSeats = scanner.nextInt();
        Trainer trainer = new Trainer(trainerID,firstName, lastName, emailAddress, password, DOB ,phoneNumber, gender,
                address, age,shift,salary,availableSeats);
        gym.addTrainer(trainer);
        WriteToFile.writeTrainer(gym.getTrainerList(), false);
        System.out.println("Trainer is registered successfully!!");

    }
    public boolean canUseEmail(String email,String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length < 3) {
                    continue;
                }
                String storedEmail = details[2];
                if (storedEmail.equals(email)) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private boolean validDate(String date){
        return date.matches("\\d{2}/\\d{2}/\\d{4}");

    }
    private boolean validPhoneNumber(String phoneNumber){
        return phoneNumber.matches("(\\+8801|01)\\d{9}");

    }
    public String autogenerate(String filename) {
        String generatedID=null;
        if(filename.equals("MemberFile.csv"))
            generatedID = "M-01"; // Default ID if file is empty

        else if (filename.equals("TrainerFile.csv"))
            generatedID = "T-01";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            String lastID = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].contains("-")) { // Validate data
                    String[] partsOfId = data[0].split("-");
                    if (partsOfId.length == 2) { // Ensure correct ID format
                        lastID = partsOfId[1]; // Get the numeric part of the last ID
                    }
                }
            }

            if (lastID != null) {
                int parsedID = Integer.parseInt(lastID);
                int newID = parsedID + 1;
                if(filename.equals("MemberFile.csv"))
                    generatedID = "M-" + String.format("%02d", newID); // Format ID as "M-XX"
                else if (filename.equals("TrainerFile.csv"))
                    generatedID = "T-" + String.format("%02d", newID); // Format ID as "M-XX"

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format in file: " + e.getMessage());
        }
        return generatedID;
    }

}