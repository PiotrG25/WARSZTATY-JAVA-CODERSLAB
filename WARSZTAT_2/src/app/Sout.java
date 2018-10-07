package app;

public class Sout {
    public static void soutClasses(){
        System.out.println(
                "Wybierz klase na ktorej chcesz operowac\n" +
                        "[exercise][solution][user_group][users]\n" +
                        "[quit - zakonczenie programu]"
        );
    }
    public static void soutOptions(String add, String edit, String delete){
        System.out.printf(
                "Wpisz jaka akcje chcesz wykonac\n" +
                        "[add - %s]\n" +
                        "[edit - %s]\n" +
                        "[delete - %s]\n" +
                        "[quit - zakonczenie programu]\n"
                , add, edit, delete);
    }
    public static void  soutoptions(String add, String view){
        System.out.printf(
                "[add - %s]\n" +
                        "[view - %s]\n" +
                        "[quit - zakonczenie programu]\n"
                , add, view);
    }
}
