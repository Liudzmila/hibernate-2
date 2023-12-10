package in.javarush.sobaleva.hiber2.util;

import in.javarush.sobaleva.hiber2.dao.CustomerDao;
import in.javarush.sobaleva.hiber2.service.NewAddressData;
import in.javarush.sobaleva.hiber2.service.NewCustomerData;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CustomerWithAddressInputUtil {
    public static void runCustomerWithAddressInput(Scanner scanner) {
        LocalDateTime today = LocalDateTime.now();

        System.out.println("Для внесения информации о клиенте, введите данные");

        String address = readAddress(scanner);
        String address2 = readAddress2(scanner);
        String district = readDistrict(scanner);
        short cityId = readCityId(scanner);
        String postalCode = readPostalCode(scanner);
        String phone = readPhone(scanner);
        LocalDateTime lastUpdate = LocalDateTime.now();

        String firstName = readFirstName(scanner);
        String lastName = readLastName(scanner);
        String email = readEmail(scanner);
        Byte storeId = readStoreId(scanner);
        boolean active = readActiveStatus(scanner);

        NewAddressData addressData = new NewAddressData(address, address2, district, cityId, postalCode, phone, lastUpdate);
        NewCustomerData customerData = new NewCustomerData(firstName, lastName, email, storeId, active, today);
        CustomerDao customerDao = new CustomerDao();
        customerDao.createCustomerWithAddress(customerData, addressData);
    }

    private static String readFirstName(Scanner scanner) {
        String firstName;
        do {
            System.out.print("Введите имя клиента (не более 45 символов): ");
            firstName = scanner.nextLine();
            if (firstName.length() > 45) {
                System.out.println("Имя должно содержать не более 45 символов. Попробуйте снова.");
            }
        } while (firstName.length() > 45);
        return firstName.toUpperCase();
    }

    private static String readLastName(Scanner scanner) {
        String lastName;
        do {
            System.out.print("Введите фамилию клиента (не более 45 символов): ");
            lastName = scanner.nextLine();
            if (lastName.length() > 45) {
                System.out.println("Фамилия должна содержать не более 45 символов. Попробуйте снова.");
            }
        } while (lastName.length() > 45);
        return lastName.toUpperCase();
    }

    private static String readEmail(Scanner scanner) {
        String email;
        do {
            System.out.print("Введите адрес электронной почты клиента (не более 50 символов): ");
            email = scanner.nextLine();
            if (email.length() > 50) {
                System.out.println("Адрес электронной почты должен содержать не более 50 символов. Попробуйте снова.");
            }
        } while (email.length() > 50);
        return email;
    }

    private static Byte readStoreId(Scanner scanner) {
        byte storeId;
        do {
            System.out.print("Введите ID магазина (допустимые значения: 1 или 2): ");
            while (!scanner.hasNextByte()) {
                System.out.println("Неверный формат. Введите целое число.");
                scanner.next();
            }
            storeId = scanner.nextByte();
            scanner.nextLine();

            if (storeId != 1 && storeId != 2) {
                System.out.println("ID магазина должен быть равен 1 или 2. Попробуйте снова.");
            }
        } while (storeId != 1 && storeId != 2);
        return storeId;
    }

    private static Boolean readActiveStatus(Scanner scanner) {
        System.out.print("Активный клиент? (true/false): ");
        return Boolean.parseBoolean(scanner.nextLine());
    }

    private static String readAddress(Scanner scanner) {
        System.out.print("Введите адрес: ");
        return scanner.nextLine();
    }

    private static String readAddress2(Scanner scanner) {
        System.out.print("Введите адрес (дополнение, если есть): ");
        return scanner.nextLine();
    }

    private static String readDistrict(Scanner scanner) {
        System.out.print("Введите район: ");
        return scanner.nextLine();
    }

    public static Short readCityId(Scanner scanner) {
        short cityId;
        do {
            System.out.print("Введите ID города: ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            cityId = scanner.nextShort();
            scanner.nextLine();
        } while (cityId <= 0);
        return cityId;
    }

    private static String readPostalCode(Scanner scanner) {
        System.out.print("Введите почтовый индекс: ");
        return scanner.nextLine();
    }

    private static String readPhone(Scanner scanner) {
        System.out.print("Введите номер телефона: ");
        return scanner.nextLine();
    }
}