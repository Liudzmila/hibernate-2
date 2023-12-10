package in.javarush.sobaleva.hiber2;

import in.javarush.sobaleva.hiber2.util.CustomerWithAddressInputUtil;
import in.javarush.sobaleva.hiber2.util.FilmInputUtil;
import in.javarush.sobaleva.hiber2.util.RentFilmFromStoreInputUtil;
import in.javarush.sobaleva.hiber2.util.ReturnRentedFilmInputUtil;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /* Задание 9:
          Добавить транзакционный метод, который описывает событие
          «сняли новый фильм, и он стал доступен для аренды».
          Фильм, язык, актеров, категории и т д выбери на свое усмотрение.

          Task 9:
          Add a transactional method that describes the event of
          'renting a new film, making it available for rental'.
          Choose the film, language, actors, categories, etc., at your discretion. */

        FilmInputUtil.runFilmInput(scanner);

        /* Задание 8:
           Добавить транзакционный метод, который описывает событие
           «покупатель сходил в магазин (store)
           и арендовал (rental) там инвентарь (inventory).
           При этом он сделал оплату (payment) у продавца (staff)».
           Фильм (через инвентарь) выбери на свое усмотрение.
           Единственное ограничение – фильм должен быть доступен для аренды.
           То есть либо в rental не должно быть вообще записей по инвентарю,
           либо должна быть заполнена колонка return_date таблицы rental
           для последней аренды этого инвентаря.

           Task 8: Add a transactional method that describes the event of
           'a customer visiting a store (store)
           and renting inventory there'.
           Additionally, the customer made a payment to the staff.
           Choose the film (via inventory) at your discretion.
           The only constraint is that the film must be available for rent.
           This means there should either be no records in the rental table for this inventory,
           or the 'return_date' column in the rental table should be filled
           for the latest rental of this inventory. */

        RentFilmFromStoreInputUtil.runRentFilmFromStoreInput(scanner);

        /* Задание 7: Добавить транзакционный метод,
          который описывает событие «покупатель пошел и вернул ранее арендованный фильм».
          Покупателя и событие аренды выбери любое на свое усмотрение.
          Рейтинг фильма пересчитывать не нужно.

          Task 7: Add a transactional method that describes the event of
          'a customer returning a previously rented film'.
          Choose any customer and rental event at your discretion.
          There's no need to recalculate the film's rating. */

        ReturnRentedFilmInputUtil.runReturnRentedFilmInput(scanner);

        /* Задание 6: Добавить метод, который умеет создавать нового покупателя
          (таблица customer) со всеми зависимыми полями.
          Не забудь сделать чтоб метод был транзакционным
          (чтоб не попасть в ситуацию что адрес покупателя записали в БД,
          а самого покупателя – нет).

          Task 6: Add a method capable of creating a new customer
          (in the 'customer' table) with all related fields.
          Make sure the method is transactional
          to avoid a situation where the customer's address is stored in the database
          but the customer itself is not. */

        CustomerWithAddressInputUtil.runCustomerWithAddressInput(scanner);

        scanner.close();
    }
}