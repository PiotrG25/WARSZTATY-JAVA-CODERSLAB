package pl.pgoralewicz.cl.workshop.find_words;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main5 {
    public static void main(String[] args) {
        Connection connect = Jsoup.connect("https://www.onet.pl/");
        try{
            Document document = connect.get();
            Elements links = document.select("span.title");

            String f1Name = "popular_words.txt";
            File f1 = new File(f1Name);
            if(!f1.exists()){
                Files.createFile(Paths.get(f1Name));
            }

            String f2Name = "filtered_popular_words.txt";
            File f2 = new File(f2Name);
            if(!f2.exists()){
                Files.createFile(Paths.get(f2Name));
            }

            PrintWriter pw1 = new PrintWriter(f1Name);
            PrintWriter pw2 = new PrintWriter(f2Name);

            String[] excludedWords = {"oraz", "ponieważ", "gdyż", "albo", "ani", "bądź", "aczkolwiek"};

            for(Element elem : links){
                StringTokenizer st = new StringTokenizer(elem.text()," \"\t\n,.:;?!'");

                while(st.hasMoreTokens()){
                    String word = st.nextToken();

                    if(word.length() >= 3){
                        pw1.println(word);
                    }
                }
            }
            pw1.close();

            Scanner scan = new Scanner(f1);
            while(scan.hasNext()){
                String word = scan.next();
                boolean write = true;

                for(int i = 0; i < excludedWords.length; i++){
                    if(word.toUpperCase().equals(excludedWords[i].toUpperCase())){
                        write = false;
                        break;
                    }
                }
                if(write == true){
                    pw2.println(word);
                }
            }
            pw2.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
