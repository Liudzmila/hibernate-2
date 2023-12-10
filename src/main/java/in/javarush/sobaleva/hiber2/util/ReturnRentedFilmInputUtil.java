package in.javarush.sobaleva.hiber2.util;

import in.javarush.sobaleva.hiber2.dao.RentalDao;

import java.util.Scanner;

public class ReturnRentedFilmInputUtil {
    public static void runReturnRentedFilmInput(Scanner scanner) {
        System.out.println("Введите данные для того, чтобы вернуть арендованный фильм");

        Short rentalId = readRentalId(scanner);

        RentalDao rentalDao = new RentalDao();
        rentalDao.returnRentedFilm(rentalId);
    }

    private static Short readRentalId(Scanner scanner) {
        short rentalId;
        do {
            System.out.print("Введите ID аренды: ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            rentalId = scanner.nextShort();
            scanner.nextLine();
        } while (rentalId <= 0);
        return rentalId;
    }
}