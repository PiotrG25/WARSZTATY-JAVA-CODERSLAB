package pl.coderslab.beans;

import java.util.Arrays;

public class RandomMachine {
    private int length;
    private int[][] tab;//W grze tablica zewnętrzna reprezentuje indeksy przycisków a wewnętrzna losowe, inne indeksy

    public RandomMachine(int length){
        this.length = length;
        setTab();
    }

    public static void main(String[] args) {
        int [][] tab = new RandomMachine(4).getTab();
        for(int [] i : tab){
            System.out.println(Arrays.toString(i));
        }
    }

    public int[][] getTab() {
        return tab;
    }

    private void setTab(){
        this.tab = new int [length][];
        for(int i = 0; i < tab.length; i++){
            tab[i] = new int [(int)(Math.random() * 3) + 1];//{1, 2, 3}
            int [] helpTab = randomIndexes(i);
            for(int j = 0; j < tab[i].length; j++){
                tab[i][j] = helpTab[j];
            }
        }
    }

    private int[] randomIndexes(int otherThanThis){
        int [] tab = new int [length];
        for(int i = 0; i < tab.length; i++){//Wypełniamy tablicę tab indeksami
            tab[i] = i;
        }
        for(int i = 0; i < tab.length; i++){//Usunięcie niepotrzebnego indexu na koniec
            if(tab[i] == otherThanThis){
                int help = tab[i];
                tab[i] = tab[tab.length - 1];
                tab[tab.length - 1] = help;
                break;
            }
        }

        int [] tab2 = new int [length - 1];//Tworzymy nową tablicę i długość pomocniczą
        int length2 = length - 1;

        for(int i = 0; i < tab2.length; i++){
            int randomIndex = (int)(Math.random() * length2);

            tab2[i] = tab[randomIndex];
            tab[randomIndex] = tab[length2 - 1];
            length2--;
        }

        return tab2;
    }
}

