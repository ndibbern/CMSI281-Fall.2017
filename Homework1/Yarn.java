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
        return uniqueSize == 0;
    }

    public int getSize () {
        return size;
    }

    public int getUniqueSize () {
        return uniqueSize;
    }

    /**
     * @param toAdd String to be added to the Yarn.
     * @return true if successful insertion, false if Yarn is at capacity (already at 100 unique Strings).
     */

    public boolean insert (String toAdd) {
        if (uniqueSize < MAX_SIZE) {
            if (findIndex(toAdd) == -1) {
                items[uniqueSize] = new Strand(toAdd, 1);
                uniqueSize++;
                size++;
                return true;
            } else {
                items[findIndex(toAdd)].count++;
                size++;
                return true;
            }
        } else {
            return false;
        }
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
        return findIndex(toCheck) != -1;
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
    /*finds the current index of the Entry with text "toFind"
    * returns -1 if not found*/
    private int findIndex(String toFind) {
        for (int i = 0; i < uniqueSize; i++){
            if (items[i].text.equals(toFind)) {
                return i;
            }
        }
        return -1;
    }

}

class Strand {
    String text;
    int count;

    Strand (String s, int c) {
        text = s;
        count = c;
    }
}
