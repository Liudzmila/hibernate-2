package in.javarush.sobaleva.hiber2.service;

import in.javarush.sobaleva.hiber2.dao.RentalDao;
import in.javarush.sobaleva.hiber2.entity.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentalService {
    private static final String PURPLE = "\u001B[35m";
    private static final String RESET_PURPLE = "\u001B[0m";

    public static Rental rentFilmFromStore(Session session, RentalDao rentalDao, Short inventoryId, Short customerId, Byte staffId) {
        Rental rental = null;
        try {
            Transaction transaction = session.beginTransaction();

            Inventory inventory = session.get(Inventory.class, inventoryId);

            if (!isInventoryAvailableForRent(session, rentalDao, inventory)) {
                throw new IllegalStateException("Инвентарь недоступен для аренды.");
            }

            Staff staff = session.get(Staff.class, staffId);

            if (staff == null) {
                throw new IllegalArgumentException("Сотрудник не найден для этого магазина.");
            }

            Store store = staff.getStore();

            if (store == null) {
                throw new IllegalArgumentException("Магазин не найден.");
            }

            Film film = inventory.getFilm();
            BigDecimal rentalRate = film.getRentalRate();

            // Фактическая длительность аренды в днях (замените это значение на фактический расчет длительности аренды)
            int actualRentalDays = film.getRentalDuration();

            // Вычисляем общую сумму оплаты за аренду на основе стоимости аренды за день и фактической длительности аренды
            BigDecimal totalAmount = rentalRate.multiply(BigDecimal.valueOf(actualRentalDays));

            rental = createRental(session, LocalDateTime.now(), inventory, customerId, staff);

            inventory.getRentals().add(rental);
            session.update(inventory);
            session.flush();

            Payment payment = createPayment(session, customerId, staff, totalAmount, LocalDateTime.now(), rental);
            session.update(payment);
            session.flush();

            transaction.commit();
            System.out.println(PURPLE + "Фильм с инвентарным ID " + inventoryId +
                    " арендован клиентом с ID " + customerId +
                    " сотрудником с ID " + staffId + RESET_PURPLE);
            System.out.println(PURPLE + "Платеж с ID " + payment.getPaymentId() + " на сумму " + totalAmount + " совершен" + RESET_PURPLE);
            System.out.println(PURPLE + "Новая аренда с ID " + rental.getRentalId() + " создана" + RESET_PURPLE);
            System.out.println("Данные внесены в базу данных");
        } catch (Exception e) {
            rentalDao.handleTransactionException(e);
        }
        return rental;
    }

        public static Rental returnRentedFilm (Session session, RentalDao rentalDao, Short rentalId){
            Rental rental = null;
            try {
                session.beginTransaction();

                rental = session.get(Rental.class, rentalId);
                if (rental == null) {
                    throw new IllegalArgumentException("Аренда с указанным ID не найдена.");
                }

                LocalDateTime returnDate = LocalDateTime.now();
                rental.setReturnDate(returnDate);
                rental.setLastUpdate(returnDate);

                LocalDateTime rentalDate = rental.getRentalDate();
                Film film = rental.getInventory().getFilm();
                BigDecimal rentalRate = film.getRentalRate();

                long daysBetween = ChronoUnit.DAYS.between(rentalDate.toLocalDate(), returnDate.toLocalDate());

                if (daysBetween > film.getRentalDuration()) {
                    BigDecimal additionalDays = BigDecimal.valueOf(daysBetween - film.getRentalDuration());
                    BigDecimal additionalAmount = rentalRate.multiply(additionalDays);

                    Payment payment = createPayment(session, rental.getCustomer().getCustomerId(), rental.getStaff(), additionalAmount, LocalDateTime.now(), rental);
                    session.save(payment);
                    System.out.println("Внесена доплата по аренде за дополнительные дни: ID платежа " + payment.getPaymentId() + " по аренде с ID " + rentalId);
                }

                session.update(rental);
                session.getTransaction().commit();
                System.out.println(PURPLE + "Фильм успешно возвращен клиентом с ID " + rental.getCustomer().getCustomerId() + ", ID аренды: " + rentalId + RESET_PURPLE);
                System.out.println("Данные внесены в базу данных");
            } catch (Exception e) {
                rentalDao.handleTransactionException(e);
            }
            return rental;
        }

        private static boolean isInventoryAvailableForRent(Session session, RentalDao rentalDao, Inventory inventory) {
            if (inventory == null) {
                return false;
            }

            try {
                session.refresh(inventory);
                Hibernate.initialize(inventory.getRentals());
                List<Rental> rentals = inventory.getRentals();

                for (Rental rental : rentals) {
                    if (rental.getReturnDate() == null) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                rentalDao.handleTransactionException(e);
                return false;
            }
    }

    private static Rental createRental(Session session, LocalDateTime rentalDate, Inventory inventory, Short customerId, Staff staff) {
        Rental rental = new Rental();
        rental.setRentalDate(rentalDate);
        rental.setInventory(inventory);
        rental.setCustomer(session.get(Customer.class, customerId));
        rental.setStaff(staff);
        rental.setLastUpdate(LocalDateTime.now());
        session.save(rental);
        return rental;
    }

    private static Payment createPayment(Session session, Short customerId, Staff staff, BigDecimal amount, LocalDateTime paymentDate, Rental rental) {
        Payment payment = new Payment();
        payment.setCustomer(session.get(Customer.class, customerId));
        payment.setStaff(staff);
        payment.setAmount(amount);
        payment.setPaymentDate(paymentDate);
        session.save(payment);

        payment.setRental(rental);

        payment.setLastUpdate(LocalDateTime.now());

        return payment;
    }
}
