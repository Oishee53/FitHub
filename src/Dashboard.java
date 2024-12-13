import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dashboard {
    public void graph(Member member) {
        //Total calorie burn for suggested exercises
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("WorkoutFile.csv"))) {
            String line;
            // Read each line from the file until the end
            double calorie = 0;
            double january=0;
            double february=0;
            double march=0;
            double april=0;
            double may=0;
            double june=0;
            double july=0;
            double august=0;
            double september=0;
            double october=0;
            double november=0;
            double december=0;
            while ((line = bufferedReader.readLine()) != null) {

                // Split the line into data columns
                String[] data = line.split(",");
                String[] date = data[1].split("/");

                //matches with members mail
                if (member.getEmailAddress().equalsIgnoreCase(data[0]))
                    calorie = calorie + Double.parseDouble(data[4]);
                

                if(member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("1") )
                    january = january + Double.parseDouble(data[4]);

                else if(member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("2") )
                    february = february + Double.parseDouble(data[4]);
                else if(member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("3") )
                    march = march + Double.parseDouble(data[4]);
                 else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("4"))
                    april = april + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("5"))
                    may = may + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("6"))
                    june = june + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("7"))
                    july = july + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("8"))
                    august = august + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("9"))
                    september = september + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("10"))
                    october = october + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("11"))
                    november = november + Double.parseDouble(data[4]);
                else if (member.getEmailAddress().equalsIgnoreCase(data[0]) && date[1].equals("12"))
                    december = december + Double.parseDouble(data[4]);

            }
            System.out.println("Total calories burned:" + calorie);
            System.out.println("Calories burned in January:" + january +"           "+"Calories burned in February:" + february);
            System.out.println("Calories burned in March:" + march +"           "+"Calories burned in April:" + april);
            System.out.println("Calories burned in May:" + may +"           "+"Calories burned in June:" + june);
            System.out.println("Calories burned in July:" + july +"           "+"Calories burned in August:" + august);
            System.out.println("Calories burned in September:" + september +"           "+"Calories burned in October:" + october);
            System.out.println("Calories burned in November:" + november +"           "+"Calories burned in December:" + december);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


        System.out.println("|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n");
        System.out.println("|_   _   _   _   _   _   _   _   _   _   _   _   \nJan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec");


    }
}
