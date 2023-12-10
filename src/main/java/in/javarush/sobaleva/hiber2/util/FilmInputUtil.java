package in.javarush.sobaleva.hiber2.util;

import in.javarush.sobaleva.hiber2.dao.FilmDao;
import in.javarush.sobaleva.hiber2.service.NewFilmData;
import in.javarush.sobaleva.hiber2.entity.enums.FilmRating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FilmInputUtil {
    public static void runFilmInput(Scanner scanner) {
        LocalDate today = LocalDate.now();

        System.out.println("Введите данные для создания нового фильма для аренды");

        String filmTitle = readFilmTitle(scanner);
        String filmDescription = readFilmDescription(scanner);
        short releaseYear = readReleaseYear(scanner, today);
        BigDecimal rentalRate = readRentalRate(scanner);
        Short rentalDuration = readRentalDuration(scanner);
        BigDecimal replacementCost = readReplacementCost(scanner);
        Short languageId = readLanguageId(scanner);
        Short originalLanguageId = readOriginalLanguageId(scanner);
        Byte storeId = readStoreId(scanner);
        FilmRating rating = readFilmRating(scanner);
        Short length = readFilmLength(scanner);

        Set<Short> actorsIds = readActorIds(scanner);
        Set<Byte> categoriesIds = readCategoryIds(scanner);

        Set<String> specialFeatures = readSpecialFeatures(scanner);

        NewFilmData filmData = new NewFilmData(filmTitle, filmDescription, releaseYear, rentalRate, rentalDuration, replacementCost, languageId, originalLanguageId, actorsIds, categoriesIds, storeId, rating, length, specialFeatures);

        FilmDao filmDao = new FilmDao();
        filmDao.addNewFilmForRent(filmData);
    }

    private static String readFilmTitle(Scanner scanner) {
        String filmTitle;
        do {
            System.out.print("Введите название фильма (не более 128 символов): ");
            filmTitle = scanner.nextLine();
            if (filmTitle.length() > 128) {
                System.out.println("Название фильма слишком длинное. Повторите ввод.");
            }
        } while (filmTitle.length() > 128);
        return filmTitle;
    }

    private static String readFilmDescription(Scanner scanner) {
        String filmDescription;
        do {
            System.out.print("Введите описание фильма (не более 500 символов): ");
            filmDescription = scanner.nextLine();
            if (filmDescription.length() > 500) {
                System.out.println("Описание фильма слишком длинное. Повторите ввод.");
            }
        } while (filmDescription.length() > 500);
        return filmDescription;
    }

    private static short readReleaseYear(Scanner scanner, LocalDate today) {
        short releaseYear;
        do {
            System.out.print("Введите год выпуска фильма (от 1901 до " + today.getYear() + "): ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат года. Попробуйте снова.");
                scanner.next();
            }
            releaseYear = scanner.nextShort();
            scanner.nextLine(); // Чтение новой строки после nextShort()
            if (releaseYear < 1901 || releaseYear > today.getYear()) {
                System.out.println("Год выпуска фильма должен быть от 1895 до " + today.getYear() + ". Попробуйте снова.");
            }
        } while (releaseYear < 1901 || releaseYear > today.getYear());
        return releaseYear;
    }

    private static Set<String> readSpecialFeatures(Scanner scanner) {
        Set<String> specialFeatures = new HashSet<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("Введите специальные характеристики (Trailers, Commentaries, Deleted Scenes, Behind the Scenes) или 'exit' для завершения: ");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "exit":
                    exit = true;
                    break;
                case "trailers":
                case "commentaries":
                case "deleted scenes":
                case "behind the scenes":
                    specialFeatures.add(input);
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
                    break;
            }
        }
        return specialFeatures;
    }

    private static Set<Byte> readCategoryIds(Scanner scanner) {
        Set<Byte> categoryIds = new HashSet<>();
        boolean exit = false;

        while (!exit) {
            System.out.print("Введите ID категории или 'exit' для завершения: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                exit = true;
            } else {
                try {
                    byte categoryId = Byte.parseByte(input);
                    if (categoryId > 0) {
                        categoryIds.add(categoryId);
                    } else {
                        System.out.println("Неверный ID. Введите положительное число или 'exit' для завершения.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ввода. Введите положительное число или 'exit' для завершения.");
                }
            }
        }
        return categoryIds;
    }

    private static Set<Short> readActorIds(Scanner scanner) {
        Set<Short> actorIds = new HashSet<>();
        boolean exit = false;

        while (!exit) {
            System.out.print("Введите ID актера или 'exit' для завершения: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                exit = true;
            } else {
                try {
                    short actorId = Short.parseShort(input);
                    if (actorId > 0) {
                        actorIds.add(actorId);
                    } else {
                        System.out.println("Неверный ID. Введите положительное число или 'exit' для завершения.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ввода. Введите положительное число или 'exit' для завершения.");
                }
            }
        }
        return actorIds;
    }

    private static BigDecimal readRentalRate(Scanner scanner) {
        BigDecimal rentalRate;
        do {
            System.out.print("Введите стоимость аренды фильма: ");
            while (!scanner.hasNextBigDecimal()) {
                System.out.println("Неверный формат стоимости. Попробуйте снова.");
                scanner.next();
            }
            rentalRate = scanner.nextBigDecimal();
            scanner.nextLine(); // Чтение новой строки после nextBigDecimal()
            if (rentalRate.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Стоимость аренды должна быть положительным числом. Попробуйте снова.");
            }
        } while (rentalRate.compareTo(BigDecimal.ZERO) < 0);
        return rentalRate;
    }

    private static Short readRentalDuration(Scanner scanner) {
        short rentalDuration;
        do {
            System.out.print("Введите длительность аренды фильма: ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат длительности. Попробуйте снова.");
                scanner.next();
            }
            rentalDuration = scanner.nextShort();
            scanner.nextLine();
        } while (rentalDuration <= 0);
        return rentalDuration;
    }

    private static BigDecimal readReplacementCost(Scanner scanner) {
        BigDecimal replacementCost;
        do {
            System.out.print("Введите стоимость замены фильма: ");
            while (!scanner.hasNextBigDecimal()) {
                System.out.println("Неверный формат стоимости. Попробуйте снова.");
                scanner.next();
            }
            replacementCost = scanner.nextBigDecimal();
            scanner.nextLine();
        } while (replacementCost.compareTo(BigDecimal.ZERO) < 0);
        return replacementCost;
    }

    private static Short readLanguageId(Scanner scanner) {
        short languageId;
        do {
            System.out.print("Введите ID языка фильма: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            languageId = scanner.nextShort();
            scanner.nextLine();
        } while (languageId <= 0);
        return languageId;
    }

    private static Short readOriginalLanguageId(Scanner scanner) {
        short originalLanguageId;
        do {
            System.out.print("Введите ID языка оригинала фильма: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            originalLanguageId = scanner.nextShort();
            scanner.nextLine();
        } while (originalLanguageId <= 0);
        return originalLanguageId;
    }

    private static Byte readStoreId(Scanner scanner) {
        byte storeId;
        do {
            System.out.print("Введите ID магазина: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат ID. Попробуйте снова.");
                scanner.next();
            }
            storeId = scanner.nextByte();
            scanner.nextLine();
        } while (storeId <= 0);
        return storeId;
    }

    private static Short readFilmLength(Scanner scanner) {
        short filmLength;
        do {
            System.out.print("Введите длительность фильма в минутах: ");
            while (!scanner.hasNextShort()) {
                System.out.println("Неверный формат длительности. Попробуйте снова.");
                scanner.next();
            }
            filmLength = scanner.nextShort();
            scanner.nextLine();
        } while (filmLength <= 0);
        return filmLength;
    }

    private static FilmRating readFilmRating(Scanner scanner) {
        FilmRating rating;

            System.out.println("Выберите рейтинг из доступных вариантов: G, PG, PG-13, R, NC-17 (по умолчанию рейтинг G)");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "PG":
                    rating = FilmRating.PG;
                    break;
                case "PG-13":
                    rating = FilmRating.PG13;
                    break;
                case "R":
                    rating = FilmRating.R;
                    break;
                case "NC-17":
                    rating = FilmRating.NC17;
                    break;
                case "G":
                default:
                    rating = FilmRating.G;
                    break;
        }
        return rating;
    }
}