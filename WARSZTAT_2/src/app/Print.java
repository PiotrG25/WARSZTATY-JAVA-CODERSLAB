package app;

public class Print {
    public static void printClasses(){
        System.out.println(
                "Wybierz klase na ktorej chcesz operowac\n" +
                        "[exercise][solution][user_group][users]\n" +
                        "[quit - zakonczenie programu]"
        );
    }
    public static void printOptions(String add, String edit, String delete){
        System.out.printf(
                "Wpisz jaka akcje chcesz wykonac\n" +
                        "[quit] - zakonczenie programu\n" +
                        "[add] - %s\n" +
                        "[edit] - %s\n" +
                        "[delete] - %s\n"
                , add, edit, delete);
    }
    public static void ptintUsersOptions(){
        printOptions("dodaj uzytkownika", "edytuj uzytkownika", "usun uzytkownika");
        System.out.print("[print] - wyswietl uzytkownika\n" +
                "[printAll] - wyswietl wszystkich uzytkownikow\n"
        );
    }
    public static void  ptintoptions(String add, String view){
        System.out.printf(
                        "[add - %s]\n" +
                        "[view - %s]\n" +
                        "[quit - zakonczenie programu]\n"
                , add, view);
    }
}
