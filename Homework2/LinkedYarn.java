import java.util.NoSuchElementException;

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
        head = null;
        size = 0;
        uniqueSize = 0;
        modCount = 0;
    }

    LinkedYarn (LinkedYarn other) {
        // TODO
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return size == 0;
    }

    public int getSize () {
        return size;
    }

    public int getUniqueSize () {
        return uniqueSize;
    }

    public void insert (String toAdd) {
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

    public String getMostCommon () {
        throw new UnsupportedOperationException();
    }

    public void swap (LinkedYarn other) {
        throw new UnsupportedOperationException();
    }

    public LinkedYarn.Iterator getIterator () {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        throw new UnsupportedOperationException();
    }

    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        throw new UnsupportedOperationException();
    }

    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------

    // You should add some methods here!


    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------

    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;
        private int index;

        Iterator (LinkedYarn y) {
            owner = y;
            current = y.head;
            itModCount = y.modCount;
            index = 1;
        }

        public boolean hasNext () {
            return current != null && current.next != null;
        }

        public boolean hasPrev () {
            return current.prev != null;
        }

        public boolean isValid () {
            return itModCount == owner.modCount;
        }

        public String getString () {
            throw new UnsupportedOperationException();
        }

        public void next () {
            current = current.next;
        }

        public void prev () {
            throw new UnsupportedOperationException();
        }

        public void replaceAll (String toReplaceWith) {
            throw new UnsupportedOperationException();
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
