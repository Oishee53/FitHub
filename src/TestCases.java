import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

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




    private static final String TEST_CSV_FILE = "WorkoutFile.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Creating a test CSV file with sample data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.write("test@example.com,01/01/2025,Exercise1,30,200\n");
            writer.write("test@example.com,02/01/2025,Exercise2,45,250\n");
            writer.write("test@example.com,03/01/2025,Exercise3,60,300\n");
            writer.write("test@example.com,04/01/2025,Exercise4,30,150\n");
            writer.write("test@example.com,05/01/2025,Exercise5,45,180\n");
            writer.write("test@example.com,06/01/2025,Exercise6,60,220\n");
            writer.write("test@example.com,07/01/2025,Exercise7,30,170\n");
            writer.write("test@example.com,08/01/2025,Exercise8,45,210\n");
            writer.write("test@example.com,09/01/2025,Exercise9,60,190\n");
            writer.write("test@example.com,10/01/2025,Exercise10,30,250\n");
            writer.write("test@example.com,11/01/2025,Exercise11,45,230\n");
            writer.write("test@example.com,12/01/2025,Exercise12,60,300\n");
        }
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after the test
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testGraphMethod() {
        Member testMember = new Member("test@example.com");
        Dashboard dashboard = new Dashboard();
        dashboard.graph(testMember);

    }

    }


