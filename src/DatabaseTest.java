import java.awt.Rectangle;
import student.TestCase;

/**
 * 
 */

/**
 * @author Naveen
 * 
 * @version 2021-09-26
 *
 */
public class DatabaseTest extends TestCase {

    private Database db;

    /**
     * setUp() method:
     */
    public void setUp() {
        db = new Database();
    }


    /**
     * Writing test cases for Search method of the database class.
     * 
     */
    public void testSearchDatabase() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        db.insert(rectangleObject);

        systemOut().clearHistory();
        String outputString = "Rectangle found:\n" + "(r1, 1, 1, 80, 100)\n";
        db.search("r1");
        assertEquals(outputString, systemOut().getHistory());

        Rectangle rect5 = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject5 =
            new KVPair<String, Rectangle>("r1", rect5);
        db.insert(rectangleObject5);

        systemOut().clearHistory();
        outputString = "Rectangle found:\n" + "(r1, 1, 1, 80, 100)\n"
            + "(r1, 1, 1, 80, 100)\n";
        db.search("r1");
        assertEquals(outputString, systemOut().getHistory());

        Rectangle rect4 = new Rectangle(1, 1, 1034, 1024);
        KVPair<String, Rectangle> rectangleObject4 =
            new KVPair<String, Rectangle>("t3", rect4);
        db.insert(rectangleObject4);

