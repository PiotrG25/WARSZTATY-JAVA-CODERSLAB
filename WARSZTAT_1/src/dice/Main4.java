package pl.pgoralewicz.cl.workshop.dice;

import java.util.Arrays;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        String str;
        Scanner scanner = new Scanner(System.in);
        int i;

        do{
            str = scanner.next();
            i = dice(str);
        }while(i == -1);

        System.out.println(i);

    }
    static int dice(String str){
        int result;

        int x = 0, y = 0, z, zp = 0, zn = 0;
        int next = 0;

        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                if(next == 0){
                    //How many throws
                    x *= 10;
                    x += str.charAt(i) - '0';
                }else if(next == 1){
                    //How many sides on a dice
                    y *= 10;
                    y += str.charAt(i) - '0';
                }else if(next == 2){
                    //extra points
                    zp *= 10;
                    zp += str.charAt(i) - '0';
                }else if(next == 3){
                    //extra negative points
                    zn *= 10;
                    zn -= str.charAt(i) - '0';
                }
            }else if(str.charAt(i) == 'D'){
                next = 1;
                if(x == 0){
                    x = 1;
                }
            }else if(str.charAt(i) == '+') {
                next = 2;
            }else if(str.charAt(i) == '-'){
                next = 3;
            }else{
                System.err.println("Błąd!");
                System.err.println("Wpisz identyfikator kostki ponownie");
                return -1;
            }
        }

        int[] def = {3, 4, 6, 8, 10, 12, 100};

        boolean different = true;
        for(int i : def){
            if(i == y){
                different = false;
                break;
            }
        }
        if(different){
            System.err.println("Błąd!");
            System.err.println("Nie ma kostki o tylu scianach");
            System.err.println("Wpisz identyfikator kostki ponownie");
            System.err.println("Dopuszczlne ilosci scian: " + Arrays.toString(def));
            return -1;
        }

        if(next == 2){
            z = zp;
        }else if(next == 3){
            z = zn;
        }else{
            z = 0;
        }

        result = 0;

        for(int i = 0; i < x; i++){
            result += (int)(Math.random() * y) + 1;
        }

        result += z;

        return result;
    }
}
