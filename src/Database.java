import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author Naveen Gupta (naveengupta)
 * 
 * @version 2021-09-26
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    /**
     * The constructor for this class initializes a SkipList object
     * with String and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle
     * has valid coordinates and dimensions, that is that
     * the coordinates are non-negative and that the
     * rectangle object has some area (not 0, 0, 0, 0).
     * This insert will insert the KVPair specified into
     * the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {

        String rectangleName = pair.getKey();

        // Parsing rectangle x, y coordinates and width, height information
        int xCoordinate = pair.getValue().x;
        int yCoordinate = pair.getValue().y;
        int width = pair.getValue().width;
        int height = pair.getValue().height;

        String rectDetail = String.format("(%1$s, %2$s, %3$s, %4$s, %5$s)",
            rectangleName, xCoordinate, yCoordinate, width, height);

        if (!(checkRectangleValidity(xCoordinate, yCoordinate, width,
            height))) {

            System.out.println("Rectangle rejected: " + rectDetail);
        }
        else {

            list.insert(pair);
            System.out.println("Rectangle inserted: " + rectDetail);
        }
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {

        KVPair<String, Rectangle> rectangleResult = list.remove(name);

        if (rectangleResult != null) {

            String rectangleInfo = String.format(
                ("%1$s, %2$s, %3$s, %4$s, %5$s"), rectangleResult.getKey(),
                rectangleResult.getValue().x, rectangleResult.getValue().y,
                rectangleResult.getValue().width, rectangleResult
                    .getValue().height);

            System.out.println("Rectangle removed: " + String.format("(%s)",
                rectangleInfo));

        }
        else {

            System.out.println("Rectangle not removed: " + String.format("(%s)",
                name));
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available.
     * If not an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {

        if (!(checkRectangleValidity(x, y, w, h))) {

            System.out.println("Rectangle rejected: " + String.format(
                ("(%1$s, %2$s, %3$s, %4$s)"), x, y, w, h));

        }
        else {
            Rectangle rect = new Rectangle(x, y, w, h);

            KVPair<String, Rectangle> removedRect = list.removeByValue(rect);

            if (removedRect != null) {

                String rectFormat = String.format(
                    ("%1$s, %2$s, %3$s, %4$s, %5$s"), removedRect.getKey(),
                    removedRect.getValue().x, removedRect.getValue().y,
                    removedRect.getValue().width, removedRect
                        .getValue().height);

                System.out.println("Rectangle removed: " + String.format("(%s)",
                    rectFormat));

            }
            else {

                System.out.println("Rectangle not removed: " + String.format(
                    "(%s)", String.format(("%1$s, %2$s, %3$s, %4$s"), rect.x,
                        rect.y, rect.width, rect.height)));
            }
        }
    }


    /**
     * Check a rectangle with the specified coordinates
     * if it is a good rectangle
     * based on defined conditions
     * 
     * @param xCoordinate
     *            x-coordinate of the rectangle to be checked
     * @param yCoordinate
     *            y-coordinate of the rectangle to be checked
     * @param width
     *            width of the rectangle to be checked
     * @param height
     *            height of the rectangle to be checked
     * 
     * @return a boolean data type if the rectangle is valid
     * 
     */
    public boolean checkRectangleValidity(
        int xCoordinate,
        int yCoordinate,
        int width,
        int height) {

        boolean checkRectangle = true;

        if (xCoordinate < 0 || yCoordinate < 0 || width <= 0 || height <= 0
            || (xCoordinate + width) > 1024 || (yCoordinate + height) > 1024) {

            checkRectangle = false;

            return checkRectangle;
        }

        return checkRectangle;
    }


    /**
     * Displays all the rectangles inside the
     * specified region. The rectangle must
     * have some area inside the area
     * that is created by the region, meaning,
     * Rectangles that only touch a side
     * or corner of the region specified will not
     * be said to be in the region.
     * You will need a SkipList Iterator for this
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {

        Iterator<KVPair<String, Rectangle>> listIterator = list.iterator();

        if (w <= 0 || h <= 0) {

            System.out.println("Rectangle rejected: " + String.format(
                "(%1$s, %2$s, %3$s, %4$s)", x, y, w, h));

        }
        else {

            System.out.println("Rectangles intersecting region " + String
                .format("(%1$s, %2$s, %3$s, %4$s): ", x, y, w, h));

            while (listIterator.hasNext()) {

                KVPair<String, Rectangle> rectangle = listIterator.next();

                int xmax = Math.max(x, rectangle.getValue().x);
                int ymax = Math.max(y, rectangle.getValue().y);

                int xmin = Math.min(x + w, rectangle.getValue().x + rectangle
                    .getValue().width);

                int ymin = Math.min(y + h, rectangle.getValue().y + rectangle
                    .getValue().height);

                if (!(xmax >= xmin || ymax >= ymin)) {

                    System.out.println(String.format(
                        "(%1$s, %2$s, %3$s, %4$s, %5$s)", rectangle.getKey(),
                        rectangle.getValue().x, rectangle.getValue().y,
                        rectangle.getValue().width, rectangle
                            .getValue().height));

                }

            }

        }

    }


    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList
     * Iterators for this
     */
    public void intersections() {

        System.out.println("Intersections pairs: ");

        Iterator<KVPair<String, Rectangle>> listIterator1 = list.iterator();

        while (listIterator1.hasNext()) {
            KVPair<String, Rectangle> firstRectangle = listIterator1.next();
            Iterator<KVPair<String, Rectangle>> listIterator2 = list.iterator();

            while (listIterator2.hasNext()) {

                KVPair<String, Rectangle> secondRectangle = listIterator2
                    .next();

                if (!(firstRectangle.equals(secondRectangle))) {

                    int xmax = Math.max(firstRectangle.getValue().x,
                        secondRectangle.getValue().x);
                    int ymax = Math.max(firstRectangle.getValue().y,
                        secondRectangle.getValue().y);

                    int xmin = Math.min((firstRectangle.getValue().x
                        + firstRectangle.getValue().width), (secondRectangle
                            .getValue().x + secondRectangle.getValue().width));

                    int ymin = Math.min((firstRectangle.getValue().y
                        + firstRectangle.getValue().height), (secondRectangle
                            .getValue().y + secondRectangle.getValue().height));

                    if (xmax >= xmin || ymax >= ymin) {

                        continue;

                    }
                    else {

                        String firstRectangleFormat = String.format(
                            ("%1$s, %2$s, %3$s, %4$s, %5$s"), firstRectangle
                                .getKey(), firstRectangle.getValue().x,
                            firstRectangle.getValue().y, firstRectangle
                                .getValue().width, firstRectangle
                                    .getValue().height);

                        String secondRectangleFormat = String.format(
                            ("%1$s, %2$s, %3$s, %4$s, %5$s"), secondRectangle
                                .getKey(), secondRectangle.getValue().x,
                            secondRectangle.getValue().y, secondRectangle
                                .getValue().width, secondRectangle
                                    .getValue().height);

                        System.out.println(String.format("(%s)",
                            firstRectangleFormat + " | "
                                + secondRectangleFormat));

                    }

                }
            }

        }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This
     * method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {

        ArrayList<KVPair<String, Rectangle>> rectangleResult = list.search(
            name);

        if (rectangleResult != null) {

            System.out.println("Rectangle found:");

            for (KVPair<String, Rectangle> rectangleFound : rectangleResult) {

                String rectangleInfo = String.format(
                    ("%1$s, %2$s, %3$s, %4$s, %5$s"), rectangleFound.getKey(),
                    rectangleFound.getValue().x, rectangleFound.getValue().y,
                    rectangleFound.getValue().width, rectangleFound
                        .getValue().height);

                System.out.println(String.format("(%s)", rectangleInfo));
            }

        }
        else {

            System.out.println("Rectangle not found: " + name);
        }
    }


    /**
     * Prints out a dump of the SkipList
     * which includes information about the size
     * of the SkipList and shows all of the
     * contents of the SkipList. This will all
     * be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
