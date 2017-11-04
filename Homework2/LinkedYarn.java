import java.util.NoSuchElementException;
/**
 *  A Yarn is an unordered collection of Strings in which duplicates are allowed.
 *  A Yarn maps Strings to the number of occurrences of each String in the Yarn.
 */

public class LinkedYarn implements LinkedYarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size, uniqueSize, modCount;


    // -----------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------
    LinkedYarn () {
        this.head = null;
        this.size = 0;
        this.uniqueSize = 0;
        this.modCount = 0;
    }

    LinkedYarn (LinkedYarn other) {
        this.head = null;
        this.size = 0;
        this.uniqueSize = 0;
        this.modCount = 0;
        Node current = other.head;
        for (int i = 0; i < other.uniqueSize; i++) {
            this.insertNode(current.text, current.count);
            current = current.next;
        }
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    /**
     * @return true if Yarn is empty
     */
    public boolean isEmpty () {
        return size == 0;
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
     * @param toAdd String to be added to the LinkedYarn.
     */
    public void insert (String toAdd) {
        if (this.contains(toAdd)) {
            find(toAdd).count ++;
            size ++;
        } else {
            Node toInsert = new Node(toAdd, 1);
            toInsert.next = head;
            toInsert.prev = null;
            if (head != null) { head.prev = toInsert; }
            this.head = toInsert;
            size ++;
            uniqueSize ++;
        }
        modCount ++;
    }

    /**
     * @param toRemove String to be removed from the LinkedYarn (only one occurrance)
     * @return the number of occurrences remaining after removal
     */
    public int remove (String toRemove) {
        Node nodeToRemoveFrom = find(toRemove);
        if ( nodeToRemoveFrom == null) {return 0;}
        if (nodeToRemoveFrom.count == 1) {
            removeAll(toRemove);
        } else {
            size --;
            nodeToRemoveFrom.count --;
            return nodeToRemoveFrom.count;
            }
        modCount ++;
        return 0;
    }

    /**
     * @param toNuke String to be removed from the LinkedYarn (all occurrances)
     */
    public void removeAll (String toNuke) {
        if (!this.contains(toNuke)) {return;}
        Node nodeToNuke = find(toNuke);
        size -= nodeToNuke.count;
        uniqueSize --;
        modCount ++;
        if (nodeToNuke == null) {return;}
        if (nodeToNuke == head) { head = nodeToNuke.next; }
        if (nodeToNuke.prev != null) {
            nodeToNuke.prev.next = nodeToNuke.next;
        }
    }

    /**
     * @param toCount String to which the number of occurrances we want to know
     * @return the number of occurrences of toCount
     */
    public int count (String toCount) {
        if (!this.contains(toCount)) {return 0;}
        return find(toCount).count;
    }

    /**
     * @param toCheck String to which we want to check its occurrance
     * @return true if the String toCheck appears at least once
     */
    public boolean contains (String toCheck) {
        if (this.isEmpty()) { return false; }
        Iterator iterator = getIterator();
        while (!iterator.getString().equals(toCheck) && iterator.hasNext()) {
            iterator.next();
        }
        return iterator.getString().equals(toCheck);
    }

    /**
     * @return the String that occurs most frequently in the LinkedYarn (if it is a tie
     * @return *either* of the most frequent. If the LinkedYarn is empty return null.
     */
    public String getMostCommon () {
        if (size == 0) {return null;}
        Node mostCommon = head;
        Iterator iterator = getIterator();
        while (iterator.hasNext()) {
            iterator.next();
            mostCommon = iterator.current.count > mostCommon.count ? iterator.current : mostCommon;
        }
        return mostCommon.text;
    }

    /**
     * @param other LinkedYarn to swap
    */
    public void swap (LinkedYarn other) {
        Node tempHead = head;
        int tempSize = size,
            tempUniqueSize = uniqueSize,
            tempModCount = modCount;

        head = other.head;
        size = other.size;
        uniqueSize = other.uniqueSize;
        modCount = other.modCount;

        other.head = tempHead;
        other.size = tempSize;
        other.uniqueSize = tempUniqueSize;
        other.modCount = tempModCount;
    }

    /**
     * @return iterator for the LinkedYarn
    */
    public LinkedYarn.Iterator getIterator () {
        return new Iterator(this);
    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    /**
     * @param y1,y2 LinkedYarns that you want to knit (put together into one Linkedyarn)
     * @return knitted LinkedYarn (y1 together with y2)
     */
    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = new LinkedYarn(y1);
        Node current = y2.head;
        for (int i = 0; i < y2.uniqueSize; i++) {
            result.insertOccurrences(current.text, current.count);
            current = current.next;
        }
        return result;
    }

    /**
     * @param y1,y2 LinkedYarns that you want to tear (put y1 together with y2 except for elements of y2 that are in y1 alredy)
     * @return teared LinkedYarn
     */
    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = new LinkedYarn(y1);
        Node current = y2.head;
        for (int i = 0; i < y2.uniqueSize; i++) {
            result.removeOccurrences(current.text, current.count);
            current = current.next;
        }
        return result;
    }

    /**
     * @param y1,y2 two LinkedYarns
     * @return true if y1 and y2 contain the exact same unique Strings and String occurrences
     */
    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
        return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    /**
     * @param word to find in the LinkedYarn
     * @return the Node that contains the word and null if LinkedYarn does not contain it
     */
    private Node find(String word) {
        if (!this.contains(word)) {return null;}
        Iterator iterator = this.getIterator();
        while (!iterator.getString().equals(word) && iterator.hasNext()) {
            iterator.next();
        }
        return iterator.getString().equals(word) ? iterator.current : null;
    }

    /**
     * @param text to be inserted in the LinkedYarn and count how many times
     */
    private void insertOccurrences (String text, int countNumber) {
        for (int i = 0; i < countNumber; i++) {
            this.insert(text);
        }
    }

    /**
     * @param text to be removed in the LinkedYarn and count how many times
     */
    private void removeOccurrences (String text, int countNumber) {
        for (int i = 0; i < countNumber; i++) {
            this.remove(text);
        }
    }

    /**
     * @param text,count insert a node with
     */
    private void insertNode (String textToAdd, int countToAdd) {
        Node currentHead = head;
        head = new Node(textToAdd, countToAdd);
        head.next = currentHead;
        size += countToAdd;
        uniqueSize++;
        modCount += countToAdd;
    }

    @Override
    public String toString(){
        if (this.isEmpty()) {
            return "{ }";
        } else {
            Iterator iterator = this.getIterator();
            String toPrint = "{ ";
            toPrint += iterator.getString();
            toPrint += ": ";
            toPrint += this.count(iterator.getString());
            while (iterator.hasNext()) {
                iterator.next();
                toPrint += ", ";
                toPrint += iterator.getString();
                toPrint += ": ";
                toPrint += this.count(iterator.getString());
            }
            toPrint += " }";
            return toPrint;
        }
    }

    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------

    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;
        private int index; // designates the position inside each node (1 being 1st occurrance)

        Iterator (LinkedYarn y) {
            owner = y;
            current = y.head;
            itModCount = y.modCount;
            index = 1;
        }

        public boolean hasNext () {
            if (owner.isEmpty()) {return false;}
            return index < current.count || current.next != null;
        }

        public boolean hasPrev () {
            if (owner.isEmpty()) {return false;}
            return index > 1 || current.prev != null;
        }

        public boolean isValid () {
            return itModCount == owner.modCount;
        }

        public String getString () {
            return this.isValid() && !owner.isEmpty() ? current.text : null;
        }

        public void next () {
            if (!isValid()) {throw new IllegalStateException();}
            if (!hasNext()) {throw new NoSuchElementException();}
                if (index == current.count) {
                    current = current.next;
                    index = 1;
                } else {
                    index++;
                }
        }

        public void prev () {
            if (!isValid()) {throw new IllegalStateException();}
            if (!hasPrev()) {throw new NoSuchElementException();}
            if (index == 1) {
                current = current.prev;
                index = current.count;
            } else {
                index --;
            }
        }

        public void replaceAll (String toReplaceWith) {
            if (isValid()) {
                current.text = toReplaceWith;
                itModCount ++;
                owner.modCount ++;
            } else {
                throw new IllegalStateException();
            }
        }

    }

    class Node {
        Node next, prev;
        String text;
        int count;

        Node (String t, int c) {
            text = t;
            count = c;
        }
    }

}

