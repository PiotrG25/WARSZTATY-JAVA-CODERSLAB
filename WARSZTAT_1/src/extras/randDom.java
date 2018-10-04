package pl.pgoralewicz.cl.workshop.extras;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class randDom {
    public static void main(String[] args) {

//        Otrzymalismy w ten sposob obiekt polosowanych wartosci calkowitych z podanego zakresu
//        mozemy z niego brac tyle elementow ile nam potrzeba(nie meczac sie z tabelami i nieskonczonym losowaniem
        StringTokenizer st1 = superRandom(1,100);
        while(st1.hasMoreTokens()){
            System.out.println(st1.nextToken());
        }
    }
    public static StringTokenizer superRandom(int min, int max){
//        min = minimal value returned, max = maximal value returned

//        dlugosc prawie tabeli, obejmujacej min i max
        int leng = max - min + 1;
//        deklaracja listy
        List arrList = new ArrayList();

        for(int i = 0; i < leng; i++){
//            dodawanie kolejnych elementow do listy
            arrList.add(Integer.toString(min + i));
        }

//        przemieszanie kolekcji
        Collections.shuffle(arrList);

//        zapis kolekcji do stringa
        String str2 = "";
        for(Object s : arrList){
            str2 += s + " ";
        }

//        podzial kolekcji na tokeny
        StringTokenizer st = new StringTokenizer(str2);

        return st;
    }
}
