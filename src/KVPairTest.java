import static org.junit.Assert.assertNotEquals;
import java.awt.Rectangle;
import student.TestCase;

/**
 * 
 */

/**
 * The purpose of this file is to test possible cases
 * for the KVPair class
 * 
 * @author Naveen Gupta (naveengupta)
 * 
 * @version 2021-09-26
 * 
 * @param <K>
 *            Key to be used
 * @param <V>
 *            Value to be associated with the key
 *
 */
public class KVPairTest<V, K> extends TestCase {

    private KVPair<String, Rectangle> kvPair;
    private String keyName = "r1";
    private Rectangle value = new Rectangle(1, 1, 80, 100);

    /**
     * setUp() method:
     */
    public void setUp() {

        kvPair = new KVPair<String, Rectangle>(keyName, value);

    }


    /**
     * Test Case for getKey in KVPair
     */
    public void testKVPairGetKey() {

        assertEquals("r1", kvPair.getKey());
        assertNotEquals("r2", kvPair.getKey());

    }


    /**
     * Test Case for getValue in KVPair
     */
    public void testKVPairGetValue() {

        assertEquals(value, kvPair.getValue());
        assertEquals(1, kvPair.getValue().x);
        assertEquals(1, kvPair.getValue().y);
        assertEquals(80, kvPair.getValue().width);
        assertEquals(100, kvPair.getValue().height);

    }


    /**
     * Test Case for toSTring method in KVPair class
     */
    public void testKVPairToString() {

        assertEquals("(r1, java.awt.Rectangle[x=1,y=1,width=80,height=100])",
            kvPair.toString());
    }


    /**
     * Test Case for compare To method in kvPair class
     */
    public void testkvPairCompareTo() {

        Rectangle rectangleObj = new Rectangle(1, 2, 90, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("p1", rectangleObj);
        assertEquals(2, kvPair.compareTo(rectangleObject));

        rectangleObject = new KVPair<String, Rectangle>("r1", rectangleObj);
        assertEquals(0, kvPair.compareTo(rectangleObject));

        rectangleObject = new KVPair<String, Rectangle>("t1", rectangleObj);
        assertEquals(-2, kvPair.compareTo(rectangleObject));
    }
}
