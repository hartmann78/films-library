package inputInformation;

import dao.impl.GenreDaoImpl;
import entity.Genre;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class GenreInput {
    private final Scanner scanner = new Scanner(System.in);
    private final GenreDaoImpl genreDao = new GenreDaoImpl();

    public Set<Genre> input() {
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

                Optional<Genre> genre = getGenre(genreName);

                if (genre.isPresent()) {
                    genres.add(genre.get());
                } else {
                    Genre newGenre = new Genre(null, genreName, new HashSet<>());
                    addGenre(newGenre);
                    genres.add(newGenre);
                }
            } catch (Exception e) {
                System.out.println("Название жанра не должно превышать " + limit + " символов!");
            }
        }
    }

    public Optional<Genre> getGenre(String name) {
        return genreDao.findByName(name);
    }

    public void addGenre(Genre genre) {
        genreDao.add(genre);
    }

    public void updateGenre(Genre genre) {
        genreDao.update(genre);
    }

    public void deleteGenre(Genre genre) {
        genreDao.delete(genre);
    }
}
