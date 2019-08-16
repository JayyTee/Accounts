/*
*Main Class
*(Insert instructions)
 */

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        //input variables
        File logs = new File("log.html");
        Scanner scan = new Scanner(System.in);
        String input = null;

        String prompt = "please enter a command(deposit, withdraw, balance, exit): ";
        Banking account1 = new Banking(logs);

        //accepted inputs
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
                        System.out.println("Please enter an amount to withdraw:");
                        input = scan.next();
                        account1.withdraw(input);
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
