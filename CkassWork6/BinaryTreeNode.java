public class BinaryTreeNode {
    private String data;
    private BinaryTreeNode left, right;

    BinaryTreeNode (String s) {
        data = s;
        left = null;
        right = null;
    }

    public void add (String s, String child) {
        if (child.equals("L")) {
            left = new BinaryTreeNode(s);
        } else if (child.equals("R")) {
            right = new BinaryTreeNode(s);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public BinaryTreeNode getChild (String child) {
        return (child.equals("L")) ? left : right;
    }

    public String getString () {
        return data;
    }

    public void doubleTree () {
        if ( this.hasChild("L")) {
            this.left.doubleTree();
        }
        if( this.hasChild("R")) {
            this.right.doubleTree();
        }
        this.doubleNode();
    }

    public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
        if (n1 == null && n2 == null) {return true;}
        if (n1 != null && n2 != null) {
            return (n1.data == n2.data
            && sameTree(n1.left, n2.left)
            && sameTree(n1.right, n2.right));
        }
        return false;
    }

    private void doubleNode() {
        BinaryTreeNode temp = this.left;
        BinaryTreeNode newNode = new BinaryTreeNode(this.data);
        this.left = newNode;
        newNode.left = temp;
    }
    private boolean hasChild(String child) {
        if (child == "L") {return this.left != null;}
        if (child == "R") {return this.right != null;}
        throw new IllegalArgumentException();
    }

    public static void preorderPrint (BinaryTreeNode n) {
     if (n == null) {return;}
     System.out.println(n.data);
     preorderPrint(n.getChild("L"));
     preorderPrint(n.getChild("R"));
    }

    public static void main(String[] args) {
        BinaryTreeNode trial = new BinaryTreeNode("2");
        trial.add("1", "L");
        trial.add("3","R");
        trial.doubleTree();
        preorderPrint(trial);

        BinaryTreeNode trial2 = new BinaryTreeNode("2");
        trial2.add("1", "L");
        trial2.add("3","R");

        BinaryTreeNode trial3 = new BinaryTreeNode("2");
        trial3.add("1", "L");
        trial3.add("3","R");

        System.out.println(sameTree(trial2,trial3));
        System.out.println(sameTree(trial,trial3));
    }
}
