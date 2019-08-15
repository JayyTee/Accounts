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
        String prompt = "please enter a command(deposit, withdraw, balance, exit): ";
        Banking account1 = new Banking(logs);


        while(input != "exit") {
            System.out.println(prompt);
            input = scan.next().toLowerCase();

            switch (input) {
                case "deposit":
                    while(account1.validate(input) == 0){
                        System.out.println("Please enter an amount:");
                        input = scan.next();
                        account1.deposit(input);
                    }

                    input = null;
                    break;

                case "withdraw":
                    while(account1.validate(input) == 0)
                    {
                        System.out.println("Please enter an amount:");
                        input = scan.next();
                        account1.deposit(input);
                    }

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
