package brain;

import java.util.Scanner;

public class BRAIN {

    public static void main(String[] args) {
        String ip = "";
        String ans = "s";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a ip");
        ip = in.nextLine();
        Behavior exec = new Behavior(ip);
        /*
        Be_Annoying coisa = new Be_Annoying(ip, 53, 120000);
        Be_Annoying coisa2 = new Be_Annoying(ip, 88, 60000);
        coisa2.start();
        coisa.start();
        try {
            coisa.join();
            coisa2.join();
        } catch (InterruptedException ex) {
            System.err.println("Zombie need BRAIN");
        }
        */
        while (ans.trim().equalsIgnoreCase("n") != true) {
            exec.ping();
            exec.Annoy();
            System.out.println("Again(y/n):");
            ans = in.nextLine();
        }
    }
}
