package inputInformation;

import dao.DirectorDao;
import entity.Director;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DirectorInput {
    private final Scanner scanner = new Scanner(System.in);
    private final DirectorDao directorDao = new DirectorDao();

    public String input() {
        int limit = 64;
        while (true) {
            try {
                System.out.println("Введите имя режиссёра:");
                String directorName = scanner.nextLine();

                if (directorName.length() > limit) {
                    throw new Exception();
                }

                return directorName;
            } catch (Exception e) {
                System.out.println("Имя режиссёра не должно превышать " + limit + " символов!");
            }
        }
    }

    public Set<Director> addDirectorToMovie() {
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

                Director director = directorDao.findByName(directorName);

                if (director != null) {
                    directors.add(director);
                } else {
                    Director newDirector = new Director(null, directorName, new HashSet<>());
                    directorDao.add(newDirector);
                    directors.add(newDirector);
                }
            } catch (Exception e) {
                System.out.println("Имя актёра не должно превышать " + limit + " символов!");
            }
        }
    }
}
