public class TDEECalculator {
    public void calculator(Member member){
        double BMR,protein,carb,fat;

        if(member.getGender().equalsIgnoreCase("male"))
        {BMR = 10*member.getWeight()+6.25* member.getHeight()-5* member.getAge()+5;}
        else
        {BMR=10*member.getWeight()+6.25* member.getHeight()-5* member.getAge()-161;}
        double TDEE=BMR*1.55;//by default moderately active
        if(member.getGoal().equalsIgnoreCase("Weight loss")){

           TDEE= TDEE-500;
        }
        else if (member.getGoal().equalsIgnoreCase("Strength building")) {
           TDEE = TDEE+400;
        }
        protein=(TDEE*30)/100;
        fat=(TDEE*30)/100;
        carb=(TDEE*40)/100;
        System.out.println("Your daily calorie conumption will be"+TDEE+"cal.\n");
        System.out.println(protein+"g protein\n");
        System.out.println(fat+"g fat\n");
        System.out.println(carb+"cal carb");
    }
}
