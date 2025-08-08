import databaseService.FilmService;

import java.util.Scanner;

public class FilmsLibrary {
    static Scanner scanner = new Scanner(System.in);
    private final FilmService filmService = new FilmService();

    public static void main(String[] args) {
        new FilmsLibrary().menu();
    }

    public void menu() {
        System.out.println("\n----------------------------");
        System.out.println("Картотека фильмов. Demo ver.");
        System.out.println("----------------------------");

        while (true) {
            System.out.println("\nВыберите функцию:");
            System.out.println("1. Поиск фильмов");
            System.out.println("2. Добавить фильм");
            System.out.println("3. Обновить фильм");
            System.out.println("4. Удалить фильм");
            System.out.println("0. Выход");

            switch (scanner.nextLine()) {
                case "1":
                    filmService.searchFilms();
                    break;
                case "2":
                    filmService.addFilm();
                    break;
                case "3":
                    filmService.updateFilm();
                    break;
                case "4":
                    filmService.deleteFilm();
                    break;
                case "0":
                    System.out.println("The End!");
                    return;
            }
        }
    }
}