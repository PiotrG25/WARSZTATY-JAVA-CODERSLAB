package pl.pgoralewicz.cl.workshop.Lotto;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        int[] nums = new int [6];

        //wpisywanie liczb uzytkownika
        System.out.println("Wpisz 6 liczb całowitych z zakresu 1-49");
        for(int i = 0; i < nums.length; i++){
            nums[i] = scanInt();
        }
        Arrays.sort(nums);

        //Sprawdzenie czy liczby się nie powtażają
        for(int i = 1; i < nums.length; i++){
            while(nums[i - 1] == nums[i]){
                System.out.println("Liczba " + nums[i] +
                        " powtarza się, podaj inną liczbę!");
                nums[i] = scanInt();
                Arrays.sort(nums);
                i = 1;
            }
        }

        //Wypisanie liczb uzytkownika na ekran
        System.out.println("Twoje liczby: " + Arrays.toString(nums));


        int[] lnums = new int [6];

        //pierwsze losowanie
        for(int i = 0; i < lnums.length; i++){
            lnums[i] = (int)(Math.random() * 49) + 1;
        }
        Arrays.sort(lnums);

        //walidacja losowania
        for(int i = 1; i < lnums.length; i++){
            while(lnums[i - 1] == lnums[i]){
                lnums[i] = (int)(Math.random() * 49) + 1;
                Arrays.sort(lnums);
            }
        }

        //Wypisywanie liczb wylosowanych na ekran
        System.out.println("Wylosowane liczby: " + Arrays.toString(lnums));

        //Zliczanie odgadnietych liczb
        int ile = 0;
        for(int i : nums){
            for(int j : lnums){
                if(i == j){
                    ile++;
                    break;
                }
            }
        }

        System.out.println("Zgadłeś " + ile + " liczb!");
        if(ile < 3){
            System.out.println("Trafiłeś mniej niż 3 liczby, brak nagrody");
        }

    }

    static Scanner scan = new Scanner(System.in);

    static int scanInt(){
        //walidated int input
        int n;
        while(true){
            if(scan.hasNextInt()){
                n = scan.nextInt();
                while(n > 49 || n < 1){
                    System.out.println("Liczba musi byc z zakresu 1 49");
                    n = scanInt();
                }
                break;
            }else{
                scan.next();
                System.out.println("Podaj liczbę");
            }
        }
        return n;
    }
}
