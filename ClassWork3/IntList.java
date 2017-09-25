public class IntList {

    // Fields
    private int[] items;
    private int   size;
    private static final int START_SIZE = 8;

    // Constructor
    IntList () {
        items = new int[START_SIZE];
        size  = 0;
    }

    public int getAt(int index) {
        indexValidityCheck(index);
        return items[index];
    }

    public void append(int toAdd) {
        checkAndGrow();
        items[size] = toAdd;
        size++;
    }

    public void prepend (int toAdd) {
        insertAt(toAdd, 0);
    }

    public void insertAt(int toAdd, int index) {
        if(index <= size) {
            checkAndGrow();
            shiftRight(index);
            items[index] = toAdd;
            size ++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void removeAt(int index) {
        shiftLeft(index);
        size--;
    }

    public int getSize() {
        return size;
    }

    private void indexValidityCheck (int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
    }
}

    /*
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }

        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];

        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        // Step 3: update IntList reference
        items = newItems;
    }

    /*
     * Shifts all elements to the right of the given
     * index one left
     */
    private void shiftLeft (int index) {
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }

    /*
     * Shifts all elements to the left of the given
     * index one right
     */
    private void shiftRight (int index) {
        for (int i = size; i > index; i--) {
            items[i] = items[i-1];
        }
    }

    @Override
    public String toString(){
        String toOutput = "{ ";
        for(int i = 0; i < size; i ++){
            toOutput += items[i] + " ";
        }
        toOutput += "}";
        return toOutput;
    }
}
