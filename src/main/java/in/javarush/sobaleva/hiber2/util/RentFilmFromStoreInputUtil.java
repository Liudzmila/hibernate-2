package in.javarush.sobaleva.hiber2.util;

import in.javarush.sobaleva.hiber2.dao.RentalDao;

import java.util.Scanner;

public class RentFilmFromStoreInputUtil {
    public static void runRentFilmFromStoreInput(Scanner scanner) {
        System.out.println("Введите данные для того, чтобы арендовать инвентарь");

        Short inventoryId = readInventoryId(scanner);
        Short customerId = readCustomerId(scanner);
        Byte staffId = readStaffId(scanner);

        RentalDao rentalDao = new RentalDao();
        rentalDao.rentFilmFromStore(inventoryId, customerId, staffId);
    }

    private static Short readInventoryId(Scanner scanner) {
        short inventoryId;
        do {
            System.out.print("Введите ID инвентаря: ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            inventoryId = scanner.nextShort();
            scanner.nextLine();
        } while (inventoryId <= 0);
        return inventoryId;
    }

    private static Short readCustomerId(Scanner scanner) {
        short customerId;
        do {
            System.out.print("Введите ID клиента: ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            customerId = scanner.nextShort();
            scanner.nextLine();
        } while (customerId <= 0);
        return customerId;
    }

    private static Byte readStaffId(Scanner scanner) {
        byte staffId;
        do {
            System.out.print("Введите ID сотрудника: ");
            while (!scanner.hasNextByte()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            staffId = scanner.nextByte();
            scanner.nextLine();
        } while (staffId <= 0);
        return staffId;
    }
}