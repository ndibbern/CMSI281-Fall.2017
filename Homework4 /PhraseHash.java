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
        return this.size == 0;
    }

    public void put (String s) {
        int index = hash(s);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
            buckets[index].add(s);
        } else if (!buckets[index].contains(s)) {
            buckets[index].add(s);
        } else {
            return;
        }
        this.size ++;

        int stringLength = charLength(s);
        if (stringLength > this.longest) this.longest = stringLength;
    }

    public String get (String s) {
        int index = hash(s);
        if (buckets[index] != null) {
            return buckets[index].contains(s) ? s : null;
        }
        return null;
    }

    public int longestLength () {
        return this.longest;
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private int hash (String s) {
        int hash = 0;
        for (char c : s.toCharArray()){
            hash += (int) c;
        }
        return hash % BUCKET_COUNT;
    }

    private int charLength (String s) {
        int spaces = 0;
        for (char c : s.toCharArray()){
            if (c == ' ') spaces ++;
        }
        return spaces + 1;
    }

}