        systemOut().clearHistory();
        outputString = "Rectangle not found: " + "t3\n";
        db.search("t3");
        assertEquals(outputString, systemOut().getHistory());
    }


    /**
     * Writing test Cases for the positive examples of Insert method of the
     * database class
     * 
     */
    public void testInsertPositiveDatabase() {

        Rectangle rect = new Rectangle(1, 1, 80, 100); // Inserted
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        String outputString = "Rectangle inserted: (r1, 1, 1, 80, 100)\n";
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(0, 0, 1024, 1024); // y is negative
        rectangleObject = new KVPair<String, Rectangle>("t2", rect);
        outputString = "Rectangle inserted: (t2, 0, 0, 1024, 1024)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        outputString = "Rectangle found:\n" + "(r1, 1, 1, 80, 100)\n";
        db.search("r1");
        assertEquals(outputString, systemOut().getHistory());

    }


    /**
     * Writing test cases for negative examples of insert method of the database
     * class.
     * 
     */

    public void testInsertNegativeDatabase() {

        Rectangle rect = new Rectangle(-1, 1, 180, 100); // x is negative
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("t1", rect);
        String outputString = "Rectangle rejected: (t1, -1, 1, 180, 100)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(10, -15, 5, 5); // y is negative
        rectangleObject = new KVPair<String, Rectangle>("t2", rect);
        outputString = "Rectangle rejected: (t2, 10, -15, 5, 5)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(10, 15, -5, 5); // width is negative
        rectangleObject = new KVPair<String, Rectangle>("t2", rect);
        outputString = "Rectangle rejected: (t2, 10, 15, -5, 5)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(10, -15, 5, 5); // height is negative
        rectangleObject = new KVPair<String, Rectangle>("t2", rect);
        outputString = "Rectangle rejected: (t2, 10, -15, 5, 5)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(10, 15, 0, 5); // width is 0
        rectangleObject = new KVPair<String, Rectangle>("t2", rect);
        outputString = "Rectangle rejected: (t2, 10, 15, 0, 5)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(10, 15, 5, 0); // height is negative
        rectangleObject = new KVPair<String, Rectangle>("t2", rect);
        outputString = "Rectangle rejected: (t2, 10, 15, 5, 0)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(1, 1, 1034, 100); // x + width > 1024 : rejected
        rectangleObject = new KVPair<String, Rectangle>("t3", rect);
        outputString = "Rectangle rejected: (t3, 1, 1, 1034, 100)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(-10, 1, 5, 100); // x + width < 0: rejected
        rectangleObject = new KVPair<String, Rectangle>("t3", rect);
        outputString = "Rectangle rejected: (t3, -10, 1, 5, 100)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(1, 1, 100, 2000); // y + height > 1024 : rejected
        rectangleObject = new KVPair<String, Rectangle>("t3", rect);
        outputString = "Rectangle rejected: (t3, 1, 1, 100, 2000)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        rect = new Rectangle(1, -10, 10, 5); // y + height < 0 : rejected
        rectangleObject = new KVPair<String, Rectangle>("t3", rect);
        outputString = "Rectangle rejected: (t3, 1, -10, 10, 5)\n";
        systemOut().clearHistory();
        db.insert(rectangleObject);
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        outputString = "Rectangle not found: " + "t1\n";
        db.search("t1");
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        outputString = "Rectangle not found: " + "t2\n";
        db.search("t2");
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        outputString = "Rectangle not found: " + "t3\n";
        db.search("t3");
        assertEquals(outputString, systemOut().getHistory());
    }


    /**
     * Writing test cases for Region Search method of the database class.
     */
    public void testRegionSearchDatabase() {
        Rectangle rect = new Rectangle(10, 10, 5, 5);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        db.insert(rectangleObject);

        rect = new Rectangle(15, 15, 5, 5);
        rectangleObject = new KVPair<String, Rectangle>("r2", rect);
        db.insert(rectangleObject);

        rect = new Rectangle(15, 15, 5, 5);
        rectangleObject = new KVPair<String, Rectangle>("r2", rect);
        db.insert(rectangleObject);

        rect = new Rectangle(7, 7, 10, 10);
        rectangleObject = new KVPair<String, Rectangle>("r3", rect);
        db.insert(rectangleObject);

        rect = new Rectangle(45, 50, 110, 90);
        rectangleObject = new KVPair<String, Rectangle>("r4", rect);
        db.insert(rectangleObject);

        systemOut().clearHistory();
        db.regionsearch(-5, -5, 20, 20);
        String outputString =
            "Rectangles intersecting region (-5, -5, 20, 20): \n"
                + "(r1, 10, 10, 5, 5)\n" + "(r3, 7, 7, 10, 10)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.regionsearch(-5, -5, -20, 20); // width is negative rectangle is
                                          // rejected.
        outputString = "Rectangle rejected: " + "(-5, -5, -20, 20)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.regionsearch(-5, -5, 20, -20); // height is negative rectangle is
                                          // rejected.
        outputString = "Rectangle rejected: " + "(-5, -5, 20, -20)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.regionsearch(-5, -5, 0, 20); // width is 0 rectangle is rejected.
        outputString = "Rectangle rejected: " + "(-5, -5, 0, 20)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.regionsearch(-5, -5, 20, 0); // height is 0 rectangle is rejected.
        outputString = "Rectangle rejected: " + "(-5, -5, 20, 0)\n";
        assertEquals(outputString, systemOut().getHistory());
    }


    /**
     * Test cases for testing Database method of Database.
     */

    public void testRegionSearchCoverageDatabase() {
        Rectangle rect = new Rectangle(10, 20, 8, 10);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        db.insert(rectangleObject);

        rect = new Rectangle(120, 117, 93, 706);
        rectangleObject = new KVPair<String, Rectangle>("r2", rect);
        db.insert(rectangleObject);

        systemOut().clearHistory();
        db.regionsearch(20, 25, 7, 9);
        String outputString =
            "Rectangles intersecting region (20, 25, 7, 9): \n";
        assertEquals(outputString, systemOut().getHistory());

    }


    /**
     * Writing test cases for Intersection method of the database class.
     * 
     */
    public void testIntersectionDatabase() {
        Rectangle rect = new Rectangle(10, 10, 5, 5);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        db.insert(rectangleObject);

        Rectangle rect1 = new Rectangle(15, 15, 5, 5);
        KVPair<String, Rectangle> rectangleObject1 =
            new KVPair<String, Rectangle>("r2", rect1);
        db.insert(rectangleObject1);

        Rectangle rect2 = new Rectangle(15, 15, 5, 5);
        KVPair<String, Rectangle> rectangleObject2 =
            new KVPair<String, Rectangle>("r2", rect2);
        db.insert(rectangleObject2);

        Rectangle rect3 = new Rectangle(7, 7, 10, 10);
        KVPair<String, Rectangle> rectangleObject3 =
            new KVPair<String, Rectangle>("r3", rect3);
        db.insert(rectangleObject3);

        Rectangle rect4 = new Rectangle(25, 30, 110, 90);
        KVPair<String, Rectangle> rectangleObject4 =
            new KVPair<String, Rectangle>("r5", rect4);
        db.insert(rectangleObject4);

        systemOut().clearHistory();
        db.intersections();
        String outputString = "Intersections pairs: \n"
            + "(r1, 10, 10, 5, 5 | r3, 7, 7, 10, 10)\n"
            + "(r2, 15, 15, 5, 5 | r2, 15, 15, 5, 5)\n"
            + "(r2, 15, 15, 5, 5 | r3, 7, 7, 10, 10)\n"
            + "(r2, 15, 15, 5, 5 | r2, 15, 15, 5, 5)\n"
            + "(r2, 15, 15, 5, 5 | r3, 7, 7, 10, 10)\n"
            + "(r3, 7, 7, 10, 10 | r1, 10, 10, 5, 5)\n"
            + "(r3, 7, 7, 10, 10 | r2, 15, 15, 5, 5)\n"
            + "(r3, 7, 7, 10, 10 | r2, 15, 15, 5, 5)\n";
        assertEquals(outputString, systemOut().getHistory());
    }


    /**
     * Writing test cases for Remove by key method of the database class.
     */
    public void testRemoveByNameDatabase() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        db.insert(rectangleObject);

        systemOut().clearHistory();
        String outputString = "Rectangle found:\n" + "(r1, 1, 1, 80, 100)\n";
        db.search("r1");
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.remove("r1");
        outputString = "Rectangle removed: (r1, 1, 1, 80, 100)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.remove("r1");
        outputString = "Rectangle not removed: (r1)\n";
        assertEquals(outputString, systemOut().getHistory());

        Rectangle rect5 = new Rectangle(1, 1, 90, 100);
        KVPair<String, Rectangle> rectangleObject5 =
            new KVPair<String, Rectangle>("r2", rect5);
        db.insert(rectangleObject5);

        Rectangle rect4 = new Rectangle(1, 1, 100, 120);
        KVPair<String, Rectangle> rectangleObject4 =
            new KVPair<String, Rectangle>("r2", rect4);
        db.insert(rectangleObject4);

        systemOut().clearHistory();
        db.remove("r2");
        outputString = "Rectangle removed: (r2, 1, 1, 100, 120)\n";
        assertEquals(outputString, systemOut().getHistory());
    }


    /**
     * Writing test cases for Remove by value method of the database class.
     * 
     */
    public void testRemoveByValueDatabase() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        db.insert(rectangleObject);

        systemOut().clearHistory();
        String outputString = "Rectangle found:\n" + "(r1, 1, 1, 80, 100)\n";
        db.search("r1");
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.remove(1, 1, 80, 100);
        outputString = "Rectangle removed: (r1, 1, 1, 80, 100)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        db.remove(1, 1, 80, 100);
        outputString = "Rectangle not removed: (1, 1, 80, 100)\n";
        assertEquals(outputString, systemOut().getHistory());

        Rectangle rect5 = new Rectangle(1, 1, 90, 100);
        KVPair<String, Rectangle> rectangleObject5 =
            new KVPair<String, Rectangle>("r2", rect5);
        db.insert(rectangleObject5);

        Rectangle rect4 = new Rectangle(1, 1, 100, 120);
        KVPair<String, Rectangle> rectangleObject4 =
            new KVPair<String, Rectangle>("r2", rect4);
        db.insert(rectangleObject4);

        systemOut().clearHistory();
        db.remove(1, 1, 90, 100);
        outputString = "Rectangle removed: (r2, 1, 1, 90, 100)\n";
        assertEquals(outputString, systemOut().getHistory());

        systemOut().clearHistory();
        rect = new Rectangle(-1, 0, 10, 10);
        db.remove(-1, 0, 10, 10);
        outputString = "Rectangle rejected: (-1, 0, 10, 10)\n";
        assertEquals(outputString, systemOut().getHistory());
    }

}
