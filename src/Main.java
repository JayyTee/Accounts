/*
*Main Class
*(Insert instructions)
 */

import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException{
        File logs = new File("log.html");
        String input = null;
        Scanner scan = new Scanner(System.in);
        String prompt = "please enter a command(deposit, withdraw, balance, exit)";
        Banking account1 = new Banking(logs);


        while(input != "exit") {
            System.out.println(prompt);
            input = scan.next().toLowerCase();

            switch (input) {
                case "deposit":
                    System.out.println("depositMethod");
                    input = null;

                    break;

                case "withdraw":
                    System.out.println("withdrawMethod");
                    input = null;
                    break;

                case "balance":
                    System.out.println("your balance is $" + account1.getbalance());
                    input = null;
                    break;
                case "exit":
                    return;
            }
        }
    }
}
