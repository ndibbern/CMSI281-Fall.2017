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
    /**
     * @return true if Yarn is empty
     */

    public boolean isEmpty () {
        return uniqueSize == 0;
    }

    /**
     * @return size
     */

    public int getSize () {
        return size;
    }

    /**
     * @return uniqueSize
     */

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

    /**
     * @param toRemove String to be removed from the Yarn (only one occurrance)
     * @return the number of occurrences remaining after removal, (0 if toRemove does not exist in Yarn)
     */

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

    /**
     * @param toNuke String to be removed from the Yarn (all occurrances)
     */

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

    /**
     * @param toCount String to which the number of occurrances we want to know
     * @return the number of occurrences of toCount in Yarn
     */

    public int count (String toCount) {

        int idx = findIndex(toCount);
        if (idx == -1) { return 0; }

        return items[findIndex(toCount)].count;
    }

    /**
     * @param toCheck String to which we want to check its occurrance in the Yarn
     * @return true if the String toCheck appears at least once inside of the Yarn.
     */

    public boolean contains (String toCheck) {
        return findIndex(toCheck) != -1;
    }

    //TODO
    public String getNth (int n) {
        int sum = 0;
        int result = 0;

        while (sum < n ) {
            for (int i = 0; i < uniqueSize; i++) {
                sum += items[i].count;
                result = i;
            }
        }
        return items[result].text;
    }

    /**
     * @param toCheck String to which we want to check its occurrance in the Yarn
     * @return the String that occurs most frequently in the Yarn (if it is a tie
     * return *either* of the most frequent. If the Yarn is empty return null.
     */

    public String getMostCommon () {
        if (isEmpty()) {
            return "null";
        }
        int mostCommonIdx = 0;
        int valueMostCommonCount = items[0].count;

        for (int i = 1; i < uniqueSize; i++) {
            if (items[i].count > valueMostCommonCount ) {
                mostCommonIdx = i;
                valueMostCommonCount = items[i].count;
            }
        }
        return items[mostCommonIdx].text;
    }

    /**
     * @param other Yarn to swap
    */

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


    @Override
    public String toString () {
        String toPrint = "{ ";
        for (int i = 0; i < uniqueSize ; i ++) {
            toPrint += "\"";
            toPrint += items[i].text;
            toPrint += "\": ";
            toPrint += items[i].count;
            if (i != uniqueSize - 1) {
                toPrint += ", ";
            }
        }
        return toPrint + " }";
    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static Yarn knit (Yarn y1, Yarn y2) {
        Yarn sum = new Yarn(y1);
        for( int i = 0; i < y2.getSize(); i ++){
            sum.insert(y2.getNth(i));
        }
        return sum;
    }

    public static Yarn tear (Yarn y1, Yarn y2) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param y1,y2 two Yarns
     * @return true if y1 and y2 contain the exact same unique Strings and String occurrences
     */

    public static boolean sameYarn (Yarn y1, Yarn y2) {

        if (y1.getUniqueSize() != y2.getUniqueSize()) { return false; }
        if (y1.getSize() != y2.getSize()) { return false; }

        for (int i = 0; i < y1.getUniqueSize(); i++) {
            if (y2.count(y1.items[i].text) != y1.items[i].count ) {return false;}
        }
        return true;
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
