package pl.pgoralewicz.cl.workshop.find_random_number;

import java.util.Random;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = (new Random()).nextInt(100) + 1;

        System.out.println("Zgadnij liczbę z zakresu 1-100");

        int i = 0;

        do{
            if(scanner.hasNextInt()){
                i = scanner.nextInt();
            }else{
                scanner.next();
                System.out.println("To nie jest liczba");
                continue;
            }
            if(i < r){
                System.out.println("Za mało!");
            }else if(i > r){
                System.out.println("Za dużo!");
            }
        }while(i != r);
        System.out.println("Zgadłeś!");
    }
}
