public class Member extends User {

    private int weight;
    private double height;
    private String trainerChose;
    private String goal;
    private String specificGoal;

    public Member(String firstName, String lastName, String emailAddress, String password,
                  String phoneNumber, String DOB, String gender, String address, int age,
                  int weight, double height,String trainerChose,String goal,String specificGoal) {

        super(firstName, lastName, emailAddress, password, phoneNumber,DOB, gender, address, age);


        this.weight = weight;
        this.height = height;
        this.trainerChose = trainerChose;
        this.goal = goal;
        this.specificGoal=specificGoal;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight){
        this.weight=weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height){
        this.height=height;
    }

    public String getTrainerChose() {
        return trainerChose;
    }

    public void setTrainerChose(String trainerChose) {
        this.trainerChose = trainerChose;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
    public String getSpecificGoal(){return specificGoal;}

    public void setSpecificGoal(String specificGoal) {
        this.specificGoal = specificGoal;
    }

    @Override
    public void showDetails() {
        System.out.println("Name: "+ getFirstName() + " " + getLastName());
        System.out.println("Contact Info: "+ getPhoneNumber());
        System.out.println("Gender: " + getGender());
        System.out.println("Address:" + getAddress());
        System.out.println("Age:" + getAge());
        System.out.println("Weight: " + getWeight());
        System.out.println("Height: " + getHeight());

    }
}
