package databaseService;

import entity.*;
import dao.FilmDao;
import inputInformation.*;

import java.util.*;

public class FilmService {
    private final FilmDao filmDao = new FilmDao();
    private final FilmInput filmInput = new FilmInput();
    private final GenreInput genreInput = new GenreInput();
    private final ActorInput actorInput = new ActorInput();
    private final YearsInput yearsInput = new YearsInput();
    private final DirectorInput directorInput = new DirectorInput();
    private static final Scanner scanner = new Scanner(System.in);

    public void searchFilms() {
        while (true) {
            System.out.println("Выберите тип поиска:");
            System.out.println("1. Получить все фильмы");
            System.out.println("2. По названию");
            System.out.println("3. По годам");
            System.out.println("4. По режиссёру");
            System.out.println("5. По актёру");
            System.out.println("6. Топ 10-фильмов по рейтингу");
            System.out.println("0. Выход");

            String command = scanner.nextLine();

            if (command.equals("0")) {
                return;
            }

            List<Film> response = new ArrayList<>();

            response = switch (command) {
                case "1" -> filmDao.findAll();
                case "2" -> filmDao.findByName(filmInput.filmNameInput());
                case "3" -> filmDao.findFilmsByYear(yearsInput.yearsBetweenInput());
                case "4" -> filmDao.findFilmsByDirector(directorInput.input());
                case "5" -> filmDao.findFilmsByActor(actorInput.input());
                case "6" -> filmDao.findTop10Films();
                default -> response;
            };

            if (response.isEmpty()) {
                switch (command) {
                    case "1", "6":
                        System.out.println("В картотеке отсутствуют фильмы!");
                        break;
                    case "2":
                        System.out.println("Не найден фильм с данным названием!");
                        break;
                    case "3":
                        System.out.println("Не найдено фильмов с данным годом выхода!");
                        break;
                    case "4":
                        System.out.println("Не найдено фильмов с данным режиссёром!");
                        break;
                    case "5":
                        System.out.println("Не найдено фильмов с данным актёром!");
                        break;
                }
            } else {
                for (Film film : response) {
                    System.out.println(film);
                }
            }
        }
    }

    public void addFilm() {
        String name = filmInput.filmNameInput();
        int year = filmInput.yearInput();
        double rating = filmInput.ratingInput();
        int stock = filmInput.stockInput();

        Set<Genre> genres = genreInput.addGenreToMovie();

        Set<Director> directors = directorInput.addDirectorToMovie();

        Set<Actor> actors = actorInput.addActorToMovie();

        filmDao.add(new Film(null, name, year, rating, stock, genres, directors, actors));
        System.out.println("Фильм " + name + " добавлен в картотеку!");
    }

    public void updateFilm() {
        while (true) {
            System.out.println("Как вы желаете обновить информацию по фильму?");
            System.out.println("1. По названию");
            System.out.println("2. По id");
            System.out.println("0. Выход");

            String select = scanner.nextLine();

            if (select.equals("0")) {
                return;
            }

            Film filmToUpdate = selector(select);

            if (filmToUpdate != null) {
                System.out.println("Найден фильм " + filmToUpdate.getName());
                while (true) {
                    System.out.println("Что вы желаете изменить?");
                    System.out.println("1. Название");
                    System.out.println("2. Год выхода");
                    System.out.println("3. Жанр");
                    System.out.println("4. Режиссёр");
                    System.out.println("5. Актёры");
                    System.out.println("6. Рейтинг");
                    System.out.println("7. Количество на складе");
                    System.out.println("8. Вывести информацию о фильме");
                    System.out.println("9. Сохранить");
                    System.out.println("0. Отмена");

                    String command = scanner.nextLine();

                    switch (command) {
                        case "1":
                            filmToUpdate.setName(filmInput.filmNameInput());
                            break;
                        case "2":
                            filmToUpdate.setYear(filmInput.yearInput());
                            break;
                        case "3":
                            filmToUpdate.setGenres(genreInput.addGenreToMovie());
                            break;
                        case "4":
                            filmToUpdate.setDirectors(directorInput.addDirectorToMovie());
                            break;
                        case "5":
                            filmToUpdate.setActors(actorInput.addActorToMovie());
                            break;
                        case "6":
                            filmToUpdate.setRating(filmInput.ratingInput());
                            break;
                        case "7":
                            filmToUpdate.setStock(filmInput.stockInput());
                            break;
                        case "8":
                            System.out.println(filmToUpdate);
                            break;
                        case "9":
                            filmDao.update(filmToUpdate);
                            System.out.println("Информация о фильме " + filmToUpdate.getName() + " обновлена!");
                            return;
                        case "0":
                            return;
                    }
                }
            }
        }
    }

    public void deleteFilm() {
        while (true) {
            System.out.println("Как вы желаете удалить фильм?");
            System.out.println("1. По названию");
            System.out.println("2. По id");
            System.out.println("0. Выход");

            String select = scanner.nextLine();

            if (select.equals("0")) {
                return;
            }

            Film film = selector(select);

            if (film != null) {
                filmDao.delete(film);
                System.out.println("Фильм " + film.getName() + " удалён из картотеки!");
                break;
            }
        }
    }

    public Film selector(String command) {
        switch (command) {
            case "1" -> {
                String filmName = filmInput.filmNameInput();
                List<Film> filmToDelete = filmDao.findByName(filmName);

                if (filmToDelete.isEmpty()) {
                    System.out.println("Фильм с данным названием не найден!");
                    return null;
                }

                return filmToDelete.get(0);
            }
            case "2" -> {
                Integer filmId = filmInput.idInput();
                Film filmToDelete = filmDao.findById(filmId);

                if (filmToDelete == null) {
                    System.out.println("Фильм с данным id не найден!");
                    return null;
                }
                return filmToDelete;
            }
            default -> {
                System.out.println("Неизвестная команда.");
                return null;
            }
        }
    }
}