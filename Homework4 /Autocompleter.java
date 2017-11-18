import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    private ArrayList<String> terms;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public boolean isEmpty () {
        return root == null;
    }

    public void addTerm (String toAdd) {
        addTerm(normalizeTerm(toAdd), root, 0);
    }

    public boolean hasTerm (String query) {
        throw new UnsupportedOperationException();
    }

    public String getSuggestedTerm (String query) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<String> getSortedTerms () {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }

    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }

    // [!] Add your own helper methods here!

    private static boolean indexIsValid (int index, String word) {
        return  index < word.length() - 1;
    }

    private TTNode addTerm (String toAdd, TTNode node, int index) {
        char[] lettersInWord = toAdd.toCharArray();

        //Base case
        if (node == null) {node = new TTNode(lettersInWord[index], false);}

        //Recursion
        int position = compareChars(lettersInWord[index], node.letter);
        if (position < 0) {
            node.left = addTerm(toAdd, node.left, index);
        } else if (position > 0) {
            node.right = addTerm(toAdd, node.right, index);
        } else {
            if (indexIsValid(index, toAdd)) {
                node.mid = addTerm(toAdd, node.mid, index + 1);
            } else {
                node.wordEnd = true;
            }
        }
        return node;
    }


    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------

    /*
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {

        boolean wordEnd;
        char letter;
        TTNode left, mid, right;

        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }

    }

}
