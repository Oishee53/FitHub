import java.io.*;
import java.util.Scanner;

public class GymManagement {
    static GymManagement gymManagement = new GymManagement();
    UserRegistration userRegistration = new UserRegistration();
    UserRemove userRemove = new UserRemove();
    EquipmentAdd equipmentAdd = new EquipmentAdd();
    static LoadData loadData = new LoadData();
    ReadFile readListFile=new ReadFile();
    Login login = new Login();
    static Gym gym = new Gym();
    WeightLoseGoal weightLoseGoal = new WeightLoseGoal();
    StrengthBuildingGoal strengthBuildingGoal = new StrengthBuildingGoal();
    TDEECalculator tdeeCalculator= new TDEECalculator();
    Scanner scanner = new Scanner(System.in);


    //main method
    public static void main(String[] args) throws IOException {
        String[] csvFiles = {
                "MemberFile.csv",
                "TrainerFile.csv",
                "Member and Trainer.csv",
                "EquipmentFile.csv",
                "LoginInfoFile.csv",
                "Male Exercise.csv",
                "Female Exercise.csv"
        };

        // Create each file
        for (String csvFile : csvFiles) {
            createCSVFile(csvFile);
        }





        loadData.LoadMemberDetails(gym);
        loadData.LoadTrainerDetails(gym);
        loadData.LoadEquipmentDetails(gym);
        gymManagement.consoleApp();

    }

