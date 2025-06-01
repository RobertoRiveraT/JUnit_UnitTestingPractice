/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import java.io.*;

/**
 * Unit tests for the Demo class.
 * This suite includes boundary tests, equivalence partitioning, and edge cases
 * for the isTriangle() method:

    ✅ Positive test — Valid triangle
    ❌ Negative test — Invalid triangle
    📏 Boundary test — Edges where a + b = c
    🧮 Equivalence Partitioning — Typical values from valid/invalid groups
    ⚠️ Edge Case test — Zeros, negatives, very large numbers

 */
public class DemoTest {

    // ✅ Positive Test Case - Typical valid triangle
    @Test
    public void testIsTriangle_ValidTriangle() {
        assertTrue(Demo.isTriangle(3, 4, 5));
    }

    // ❌ Negative Test Case - Does not satisfy triangle inequality
    @Test
    public void testIsTriangle_InvalidTriangle() {
        assertFalse(Demo.isTriangle(1, 2, 3));
    }

    // 📏 Boundary Test - Sum of two sides equals the third
    @Test
    public void testIsTriangle_BoundaryCaseEquals() {
        assertFalse(Demo.isTriangle(5, 5, 10)); // 5+5 = 10 → Not a triangle
    }

    // 📏 Boundary Test - Sum just greater than third
    @Test
    public void testIsTriangle_BoundaryJustValid() {
        assertTrue(Demo.isTriangle(5, 5, 9.999));
    }

    // 🧮 Equivalence Partitioning - Valid group
    @Test
    public void testIsTriangle_EquivalenceValid() {
        assertTrue(Demo.isTriangle(6, 7, 8));
    }

    // 🧮 Equivalence Partitioning - Invalid group
    @Test
    public void testIsTriangle_EquivalenceInvalid() {
        assertFalse(Demo.isTriangle(1, 1, 5));
    }

    // ⚠️ Edge Case - All sides zero
    @Test
    public void testIsTriangle_AllSidesZero() {
        assertFalse(Demo.isTriangle(0, 0, 0));
    }

    // ⚠️ Edge Case - One side zero
    @Test
    public void testIsTriangle_OneSideZero() {
        assertFalse(Demo.isTriangle(0, 3, 4));
    }

    // ⚠️ Edge Case - Negative side
    @Test
    public void testIsTriangle_NegativeSide() {
        assertFalse(Demo.isTriangle(-1, 2, 2));
    }

    // ⚠️ Edge Case - Very large numbers
    @Test
    public void testIsTriangle_LargeNumbers() {
        assertTrue(Demo.isTriangle(1e9, 1e9, 1e9));
    }

    /**
     * Test Demo.main() with a valid triangle input.
     * Simulates user typing "3\n4\n5\n" as triangle sides and checks the output message.
     */
    @Test
    public void testMain_ValidTriangleOutput() throws Exception {
        // Step 1: Prepare simulated input as if the user typed it in the console
        String input = "3\n4\n5\n"; // each \n simulates pressing "Enter"
        
        // Step 2: Backup the original System.in and System.out so we can restore them later
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        // Step 3: Create a fake System.in from the input string
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        
        // Step 4: Create a stream to capture what gets printed to System.out
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Step 5: Replace System.in and System.out with our fake versions
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Step 6: Call the method we want to test (main), which will now use our simulated input/output
        Demo.main(new String[]{});

        // Step 7: Restore the original System.in and System.out so other tests or code aren’t affected
        System.setIn(originalIn);
        System.setOut(originalOut);

        // Step 8: Extract the captured output and verify it contains the expected result
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("This is a triangle."));
    }

    /**
     * Test Demo.main() with an invalid triangle input.
     * Simulates user typing "1\n2\n3\n" and checks the output message.
     */
    @Test
    public void testMain_InvalidTriangleOutput() throws Exception {
        String input = "1\n2\n3\n"; // Not a valid triangle (1 + 2 = 3)

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        Demo.main(new String[]{});

        System.setIn(originalIn);
        System.setOut(originalOut);

        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("This is not a triangle."));
    }
}