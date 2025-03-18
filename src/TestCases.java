import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TestCases {
       @Test
        void testCalculator() {
            // Arrange
            Member member = new Member();
            member.setGender("male");
            member.setWeight(70);
            member.setHeight(175);
            member.setAge(25);
            member.setGoal("WeightLoss");

            TDEECalculator calculator = new TDEECalculator();

            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Act
            calculator.calculator(member);

            // Reset System.out
            System.setOut(originalOut);

            // Assert
            String output = outputStream.toString();
            assertTrue(output.contains("Daily Calorie Consumption"));
            assertTrue(output.contains("Protein (cal)"));
            assertTrue(output.contains("Fat (cal)"));
            assertTrue(output.contains("Carbohydrates (cal)"));
        }
    }


