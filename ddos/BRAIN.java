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
        Be_Annoying coisa = new Be_Annoying(ip, 100, 10000);
        Be_Annoying coisa2 = new Be_Annoying(ip, 90, 10000);
        coisa2.start();
        coisa.start();
        try {
            coisa.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BRAIN.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        while (ans.trim().equalsIgnoreCase("n") != true) {
            //exec.ping();
            //exec.Annoy();
            System.out.println("Again(y/n):");
            ans = in.nextLine();
        }
    }
}
