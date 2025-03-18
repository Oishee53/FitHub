import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class TestCase {
    private TDEECalculator tdeeCalculator;
    private Member testMember;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        tdeeCalculator = new TDEECalculator();
        testMember = new Member();

        testMember.setGender("male");
        testMember.setWeight(70);
        testMember.setHeight(175);
        testMember.setAge(25);
        testMember.setGoal("WeightLoss");

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCalculatorOutput() {
        // Call method
        tdeeCalculator.calculator(testMember);

        // Check printed output
        String expectedOutput = "Your daily calorie consumption will be ";
        assertTrue("Output should contain expected text", outContent.toString().contains(expectedOutput));


}
}

