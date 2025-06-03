import java.util.Scanner;

/**
 * This class reads three numbers from the user and determines if they can form a triangle.
 * It uses the isTriangle method to apply the triangle inequality logic.
 */
public class Demo {

    public static void main(String[] args) {
        // Create a Scanner to read from standard input (System.in)
        Scanner reader = new Scanner(System.in);  
        
        // Prompt and read the three triangle sides
        System.out.println("Enter side 1: ");
        int side_1 = reader.nextInt();

        System.out.println("Enter side 2: ");
        int side_2 = reader.nextInt();

        System.out.println("Enter side 3: ");
        int side_3 = reader.nextInt();

        // Call the isTriangle method to check if the input forms a triangle
        if (isTriangle(side_1, side_2, side_3)) {
            System.out.println("This is a triangle.");
        } else {
            System.out.println("This is not a triangle.");
        }

        // Always close the scanner to free up resources
        reader.close();
    }

    /**
     * Checks whether three sides form a valid triangle using the triangle inequality theorem.
     * The sum of any two sides must be greater than the third.
     *
     * @param a side 1
     * @param b side 2
     * @param c side 3
     * @return true if the three sides form a triangle; false otherwise
     */
    public static boolean isTriangle(double a, double b, double c) {
        if ((a + b > c) &&
            (a + c > b) && // should be a + c > b
            (b + c > a)) {
            return true; 
        }
        return false;
    }
}