package inputInformation;

import dto.MovieByYear;

import java.time.LocalDate;
import java.util.Scanner;

public class YearsInput {
    private final Scanner scanner = new Scanner(System.in);

    public MovieByYear yearsBetweenInput() {
        while (true) {
            try {
                System.out.println("Введите начальный год:");
                int startYear = Integer.parseInt(scanner.nextLine());

                if (startYear < 1888 || startYear > LocalDate.now().getYear()) {
                    throw new Exception();
                }

                System.out.println("Введите конечный год:");
                int endYear = Integer.parseInt(scanner.nextLine());

                if (endYear < 1888 || endYear > LocalDate.now().getYear()) {
                    throw new Exception();
                }

                return new MovieByYear(startYear, endYear);
            } catch (Exception e) {
                System.out.println("Год должен быть не раньше 1888 и не больше " + LocalDate.now().getYear());
            }
        }
    }
}
