import java.util.ArrayList;

public class Gym {
    static ArrayList<Member> members=new ArrayList<>();
    ArrayList<Trainer> trainers=new ArrayList<>();
    ArrayList<Equipment>equipments = new ArrayList<>();

    public static ArrayList<Member> getMemberList(){
        return members;
    }

    public void addMembers(Member member){
        members.add(member);
    }
    public void removeMembers(Member member){
        members.remove(member);
    }

    public ArrayList<Trainer> getTrainerList(){
        return trainers;
    }
    public void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }
    public void removeTrainers(Trainer trainer){
        trainers.remove(trainer);
    }
    public ArrayList<Equipment> getEquipmentsList(){
        return equipments;
    }

    public void addEquipment(Equipment equipment){
        equipments.add(equipment);
    }
    public void removeEquipment(Equipment equipment){
        equipments.remove(equipment);

    }


}