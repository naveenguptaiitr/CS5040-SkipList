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
public class CommandProcessorTest extends TestCase {
    
    private CommandProcessor cmdProcessor;

    /**
     * This is a member function of the CommandProcessor Class
     * 
     */

    public void setUp() {
        
        cmdProcessor = new CommandProcessor();

    }

    /**
     * This is a testMain Class
     * 
     */
    public void testMain() {
        cmdProcessor.processor("insert r2 15 15 15 15");
        assertEquals("Rectangle inserted: (r2, 15, 15, 15, 15)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProcessor.processor("insert r1 -15 15 15 15");
        assertEquals("Rectangle rejected: (r1, -15, 15, 15, 15)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProcessor.processor("remove r2");
        assertEquals("Rectangle removed: (r2, 15, 15, 15, 15)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProcessor.processor("remove 1 2 3 4");
        assertEquals("Rectangle not removed: (1, 2, 3, 4)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProcessor.processor("regionsearch 900 5 0 0");
        assertEquals("Rectangle rejected: (900, 5, 0, 0)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProcessor.processor("intersections");
        assertEquals("Intersections pairs: \n", systemOut().getHistory());
        systemOut().clearHistory();

//        cmdProcessor.processor("removal r5");
//        assertEquals("Invalid Output\n", systemOut().getHistory());
//        systemOut().clearHistory();

        cmdProcessor.processor("search r4");
        assertEquals("Rectangle not found: r4\n", systemOut().getHistory());
        systemOut().clearHistory();

    }

}
