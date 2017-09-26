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

        this.items = new Strand[MAX_SIZE];
        for (int i = 0; i < other.getUniqueSize(); i++) {
            this.items[i] = other.items[i];
        }
        this.size = other.getSize();
        this.uniqueSize = other.getUniqueSize();

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
        int idx = findIndex(toRemove);
        if (idx != -1) {
            if(items[idx].count == 1) {
                removeAll(toRemove);
            } else {
                items[idx].count--;
                size -= 1;
                return items[idx].count;
            }
        }
        return 0;
    }


    public void removeAll (String toNuke) {
        int idx = findIndex(toNuke);
        if (idx == -1) { return; }

        int toNukeCount = items[idx].count;
        swapStrand(idx, uniqueSize);
        items[uniqueSize] = null;
        uniqueSize--;
        size -= toNukeCount;
        return;
    }

    public int count (String toCount) {

        int idx = findIndex(toCount);
        if (idx == -1) { return 0; }

        return items[findIndex(toCount)].count;
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

        Yarn tempYarn = new Yarn(other);

        other.items = this.items;
        other.size = this.size;
        other.uniqueSize = uniqueSize;

        this.size = tempYarn.getSize();
        this.uniqueSize = tempYarn.getUniqueSize();
        this.items = tempYarn.items;
        return;
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

    private void swapStrand (int idx1, int idx2) {
        if (idx1 > uniqueSize || idx1 < 0 || idx2 > uniqueSize || idx2 < 0 ) {
            return;
        }
        Strand save = this.items[idx1];
        this.items[idx1] = this.items[idx2];
        this.items[idx2] = save;
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
