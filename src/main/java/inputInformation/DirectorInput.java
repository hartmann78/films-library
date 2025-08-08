package inputInformation;

import dao.impl.DirectorDaoImpl;
import entity.Director;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class DirectorInput {
    private final Scanner scanner = new Scanner(System.in);
    private final DirectorDaoImpl directorDao = new DirectorDaoImpl();

    public Set<Director> input() {
        Set<Director> directors = new HashSet<>();
        int limit = 64;
        while (true) {
            try {
                if (!directors.isEmpty()) {
                    System.out.println("Добавленные режиссёры:" + directors);
                }

                System.out.println("Введите имя режиссёра. Чтобы сохранить, введите 0:");
                String directorName = scanner.nextLine();

                if (directorName.equals("0")) {
                    return directors;
                }

                if (directorName.length() > limit) {
                    throw new Exception();
                }

                Optional<Director> director = getDirector(directorName);

                if (director.isPresent()) {
                    directors.add(director.get());
                } else {
                    Director newDirector = new Director(null, directorName, new HashSet<>());
                    addDirector(newDirector);
                    directors.add(newDirector);
                }
            } catch (Exception e) {
                System.out.println("Имя актёра не должно превышать " + limit + " символов!");
            }
        }
    }

    public Optional<Director> getDirector(String name) {
        return directorDao.findByName(name);
    }

    public void addDirector(Director director) {
        directorDao.add(director);
    }

    public void updateActor(Director director) {
        directorDao.update(director);
    }

    public void deleteDirector(Director director) {
        directorDao.delete(director);
    }
}
