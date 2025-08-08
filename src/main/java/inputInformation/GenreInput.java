package inputInformation;

import dao.GenreDao;
import entity.Genre;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GenreInput {
    private final Scanner scanner = new Scanner(System.in);
    private final GenreDao genreDao = new GenreDao();

    public Set<Genre> addGenreToMovie() {
        Set<Genre> genres = new HashSet<>();
        int limit = 64;
        while (true) {
            try {
                if (!genres.isEmpty()) {
                    System.out.println("Добавленные жанры:" + genres);
                }

                System.out.println("Введите жанры фильма. Чтобы сохранить, введите 0:");
                String genreName = scanner.nextLine();

                if (genreName.equals("0")) {
                    return genres;
                }

                if (genreName.length() > limit) {
                    throw new Exception();
                }

                Genre genre = genreDao.findByName(genreName);

                if (genre!=null) {
                    genres.add(genre);
                } else {
                    Genre newGenre = new Genre(null, genreName, new HashSet<>());
                    genreDao.add(newGenre);
                    genres.add(newGenre);
                }
            } catch (Exception e) {
                System.out.println("Название жанра не должно превышать " + limit + " символов!");
            }
        }
    }
}
