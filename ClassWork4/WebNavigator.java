import java.util.Stack;
import java.util.Scanner;

public class WebNavigator {

    // Fields
    private String current;
    private Stack<String> history;
    private Stack<String> forward;

    // Constructor
    WebNavigator () {
        this.forward = new Stack<String>();
        this.history = new Stack<String>();
        this.current = null;
    }

    public boolean getNextUserCommand (Scanner input) {
        String command = input.nextLine();
        String[] parsedCommand = command.split(" ");

        // Switch on the command (issued first in input line)
        switch(parsedCommand[0]) {
        case "exit":
            System.out.println("Goodbye!");
            return false;
        case "visit":
            visit(parsedCommand[1]);
            break;
        case "back":
            back();
            break;
        case "forward":
            forw();
            break;
        default:
            System.out.println("[X] Invalid command, try again");
        }

        System.out.println("Currently Visiting: " + current);

        return true;
    }

    /*
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     */
    public void visit (String site) {
        history.push(site);
        current = site;
        while (!forward.empty()) {
            forward.pop();
        }
    }

    /*
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back () {
        if (!history.empty()) {
            forward.push(history.pop());
            if (!history.empty()) {
                current = history.peek();
            }
        }

    }

    public void forw () {
        if (!forward.empty()) {
            history.push(forward.pop());
            if (!forward.empty()) {
                current = forward.peek();
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WebNavigator navi = new WebNavigator();

        System.out.println("Welcome to ForneyFox, enter a command from your ForneyFox user manual!");
        while (navi.getNextUserCommand(input)) {}
        //Took this line out because it prints Goodbye two times.
        //System.out.println("Goodbye!");
    }

}
