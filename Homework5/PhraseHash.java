package sentinal;

import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private final static int BUCKET_COUNT = 1000;
    private int size, longest;
    private LinkedList<String>[] buckets;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    @SuppressWarnings("unchecked") // Don't worry about this >_>
    PhraseHash () {
        this.size = 0;
        this.longest = 0;
        this.buckets = new LinkedList[BUCKET_COUNT];
    }


    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------

    public int size () {
        return this.size;
    }

    public boolean isEmpty () {
        return this.size ==0;
    }

    public void put (String s) {
        throw new UnsupportedOperationException();
    }

    public String get (String s) {
        throw new UnsupportedOperationException();
    }

    public int longestLength () {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private int hash (String s) {
        throw new UnsupportedOperationException();
    }

}
