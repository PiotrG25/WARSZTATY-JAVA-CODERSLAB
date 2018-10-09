package app;

public class Print {
    public static void printClasses(){
        System.out.println(
                "Wybierz klase na ktorej chcesz operowac\n" +
                "[exercise][solution][user_group][users]" +
                "[quit] - zakonczenie programu"
        );
    }

    public static void printExerciseOptions(){
        printOptions(
                "dodaj zadanie",
                "edytuj zadanie",
                "usun zadanie",
                "wyswietl zadanie",
                "wyswietl wszystkie zadania"
        );
    }

    public static void printSolutionOptions(){
        printOptions(
                "dodaj rozwiazanie",
                "edytuj rozwiazanie",
                "usun rozwiazanie",
                "wyswietl rozwiazanie",
                "wyswietl wszystkie rozwiazania"
        );
    }

    public static void printUser_groupOptions(){
        printOptions(
                "dodaj grupe",
                "edytuj grupe",
                "usun grupe",
                "wyswietl grupe",
                "wyswietl wszystkie grupy"
        );
    }

    public static void ptintUsersOptions(){
        printOptions(
                "dodaj uzytkownika",
                "edytuj uzytkownika",
                "usun uzytkownika",
                "wyswietl uzytkownika",
                "wyswietl wszystkich uzytkownikow"
        );
    }

    private static void printOptions(String add, String edit, String delete, String print, String printAll){
        System.out.printf(
                "Wpisz jaka akcje chcesz wykonac\n" +
                        "[quit] - zakonczenie programu\n" +
                        "[add] - %s\n" +
                        "[edit] - %s\n" +
                        "[delete] - %s\n" +
                        "[print] - %s\n" +
                        "[printAll] - %s\n"
                , add, edit, delete, print, printAll
        );
    }
}
