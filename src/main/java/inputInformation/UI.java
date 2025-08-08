package inputInformation;

import databaseService.DatabaseService;

import java.util.Scanner;

public class UI {
    static Scanner scanner = new Scanner(System.in);
    private final DatabaseService databaseService = new DatabaseService();

    public void menu() {
        System.out.println("\n--------------------");
        System.out.println("Видеотека. Demo ver.");
        System.out.println("--------------------");

        while (true) {
            System.out.println("\nВыберите функцию:");
            System.out.println("1. Поиск");
            System.out.println("2. Добавить фильм");
            System.out.println("3. Обновить фильм");
            System.out.println("4. Удалить фильм");
            System.out.println("0. Выход");

            switch (scanner.nextLine()) {
                case "1":
                    databaseService.searchFilms();
                    break;
                case "2":
                    databaseService.addFilm();
                    break;
                case "3":
                    databaseService.updateFilm();
                    break;
                case "4":
                    databaseService.deleteFilm();
                    break;
                case "0":
                    return;
            }
        }
    }
}
