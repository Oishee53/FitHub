import java.util.ArrayList;

public class Trainer extends User {
    private String trainerID;
    private String shift;
    private double salary;
    private int availableSeats;
    ArrayList<Member> assignedMembers = new ArrayList<>();

    public Trainer(String trainerID, String firstName, String lastName, String emailAddress, String password,
                   String phoneNumber, String DOB, String gender, String address, int age,
                   String shift, double salary,int availableSeats) {

        super(firstName, lastName, emailAddress, password, phoneNumber,DOB, gender, address, age);


        this.trainerID = trainerID;
        this.shift = shift;
        this.salary = salary;
        this.availableSeats = availableSeats;
    }

    public void addAssignedMember(Member member){
        assignedMembers.add(member);
    }
    public void removeAssignedMember(Member member){
        assignedMembers.remove(member);
    }

    @Override
    public void showDetails() {
        System.out.println("Trainer Name: " + getFullName());
        System.out.println("Shift: " + shift);
    }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public ArrayList<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(ArrayList<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }
}