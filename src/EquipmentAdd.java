import java.util.Scanner;
import java.util.concurrent.locks.Condition;

public class EquipmentAdd {
    Scanner scanner = new Scanner(System.in);
    public void newEquipment(Gym gym){
        System.out.println("Enter equipment ID");
        String equipmentID=scanner.next();
        System.out.println("Enter equipment name:");
        String equipmentName=scanner.next();
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Condition");
        String condition = scanner.next();
        Equipment equipment = new Equipment(equipmentID,equipmentName,quantity,condition);
        gym.addEquipment(equipment);
        WriteToFile.writeInventory(gym.getEquipmentsList(), true);
        System.out.println("Equipment is added successfully!!");

    }
}