    //method for all operation
    public void consoleApp() throws IOException {


        System.out.println(".............FitHub...............");
        System.out.println("Enter your choice:");
        System.out.println("1.Admin\n2.Member\n3.Trainer");
        int loginChoice = scanner.nextInt();
        if (loginChoice == 1) {
            //Admin
            System.out.println("1.Member Management\n2.Trainer Management\n3.Equipment management\n");
            int adminChoice = scanner.nextInt();
            if (adminChoice == 1) {
                System.out.println("1.Register a new member\n2.Remove a member\n3.Show all members");
                int adminMchoice = scanner.nextInt();
                if (adminMchoice == 1) {
                    // register new member
                    userRegistration.memberRegistration(gym);
                    gymManagement.consoleApp();
                } else if (adminMchoice == 2) {
                    System.out.println("Enter the member email id you want to remove");
                    String removeMemberId = scanner.next();
                    userRemove.memberRemove(gym, removeMemberId);
                    gymManagement.consoleApp();

                } else if (adminMchoice == 3) {
                    readListFile.readFile("MemberFile.csv");

                }
            } else if (adminChoice == 2) {
                System.out.println("1.Register a new trainer\n2.Remove a trainer\n3.Show all trainers");
                int adminTchoice = scanner.nextInt();
                if (adminTchoice == 1) {
                    // register new member
                    userRegistration.trainerRegistration(gym);
                    gymManagement.consoleApp();
                } else if (adminTchoice == 2) {
                    System.out.println("Enter the trainer email id you want to remove");
                    String removeTrainerID = scanner.next();
                    userRemove.trainerRemove(gym, removeTrainerID, loadData);
                    gymManagement.consoleApp();

                } else if (adminTchoice == 3) {

                    readListFile.readFile("TrainerFile.csv");
                }
            } else if (adminChoice == 3) {
                System.out.println("1.Add new equipments\n2.Remove equipment\n3.Show all equipments");
                int adminEchoice = scanner.nextInt();
                if (adminEchoice == 1) {
                    //add new equipment
                    equipmentAdd.newEquipment(gym);
                    gymManagement.consoleApp();
                } else if (adminEchoice == 2) {
                    System.out.println("Enter the equipment id you want to remove:");
                    String removeEquipmentID = scanner.next();
                    userRemove.equipmentRemove(gym, removeEquipmentID, loadData);

                    gymManagement.consoleApp();
                } else if (adminEchoice == 3) {
                    readListFile.readFile("EquipmentFile.csv");

                }
            }

        }//member
        else if (loginChoice == 2) {
            System.out.print("Enter your email:\n");
            String loginEmail = scanner.next();
            System.out.print("Enter your password:\n");
            String loginPassword = scanner.next();
            String filename = "MemberFile.csv";
            boolean isAuthenticated = Login.authenticateLogin(loginEmail, loginPassword, filename);
            if (isAuthenticated) {
                System.out.println("1. View Your Details");
                System.out.println("2. Update Details");
                System.out.println("3. View Suggestions Based On Your Goal");
                System.out.println("4. Track your progress");
                System.out.println("5. Logout");
                int memberChoice = scanner.nextInt();

                if (memberChoice == 1) {
                    login.ReadMemberDetails(loginEmail, loginPassword);
                } else if (memberChoice == 2) {
                    int memberResetChoice;
                    String newData;
                    System.out.println("1.Reset First Name: ");
                    System.out.println("2.Reset Last Name: ");
                    // System.out.println("3.Reset Email: ");
                    System.out.println("4.Reset Password: ");
                    System.out.println("5. Reset Date Of Birth: ");
                    System.out.println("6.Gender: ");
                    System.out.println("7.Reset Address: ");
                    System.out.println("8.Reset Weight: ");
                    System.out.println("9.Reset Height: ");
                    System.out.println("10.Age: ");
                    memberResetChoice = scanner.nextInt();
                    System.out.println("Enter new data:");
                    newData = scanner.next();
                    login.resetMemberData(memberResetChoice, newData, loginEmail, loginPassword, gym.getMemberList());
                }
                else if (memberChoice == 3) {
                    for (Member member : gym.getMemberList()) {


                        if (member.getEmailAddress().equalsIgnoreCase(loginEmail)) {

                            if (member.getGoal().equalsIgnoreCase("Weight Loss")) {
                                weightLoseGoal.suggestion(member,tdeeCalculator);
                                return;
                            } else if (member.getGoal().equalsIgnoreCase("Strength Building")) {
                                strengthBuildingGoal.suggestion(member,tdeeCalculator);
                            } else {
                                System.out.println("Goal did not match either Weight Loss or Strength Building");
                            }
                        }
                    }
                }
                else if (memberChoice == 4){





                }

                else if (memberChoice == 5) {
                    WriteToFile.LoginFile("", "");
                    gymManagement.consoleApp();
                }
            } else {
                System.out.println("Wrong email or password:");
                gymManagement.consoleApp();
            }
            System.out.println("1.Logout");
            int back2 = scanner.nextInt();
            if (back2 == 1) {
                WriteToFile.LoginFile("", "");
                gymManagement.consoleApp();
            }
        }
        else if (loginChoice == 3) {
            System.out.print("Enter your email:\n");
            String loginEmail = scanner.next();
            System.out.print("Enter your password:\n");
            String loginPassword = scanner.next();
            String filename = "TrainerFile.csv";
            boolean isAuthenticated = Login.authenticateLogin(loginEmail, loginPassword, filename);
            if (isAuthenticated) {
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
                    //  System.out.println("3.Reset Email ");
                    System.out.println("4.Reset Password ");
                    System.out.println("5. Reset Date Of Birth ");
                    System.out.println("6.Reset Gender ");
                    System.out.println("7.Reset Address ");
                    System.out.println("8.Reset Shift ");
                    System.out.println("9.Reset Age ");
                    trainerResetChoice = scanner.nextInt();
                    System.out.println("Enter new data:");
                    newData = scanner.next();
                    login.resetTrainerData(trainerResetChoice, newData, loginEmail, loginPassword, gym.getTrainerList());
                } else if (trainerChoice == 3) {
                    WriteToFile.LoginFile("", "");
                    gymManagement.consoleApp();
                }
            } else {
                System.out.println("Wrong email or password:");
                gymManagement.consoleApp();
            }
            System.out.println("1.Logout");
            int back3 = scanner.nextInt();
            if (back3 == 1) {
                WriteToFile.LoginFile("", "");
                gymManagement.consoleApp();
            }
        }
    }
    private static void createCSVFile(String fileName) {
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
}


//Oishee test











