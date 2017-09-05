package sandbox;

import java.util.Scanner;

public class PokeInsult {
    public static void main (String[] args) {
        Scanner scanny = new Scanner(System.in);

        System.out.println("Show me what you got:");
        String highestCp = scanny.nextLine();

        if(Integer.parseInt(highestCp) < 1000) {
            System.out.println("Symply pathethic.");
        } else {
            System.out.println("Wow! You're a pokemon master!");
        }
    }
}