/**
 * In my opinion programming both the sequential list and the linked list felt
 * really similar. Sequential list was more intuitive since I am more used to
 * working with arrays than I do with references. Furthermore, eventhough we went
 * through the iterator class in class, it was not as intuitive how to do all the
 * methods such that they would work with the LinkedYarn.
 *
 * On the other hand, I feel like some operatations' logic (such as add,
 * remove all, remove) where harder to do on the Yarn class given that we had no
 * prior experience of how a Yarn worked. When I got to implement the LinkedYarn,
 * I already had an intuition of the steps that such methods needed to follow in
 * order to work correctly, the only thing that changed was doing it in a Linked
 * List rather than an array, but the algorithm was the same. Because of this, I
 * believe the level of "difficulty" between both balanced out such that both were
 * at the same level.
 *
 * I wold rather use a linked list implementation on a scenario where I do not
 * know a priori how many items will be on the list (and know that it will be large).
 * An array list would have to extend its size many times if the size is large but
 * for a linked list there is no need. Furthermore I would use linked lists in
 * scenarios where I want to do a stack or queue. In a sequential list this requires
 * constant shifting of the values (either to the right or left). However, on a
 * LinkedList there is no need to do this (can be done easily).
 *
 * On the other hand I would use an array list in a scenario where I would like
 * to have random access in constant time (need an iterator for LL). For example,
 * I had to code a program last semester for CMSI 282 where ecah time I had to access
 * the specific items in a list of items. I used an arraylist for this given that I
 * had to do this so many times and it is easier to do if you have a array (also
 * my list of items was fixed so there was no need for expansion).
 */
