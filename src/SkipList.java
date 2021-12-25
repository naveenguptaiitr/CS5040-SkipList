import java.awt.Rectangle;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Naveen Gupta (naveengupta)
 * 
 * @version 2021-09-26
 * 
 * 
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    private int level;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * 
     * @return ArrayList foundElement contains the KVPairs for the specified key
     */
    public ArrayList<KVPair<K, V>> search(K key) {

        ArrayList<KVPair<K, V>> foundRectangles = new ArrayList<KVPair<K, V>>();

        SkipNode x = head; // Dummy header node

        for (int i = level; i >= 0; i--) {

            // For each level...
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) {

                // go forward
                x = x.forward[i];
            }
        }

        // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists

        if ((x != null) && (x.element().getKey().compareTo(key) == 0)) {

            while ((x.forward[0] != null) && (x.forward[0].element().getKey()
                .compareTo(key) == 0)) {

                foundRectangles.add(x.element());
                x = x.forward[0];

            }
            foundRectangles.add(x.element());
            return foundRectangles;
        }
        else {

            return null; // Its not there
        }

    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by
     * its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {

        int newLevel = randomLevel(); // New node's level

        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }

        // Track end of level
        SkipNode[] update = new SkipList.SkipNode[level + 1];

        SkipNode x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices
     * than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }
        level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the
     * pair was valid and false if not.
     * 
     * @param key
     *            the key to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {

        // Track end of level
        SkipNode[] update = new SkipList.SkipNode[level + 1];

        SkipNode x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }

        x = x.forward[0];
        if ((x != null) && (x.element().getKey().compareTo(key) == 0)) {

            SkipNode[] tempArray = new SkipList.SkipNode[x.forward.length];

            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = x.forward[i];
            }

            for (int i = 0; i < tempArray.length; i++) {

                update[i].forward[i] = tempArray[i];
            }

            size--;
            return x.element();
        }

        return null;

    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {

        SkipNode[] update = new SkipList.SkipNode[level + 1];

        SkipNode x = head; // Start at header node

        for (int i = level; i >= 0; i--) { // Find insert position
            x = head;
            while ((x.forward[i] != null) && (!(x.forward[i].element()
                .getValue().equals(val)))) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }

        x = x.forward[0];

        if ((x != null) && (x.element().getValue().equals(val))) {

            SkipNode[] tempArray = new SkipList.SkipNode[x.forward.length];

            for (int i = 0; i < tempArray.length; i++) {
                tempArray[i] = x.forward[i];
            }

            for (int i = 0; i < tempArray.length; i++) {

                update[i].forward[i] = tempArray[i];
            }

            size--;
            return x.element();
        }

        return null;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {

        System.out.println("SkipList dump:");

        SkipNode current = head; // Start at header node
        while (current != null) {

            if (current.element() != null) {

                KVPair<K, V> rectangleObject = current.element();

                Rectangle rectRecord = (Rectangle)rectangleObject.getValue();

                String rectDetails = String.format(
                    ("(%1$s, %2$s, %3$s, %4$s, %5$s)"), rectangleObject
                        .getKey(), rectRecord.x, rectRecord.y, rectRecord.width,
                    rectRecord.height);

                System.out.println("Node has depth " + current.forward.length
                    + ", Value " + rectDetails);
            }
            else {

                System.out.println("Node has depth " + current.forward.length
                    + ", Value " + String.format("(%s)", current.element()));
            }

            current = current.forward[0];

        }

        System.out.println("SkipList size is: " + size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */

    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from
         * the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {

            return current.forward[0] != null;
        }


        @Override
        public KVPair<K, V> next() {

            current = current.forward[0];

            return current.element();
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
