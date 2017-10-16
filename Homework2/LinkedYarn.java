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
        this.head = null;
        this.size = 0;
        this.uniqueSize = 0;
        this.modCount = 0;
    }

    LinkedYarn (LinkedYarn other) {
        LinkedYarn temporary = new LinkedYarn();
        Iterator iterator = other.getIterator();
        while (iterator.hasNext()) {
            temporary.insert(iterator.getString());
            iterator.next();
        }

        temporary.insert(other.head.text);
        this.head = temporary.head;
        this.size  = temporary.getSize();
        this.uniqueSize = temporary.getUniqueSize();
        this.modCount = temporary.modCount;
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

    public int remove (String toRemove) {
        Node nodeToRemoveFrom = find(toRemove);
        if ( nodeToRemoveFrom == null) { return 0;}
        if (nodeToRemoveFrom.count == 1) {
            removeAll(toRemove);
        } else {
            size -= 1;
            nodeToRemoveFrom.count--;
            return nodeToRemoveFrom.count;
            }
        modCount++;
        return 0;
    }

    public void removeAll (String toNuke) {
        Node nodeToNuke = find(toNuke);
        size -= nodeToNuke.count;
        if (nodeToNuke == null) {return;}
        if (nodeToNuke == head) { head = nodeToNuke.next; }
        if (nodeToNuke.prev != null) {
            nodeToNuke.prev.next = nodeToNuke.next;
        }
        uniqueSize--;
        modCount++;
    }

    public int count (String toCount) {
        throw new UnsupportedOperationException();
    }

    public boolean contains (String toCheck) {

        if ( this.isEmpty() ) { return false; }
        Iterator iterator = getIterator();
        while( !iterator.getString().equals(toCheck) && iterator.hasNext() ) {
            iterator.next();
        }
        return iterator.getString().equals(toCheck);
    }

    public String getMostCommon () {
        throw new UnsupportedOperationException();
    }

    public void swap (LinkedYarn other) {
        throw new UnsupportedOperationException();
    }

    public LinkedYarn.Iterator getIterator () {
        return new Iterator(this);
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

    private Node find(String word) {
        //Returns the Node that contains that text
        //Or null if it could not find it
        if (!this.contains(word)) { return null; }

        Iterator iterator = this.getIterator();
        while (!iterator.getString().equals(word) && iterator.hasNext()) {
            iterator.next();
        }
        return iterator.getString().equals(word) ? iterator.current : null;
    }

    @Override
    public String toString(){
        if (this.isEmpty()) {
            return "{ }";
        } else {
            Iterator iterator = this.getIterator();
            String toPrint = "{ ";
            toPrint += iterator.getString();
            while (iterator.hasNext()) {
                iterator.next();
                toPrint += ", ";
                toPrint += iterator.getString();
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
            return current == head;
        }

        public boolean isValid () {
            return itModCount == owner.modCount;
        }

        public String getString () {
            return this.isValid() && !owner.isEmpty() ? current.text : null;
        }

        public void next () {
            if (!this.hasNext()) {return;}
            current = current.next;
        }

        public void prev () {
            if (!this.hasPrev()) {return;}
            current = current.prev;
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
