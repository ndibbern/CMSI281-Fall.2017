package sandbox;

public class LinkedProcess {
    public Node head;

    LinkedProcess () {
        head = null;
    }

    public void prepend (int d) {
        Node currentHead = head;
        head = new Node(d);
        head.next = currentHead;
    }

    public boolean hasCycle () {

        Node current = head;
        Node currentTimesTwo = head;
        while (current.next != null || currentTimesTwo != null) {
            current = current.next;
            currentTimesTwo = currentTimesTwo.next.next;
            if (current == currentTimesTwo) {
                return true;
            }
        }
        return false;
    }

    public static void main (String[] args) {
        LinkedProcess linky = new LinkedProcess();
        for (int i = 0; i < 3; i++) {
            linky.prepend(i); // add a few Nodes...
        }
        System.out.println(linky.hasCycle()); // false

        linky.head.next.next = linky.head;
        System.out.println(linky.hasCycle()); // true

        linky.head.next.next = linky.head.next;
        System.out.println(linky.hasCycle()); // true
    }

    private class Node {
        int data;
        Node next;
        Node (int d) {
            data = d;
            next = null;
        }
    }

}
