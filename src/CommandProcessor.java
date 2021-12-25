import java.awt.Rectangle;

// import java.util.Arrays;
// import java.util.Scanner;
/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author Naveen Gupta (naveengupta)
 * 
 * @version 2021-09-26
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the
     * file line is not one of these, an appropriate message will be
     * written in the console. This processor method is called for each
     * line in the file. Note that the methods called will themselves
     * write to the console, this method does not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    @SuppressWarnings("unchecked")
    public void processor(String line) {

        String[] inputLine = line.split("\\s+");

        if (inputLine[0].equals("insert")) {

            // Reading x and y coordinate of the input rectangle
            int xCoordinate = Integer.parseInt(inputLine[2]);
            int yCoordinate = Integer.parseInt(inputLine[3]);

            // Reading width and height coordinate of the input rectangle
            int width = Integer.parseInt(inputLine[4]);
            int height = Integer.parseInt(inputLine[5]);

            Rectangle rectangle = new Rectangle(xCoordinate, yCoordinate, width,
                height);

            KVPair<String, Rectangle> rectangleKVPair =
                new KVPair<String, Rectangle>(inputLine[1], rectangle);

            data.insert(rectangleKVPair);

        }
        else if (inputLine[0].equals("search")) {
            data.search(inputLine[1]);
        }
        else if (inputLine[0].equals("dump")) {
            data.dump();
        }
        else if (inputLine[0].equals("remove")) {

            if (inputLine.length == 5) {

                // Reading x and y coordinate of the input rectangle
                int xCoordinate = Integer.parseInt(inputLine[1]);
                int yCoordinate = Integer.parseInt(inputLine[2]);

                // Reading width and height coordinate of the input rectangle
                int width = Integer.parseInt(inputLine[3]);
                int height = Integer.parseInt(inputLine[4]);

                data.remove(xCoordinate, yCoordinate, width, height);

            }
            else {

                data.remove(inputLine[1]);
            }
        }
        else if (inputLine[0].equals("regionsearch")) {

            // Reading x and y coordinate of the input rectangle
            int xCoordinate = Integer.parseInt(inputLine[1]);
            int yCoordinate = Integer.parseInt(inputLine[2]);

            // Reading width and height coordinate of the input rectangle
            int width = Integer.parseInt(inputLine[3]);
            int height = Integer.parseInt(inputLine[4]);

            data.regionsearch(xCoordinate, yCoordinate, width, height);
        }
        else if (inputLine[0].equals("intersections")) {

            data.intersections();
        }
    }
}
