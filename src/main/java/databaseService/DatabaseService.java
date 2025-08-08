package databaseService;

import inputInformation.ActorInput;
import inputInformation.DirectorInput;
import inputInformation.FilmInput;
import inputInformation.GenreInput;
import dao.FilmDao;
import dao.impl.FilmDaoImpl;
import entity.Actor;
import entity.Director;
import entity.Film;
import entity.Genre;

import java.util.*;

public class DatabaseService {
    private final FilmDao filmDao = new FilmDaoImpl();
    private final FilmInput filmInput = new FilmInput();
    private final GenreInput genreInput = new GenreInput();
    private final ActorInput actorInput = new ActorInput();
    private final DirectorInput directorInput = new DirectorInput();
    private static final Scanner scanner = new Scanner(System.in);

    public void searchFilms() {
        while (true) {
            System.out.println("Выберите тип поиска:");
            System.out.println("1. Получить все фильмы");
            System.out.println("2. По названию");
            System.out.println("3. По году выхода");
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
                case "2" -> filmDao.findByName(filmInput.filmNameInput()).stream().toList();
                case "3" -> filmDao.findFilmsByYear(filmInput.yearInput());
                case "4" -> filmDao.findFilmsByDirector(filmInput.directorInput());
                case "5" -> filmDao.findFilmsByActor(filmInput.actorInput());
                case "6" -> filmDao.findTop10Films();
                default -> response;
            };

            if (response.isEmpty()) {
                switch (command) {
                    case "1", "6":
                        System.out.println("В базе данных отсутствуют фильмы!");
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
                System.out.println(response);
                break;
            }
        }
    }

    public void addFilm() {
        String name = filmInput.filmNameInput();
        int year = filmInput.yearInput();

        Set<Genre> genres = genreInput.input();

        Set<Director> directors = directorInput.input();

        Set<Actor> actors = actorInput.input();

        double rating = filmInput.ratingInput();
        int stock = filmInput.stockInput();

        filmDao.add(new Film(null, name, year, genres, directors, actors, rating, stock));
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

            Optional<Film> film = selector(select);

            if (film.isPresent()) {
                Film filmToUpdate = film.get();
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
                    System.out.println("0. Сохранить");

                    String command = scanner.nextLine();

                    switch (command) {
                        case "1":
                            filmToUpdate.setName(filmInput.filmNameInput());
                            break;
                        case "2":
                            filmToUpdate.setYear(filmInput.yearInput());
                            break;
                        case "3":
                            filmToUpdate.setGenres(genreInput.input());
                            break;
                        case "4":
                            filmToUpdate.setDirectors(directorInput.input());
                            break;
                        case "5":
                            filmToUpdate.setActors(actorInput.input());
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
                        case "0":
                            filmDao.update(filmToUpdate);
                            System.out.println("Информация о фильме обновлена!");
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

            Optional<Film> film = selector(select);

            if (film.isPresent()) {
                filmDao.delete(film.get());
                System.out.println("Фильм удалён!");
                break;
            }
        }
    }

    public Optional<Film> selector(String command) {
        switch (command) {
            case "1" -> {
                String filmName = filmInput.filmNameInput();
                Optional<Film> filmToDelete = filmDao.findByName(filmName);

                if (filmToDelete.isEmpty()) {
                    System.out.println("Фильм с данным названием не найден!");
                }
                return filmToDelete;
            }
            case "2" -> {
                Integer filmId = filmInput.idInput();
                Optional<Film> filmToDelete = filmDao.findById(filmId);

                if (filmToDelete.isEmpty()) {
                    System.out.println("Фильм с данным id не найден!");
                }
                return filmToDelete;
            }
            default -> {
                System.out.println("Неизвестная команда.");
                return Optional.empty();
            }
        }
    }
}


