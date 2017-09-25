import javax.naming.OperationNotSupportedException;

/**
 *  A Yarn is an unordered collection of Strings in which duplicates are allowed.
 *  A Yarn maps Strings to the number of occurrences of each String in the Yarn.
 */

public class Yarn implements YarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Strand[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;


    // -----------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------
    Yarn () {
        this.items = new Strand[MAX_SIZE];
        this.size = 0;
        this.uniqueSize = 0;
    }

    Yarn (Yarn other) {

    }

    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        throw new UnsupportedOperationException();
    }

    public int getSize () {
        throw new UnsupportedOperationException();
    }

    public int getUniqueSize () {
        throw new UnsupportedOperationException();
    }

    public boolean insert (String toAdd) {
        throw new UnsupportedOperationException();
    }

    public int remove (String toRemove) {
        throw new UnsupportedOperationException();
    }

    public void removeAll (String toNuke) {
        throw new UnsupportedOperationException();
    }

    public int count (String toCount) {
        throw new UnsupportedOperationException();
    }

    public boolean contains (String toCheck) {
        throw new UnsupportedOperationException();
    }

    public String getNth (int n) {
        throw new UnsupportedOperationException();
    }

    public String getMostCommon () {
        throw new UnsupportedOperationException();
    }

    public void swap (Yarn other) {
        throw new UnsupportedOperationException();
    }

    public String toString () {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static Yarn knit (Yarn y1, Yarn y2) {
        throw new UnsupportedOperationException();
    }

    public static Yarn tear (Yarn y1, Yarn y2) {
        throw new UnsupportedOperationException();
    }

    public static boolean sameYarn (Yarn y1, Yarn y2) {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    // Add your own here!

}

class Strand {
    String text;
    int count;

    Strand (String s, int c) {
        text = s;
        count = c;
    }
}
