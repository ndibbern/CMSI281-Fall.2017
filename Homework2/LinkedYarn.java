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
        Node current = other.head;
        for (int i = 0; i < other.uniqueSize; i++) {
            temporary.insertNode(current.text, current.count);
            current = current.next;
        }
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

    public int count (String toCount) {
        if (!this.contains(toCount)) {return 0;}
        return find(toCount).count;
    }

    public boolean contains (String toCheck) {
        if (this.isEmpty()) { return false; }
        Iterator iterator = getIterator();
        while (!iterator.getString().equals(toCheck) && iterator.hasNext()) {
            iterator.next();
        }
        return iterator.getString().equals(toCheck);
    }

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

    public LinkedYarn.Iterator getIterator () {
        return new Iterator(this);
    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = new LinkedYarn(y1);
        Node current = y2.head;
        for (int i = 0; i < y2.uniqueSize; i++) {
            result.insertOccurrences(current.text, current.count);
            current = current.next;
        }
        return result;
    }

    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = new LinkedYarn(y1);
        Node current = y2.head;
        for (int i = 0; i < y2.uniqueSize; i++) {
            result.removeOccurrences(current.text, current.count);
            current = current.next;
        }
        return result;
    }

    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
        return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------

    private Node find(String word) {
        if (!this.contains(word)) {return null;}
        Iterator iterator = this.getIterator();
        while (!iterator.getString().equals(word) && iterator.hasNext()) {
            iterator.next();
        }
        return iterator.getString().equals(word) ? iterator.current : null;
    }

    private void insertOccurrences (String text, int countNumber) {
        for (int i = 0; i < countNumber; i++) {
            this.insert(text);
        }
    }

    private void removeOccurrences (String text, int countNumber) {
        for (int i = 0; i < countNumber; i++) {
            this.remove(text);
        }
    }

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
