import student.TestCase;

/**
 * 
 */

/**
 * @author Naveen Gupta (naveengupta)
 * 
 * @version 2021-09-26
 *
 */
public class Rectangle1Test extends TestCase {

    /**
     * Writing test cases for the main method of the Rectangle class
     */

    @SuppressWarnings("static-access")
    public void testMainMethodRectangle() {
        String expectedAnswer = "Invalid file";
        Rectangle1.main(new String[] { "Data/P1test1.txt" });
        assertTrue(getName(), true);
        assertTrue(equalsRegex(systemOut().getHistory(), "[\\s\\S]*"));
        Rectangle1.main(new String[] { "Data/P1test2.txt" });
        Rectangle1.main(new String[] { " " });
        assertTrue(systemOut().getHistory().contains(expectedAnswer));
        Rectangle1.main(new String[] { "Data/P1test3.txt" });
        assertTrue(systemOut().getHistory().contains(expectedAnswer));

        Rectangle1 testRectangle = new Rectangle1();
        testRectangle.main(new String[] { "./Data/P1test1.txt" });
        assertTrue(equalsRegex(systemOut().getHistory(), "[\\s\\S]*"));
        testRectangle.main(new String[] { "./Data/P1test2.txt" });

    }
}
