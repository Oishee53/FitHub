public class TDEECalculator {
    public void calculator(Member member){
        double BMR,protein,carb,fat,estimatedWeight = 0;

        if(member.getGender().equalsIgnoreCase("male"))
        {BMR = 10*member.getWeight()+6.25* member.getHeight()-5* member.getAge()+5;}
        else
        {BMR=10*member.getWeight()+6.25* member.getHeight()-5* member.getAge()-161;}
        double TDEE=BMR*1.55;//by default moderately active
        if(member.getGoal().equalsIgnoreCase("WeightLoss")){

           TDEE= TDEE-500;
           estimatedWeight=(500*90)/7700;//kg lose
        }
        else if (member.getGoal().equalsIgnoreCase("StrengthBuilding")) {
           TDEE = TDEE+400;
            estimatedWeight=(400*90)/7700;//kg gain
        }
        protein=(TDEE*30)/100;
        fat=(TDEE*30)/100;
        carb=(TDEE*40)/100;
        if(member.getGoal().equalsIgnoreCase("WeightLoss"))
        System.out.println("Your diet plan for "+estimatedWeight+" kg weight loss in 3 months:");
        else System.out.println("Your diet plan for "+estimatedWeight+" kg weight gain in 3 months:");
        System.out.println("Your daily calorie consumption will be "+TDEE+" cal.\n");
        System.out.println(protein+" cal protein\n");
        System.out.println(fat+" cal fat\n");
        System.out.println(carb+" cal carb\n");
        System.out.println("            \n");
    }
}
