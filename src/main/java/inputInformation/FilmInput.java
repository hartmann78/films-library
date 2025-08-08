package inputInformation;

import java.time.LocalDate;
import java.util.Scanner;

public class FilmInput {
    private static final Scanner scanner = new Scanner(System.in);

    public String filmNameInput() {
        int limit = 255;
        while (true) {
            try {
                System.out.println("Введите название фильма:");
                String filmName = scanner.nextLine();

                if (filmName.length() > limit) {
                    throw new Exception();
                }

                return filmName;
            } catch (Exception e) {
                System.out.println("Название фильма не должно превышать " + limit + " символов!");
            }
        }
    }

    public int yearInput() {
        while (true) {
            try {
                System.out.println("Введите год выхода фильма:");
                int year = Integer.parseInt(scanner.nextLine());

                if (year < 1888 || year > LocalDate.now().getYear()) {
                    throw new Exception();
                }

                return year;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно введён год!");
            } catch (Exception e) {
                System.out.println("Год выхода фильма должен быть не раньше 1888 и не больше " + LocalDate.now().getYear());
            }
        }
    }

    public String directorInput() {
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


    public String actorInput() {
        int limit = 64;
        while (true) {
            try {
                System.out.println("Введите имя актёра:");
                String actorName = scanner.nextLine();

                if (actorName.length() > limit) {
                    throw new Exception();
                }

                return actorName;
            } catch (Exception e) {
                System.out.println("Имя актёра не должно превышать " + limit + " символов!");
            }
        }
    }

    public double ratingInput() {
        while (true) {
            try {
                System.out.println("Введите рейтинг фильма:");
                double rating = Double.parseDouble(scanner.nextLine());

                if (rating < 0 || rating > 10) {
                    throw new Exception();
                }

                return rating;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно введён рейтинг!");
            } catch (Exception e) {
                System.out.println("Рейтинг фильма должен быть не меньше 0.00 и не больше 10.00");
            }
        }
    }

    public int stockInput() {
        while (true) {
            try {
                System.out.println("Введите количество фильма на складе:");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 0) {
                    throw new Exception();
                }

                return quantity;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно введёно количество!");
            } catch (Exception e) {
                System.out.println("Количество должно быть не меньше 0");
            }
        }
    }

    public Integer idInput() {
        while (true) {
            try {
                System.out.println("Введите id фильма:");
                int id = Integer.parseInt(scanner.nextLine());

                if (id < 0) {
                    throw new Exception();
                }

                return id;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно введён id!");
            } catch (Exception e) {
                System.out.println("id должно быть не меньше 0");
            }
        }
    }
}
