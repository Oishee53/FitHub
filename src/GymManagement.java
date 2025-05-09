import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GymManagement {
    static GymManagement gymManagement = new GymManagement();
    UserRegistration userRegistration = new UserRegistration();
    UserRemove userRemove = new UserRemove();
    EquipmentAdd equipmentAdd = new EquipmentAdd();
    static LoadData loadData = new LoadData();
    ReadFile readListFile = new ReadFile();
    Login login = new Login();
    static Gym gym = new Gym();
    WeightLoseGoal weightLoseGoal = new WeightLoseGoal();
    StrengthBuildingGoal strengthBuildingGoal = new StrengthBuildingGoal();
    TDEECalculator tdeeCalculator = new TDEECalculator();
    Workouts workouts = new Workouts();
    Dashboard dashboard= new Dashboard();
    PasswordField passwordField = new PasswordField();
    static Scanner scanner = new Scanner(System.in);

    // Main method
    public static void main(String[] args) throws IOException {
        String[] csvFiles = {
                "MemberFile.csv",
                "TrainerFile.csv",
                "Member and Trainer.csv",
                "EquipmentFile.csv",
                "LoginInfoFile.csv",
                "Male Exercise.csv",
                "Female Exercise.csv",
                "WorkoutFile.csv",
                "BalanceFile.csv",
                "Attendance.csv"
        };

        // Create each file
        for (String csvFile : csvFiles) {
            createCSVFile(csvFile);
        }
        double initailBalance = 100000;
        double updatedBalance = 0.0;
        updatedBalance =ReadFile.readBalanceFile("BalanceFile.csv");
        if(updatedBalance == 0.0){
            Account account = new Account(initailBalance);
            WriteToFile.BalanceFile(initailBalance);
            WriteToFile.initialAccountBalance(initailBalance);
        }
        else{
            Account account = new Account(updatedBalance);
        }
        
        loadData.LoadMemberDetails(gym);
        loadData.LoadTrainerDetails(gym);
        loadData.LoadEquipmentDetails(gym);
        loadData.LoadMemberAssignedToTrainerDetails(gym);
        gymManagement.consoleApp();

    }

    //method for all operation
    public void consoleApp() throws IOException {


        System.out.println(".............FitHub...............");
        System.out.println("Enter your choice:");
        System.out.println("1.Admin\n2.Member\n3.Trainer");
        int loginChoice = scanner.nextInt();
        if (loginChoice == 1) {
                System.out.print("Enter your email:\n");
                String loginEmail = scanner.next();
                System.out.print("Enter your password:\n");
                String password = scanner.next();
                String loginPassword = PasswordField.hashPassword(password);
                String filename = "AdminFile.csv";
                boolean isAuthenticated = Login.authenticateLogin(loginEmail, loginPassword, filename);
                if (isAuthenticated) {
                    adminPanel();
                }
                else{
                    System.out.println("Wrong email or password:");
                    gymManagement.consoleApp();
                }
        }



        //member
        else if (loginChoice == 2) {
            System.out.print("Enter your email:\n");
            String loginEmail = scanner.next();
            System.out.print("Enter your password:\n");
            String password = scanner.next();
            String loginPassword = PasswordField.hashPassword(password);
            String filename = "MemberFile.csv";
            boolean isAuthenticated = Login.authenticateLogin(loginEmail, loginPassword, filename);
            if (isAuthenticated) {
                memberPanel(loginEmail, loginPassword);
            }
            else{
                    System.out.println("Wrong email or password:");
                    gymManagement.consoleApp();
            }
        }

        //trainer
        else if (loginChoice == 3) {
            System.out.print("Enter your email:\n");
            String loginEmail = scanner.next();
            System.out.println("Enter your password:");
            String password = scanner.next();
            String loginPassword = PasswordField.hashPassword(password);
            String filename = "TrainerFile.csv";
            boolean isAuthenticated = Login.authenticateLogin(loginEmail, loginPassword, filename);
            if (isAuthenticated) {
                trainerPanel(loginEmail,loginPassword);
            } else {
                System.out.println("Wrong email or password:");
                gymManagement.consoleApp();
            }
        }


    }
    private static void createCSVFile (String fileName){
        File file = new File(fileName);
        try {
            // Create the file
            if (file.createNewFile()) {
                System.out.println("CSV file created: " + file.getName());
            } else {
                // System.out.println("CSV file already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating: " + fileName);
            e.printStackTrace();
        }
    }
    public void memberPanel(String loginEmail,String loginPassword) throws IOException {
        System.out.println("1. View Your Details");
        System.out.println("2. Update Details");
        System.out.println("3. View Suggestions Based On Your Goal");
        System.out.println("4. Track your progress");
        System.out.println("5. Logout");
        int memberChoice = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        if (memberChoice == 1) {
            login.ReadMemberDetails(loginEmail, loginPassword); // Existing method for viewing details
            login.viewAttendance(loginEmail); // New method to display attendance
        } else if (memberChoice == 2) {
            int memberResetChoice;
            String newData;
            System.out.println("1.Reset First Name ");
            System.out.println("2.Reset Last Name ");
            System.out.println("3.Reset Password ");
            System.out.println("4.Reset Date Of Birth ");
            System.out.println("5.Reset Gender ");
            System.out.println("6.Reset Address ");
            System.out.println("7.Reset Weight ");
            System.out.println("8.Reset Height ");
            System.out.println("9.Reset Age ");
            System.out.println("10.Reset Phone Number");
            memberResetChoice = scanner.nextInt();
            System.out.println("Enter new data ");
            newData = scanner.next();
            login.resetMemberData(memberResetChoice, newData, loginEmail, loginPassword, gym.getMemberList());
        } else if (memberChoice == 3) {
            for (Member member : gym.getMemberList()) {


                if (member.getEmailAddress().equalsIgnoreCase(loginEmail)) {

                    if (member.getGoal().equalsIgnoreCase("WeightLoss")) {
                        weightLoseGoal.suggestion(member, tdeeCalculator);
                        return;
                    } else if (member.getGoal().equalsIgnoreCase("StrengthBuilding")) {
                        strengthBuildingGoal.suggestion(member, tdeeCalculator);
                    } else {
                        System.out.println("Goal did not match either Weight Loss or Strength Building");
                    }
                }
            }
        } else if (memberChoice == 4) {
            System.out.println("1.Dashboard");
            System.out.println("2.Workout");
            int trackingChoice = scanner.nextInt();
            if (trackingChoice == 1) {
                //DashBoard
                for (Member member : gym.getMemberList()) {
                    if (member.getEmailAddress().equalsIgnoreCase(loginEmail)) {
                        dashboard.graph(member);
                    }
                }
            } else if (trackingChoice == 2) {
                System.out.println("1.Post new workout");
                System.out.println("2.Past workouts");
                int WorkoutChoice = scanner.nextInt();
                if (WorkoutChoice == 1) {
                    //Post new workouts
                    for (Member member : gym.getMemberList()) {
                        if (member.getEmailAddress().equalsIgnoreCase(loginEmail) && member.getGender().equalsIgnoreCase("Male")) {

                            workouts.NewMaleWorkout(member,scanner);
                        }
                        else if (member.getEmailAddress().equalsIgnoreCase(loginEmail) && member.getGender().equalsIgnoreCase("Female")){

                            workouts.NewFemaleWorkout(member,scanner);

                        }
                    }
                }
                else if (WorkoutChoice == 2) {
                    //Past workouts
                    for (Member member : gym.getMemberList()) {
                        if (member.getEmailAddress().equalsIgnoreCase(loginEmail)) {
                            workouts.pastWorkout(member);
                        }
                    }
                }
            }
        }
        else if (memberChoice == 5) {
            WriteToFile.LoginFile("", "");
            gymManagement.consoleApp();
        }
        else if (memberChoice == 6) { // Add this option for attendance
            for (Member member : gym.getMemberList()) {
                if (member.getEmailAddress().equalsIgnoreCase(loginEmail)) {
                    member.viewAttendance();
                    break;
                }
            }
        }
        System.out.println("1.Logout");
        System.out.println("2.Back");
        int back3 = scanner.nextInt();
        if (back3 == 1) {
            WriteToFile.LoginFile("", "");
            gymManagement.consoleApp();
        } else if (back3 == 2) {
            memberPanel(loginEmail, loginPassword);
        }
    }
    public void trainerPanel(String loginEmail,String loginPassword) throws IOException {
        System.out.println("1. View Your Details");
        System.out.println("2. Update Details");
        System.out.println("3. Logout");
        int trainerChoice = scanner.nextInt();

        if (trainerChoice == 1) {
            login.ReadTrainerDetails(loginEmail, loginPassword);
        } else if (trainerChoice == 2) {
            int trainerResetChoice;
            String newData;
            System.out.println("What do you want to reset?");
            System.out.println("1.Reset First Name ");
            System.out.println("2.Reset Last Name ");
            System.out.println("3.Reset Password ");
            System.out.println("4.Reset Date Of Birth ");
            System.out.println("5.Reset Gender ");
            System.out.println("6.Reset Address ");
            System.out.println("7.Reset Age ");
            System.out.println("8.Reset Phone Number ");
            trainerResetChoice = scanner.nextInt();
            System.out.println("Enter new data:");
            newData = scanner.next();
            login.resetTrainerData(trainerResetChoice, newData, loginEmail, loginPassword, gym.getTrainerList());
        } else if (trainerChoice == 3) {
            WriteToFile.LoginFile("", "");
            gymManagement.consoleApp();
        }
        System.out.println("1.Logout");
        System.out.println("2.Back");
        int back3 = scanner.nextInt();
        if (back3 == 1) {
            WriteToFile.LoginFile("", "");
            gymManagement.consoleApp();
        }
        else if(back3==2){
            trainerPanel(loginEmail,loginPassword);
        }
    }

    public void adminPanel() throws IOException {
        {
            System.out.println("1.Member Management\n2.Trainer Management\n3.Equipment management\n4.Account\n5.Attendance Management");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 1) {
                System.out.println("1.Register a new member(3 months package)\n2.Remove a member\n3.Show all members");
                int adminMchoice = scanner.nextInt();
                if (adminMchoice == 1) {
                    // register new member
                    userRegistration.memberRegistration(gym);
                } else if (adminMchoice == 2) {
                    System.out.println("Enter the memberID you want to remove");
                    String removeMemberId = scanner.next();
                    userRemove.memberRemove(gym, removeMemberId);
                } else if (adminMchoice == 3) {
                    readListFile.readFile("MemberFile.csv");
                }
            } else if (adminChoice == 5) {
                // For Attendance Management
                System.out.println("1. Take Attendance\n2. View Attendance for All Members");
                int attendanceChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline character

                if (attendanceChoice == 1) {
                    System.out.println("Enter Class Name (e.g., Class 1):");
                    String className = scanner.nextLine(); // Use nextLine to capture full class name
                    System.out.println("Enter IDs of present members (comma-separated):");
                    String presentIDs = scanner.nextLine(); // Use nextLine to capture the full input
                    WriteToFile.writeAttendance(className, presentIDs, gym.getMemberList());
                } else if (attendanceChoice == 2) {
                    readListFile.readFile("Attendance.csv");
                }
            }else if (adminChoice == 2) {
                System.out.println("1.Register a new trainer\n2.Remove a trainer\n3.Show all trainers");
                System.out.println("4.Pay trainer salary");
                int adminTchoice = scanner.nextInt();
                if (adminTchoice == 1) {
                    userRegistration.trainerRegistration(gym);
                } else if (adminTchoice == 2) {
                    System.out.println("Enter the trainerID you want to remove: ");
                    String removeTrainerID = scanner.next();
                    for (Trainer trainer: gym.getTrainerList() ) {
                        if (removeTrainerID.equals(trainer.getTrainerID())) {
                            userRemove.trainerRemove(gym, removeTrainerID);
                            for (Member member : trainer.getAssignedMembers()) {
                                AssignTrainer.trainerReassign(gym, member);
                            }
                            return;
                        }
                    }


                } else if (adminTchoice == 3) {

                    readListFile.readFile("TrainerFile.csv");
                } else if (adminTchoice == 4) {
                    System.out.println("1.Pay all trainers");
                    System.out.println("2.Pay specific trainer");
                    System.out.println("3.Pay all trainers, excluding some");
                    int paymentChoice = scanner.nextInt();
                    if (paymentChoice == 1) {
                        for (Trainer trainer : gym.getTrainerList()) {
                            Account.trainerPaid(trainer.getTrainerID(), trainer.getSalary());
                        }
                    } else if (paymentChoice == 2) {
                        System.out.println("Enter trainerID:");
                        String trainerID = scanner.next();
                        boolean found = false;
                        for (Trainer trainer : gym.getTrainerList()) {
                            if (trainer.getTrainerID().equals(trainerID)) {
                                found = true;
                                Account.trainerPaid(trainerID, trainer.getSalary());
                            }
                        }
                        if (found == false) {
                            System.out.println("Incorrect trainerID!");
                        }
                    } else if (paymentChoice == 3) {
                        System.out.println("Enter the trainer ID to exclude from payment:");
                        System.out.println("Write the IDs seperating them by coma");
                        String trainerID = scanner.next();
                        String[] excludedID = trainerID.split(",");
                        for (Trainer trainer : gym.getTrainerList()) {
                            boolean isExcluded = false;
                            for (String id : excludedID) {
                                if (trainer.getTrainerID().equals(id)) {
                                    isExcluded = true;
                                }
                            }
                            if (!isExcluded) {
                                Account.trainerPaid(trainer.getTrainerID(), trainer.getSalary());
                            } else {
                                System.out.println(trainer.getTrainerID() +
                                        " is already paid or will get paid later!");
                            }
                        }
                    }
                }
            } else if (adminChoice == 3) {
                System.out.println("1.Add new equipments\n2.Remove equipment\n3.Show all equipments");
                int adminEchoice = scanner.nextInt();
                if (adminEchoice == 1) {
                    //add new equipment
                    equipmentAdd.newEquipment(gym);
                } else if (adminEchoice == 2) {
                    System.out.println("Enter the equipment id you want to remove:");
                    String removeEquipmentID = scanner.next();
                    userRemove.equipmentRemove(gym, removeEquipmentID);

                } else if (adminEchoice == 3) {
                    readListFile.readFile("EquipmentFile.csv");

                }
            } else if (adminChoice == 4) {
                System.out.println("1.View Balance");
                System.out.println("2.View all transaction");
                int accountChoice = scanner.nextInt();
                if (accountChoice == 1) {
                    System.out.println("Account Balance: ");
                    System.out.println(ReadFile.readBalanceFile("BalanceFile.csv"));
                } else if (accountChoice == 2) {
                    ReadFile.readAccountFile();
                }
            }
            System.out.println("1.Logout");
            System.out.println("2.Back");
            int back3 = scanner.nextInt();
            if (back3 == 1) {
                WriteToFile.LoginFile("", "");
                gymManagement.consoleApp();
            } else if (back3 == 2) {
                adminPanel();
            }
        }
    }
}
