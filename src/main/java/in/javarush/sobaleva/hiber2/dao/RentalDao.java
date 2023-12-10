package in.javarush.sobaleva.hiber2.dao;

import in.javarush.sobaleva.hiber2.entity.*;
import in.javarush.sobaleva.hiber2.service.RentalService;
import org.hibernate.Session;


public class RentalDao extends DaoImpl<Rental, Short> {
    public RentalDao() {
        super(Rental.class);
    }

    public Rental returnRentedFilm(Short rentalId) {
        Rental rental = null;
        try (Session session = getSession()) {
            rental = RentalService.returnRentedFilm(session, this, rentalId);
        } catch (Exception e) {
            handleTransactionException(e);
        }
        return rental;
    }

    public Rental rentFilmFromStore(Short inventoryId, Short customerId, Byte staffId) {
        Rental rental = null;
        try (Session session = getSession()) {
            rental = RentalService.rentFilmFromStore(session, this, inventoryId, customerId, staffId);
        } catch (Exception e) {
            handleTransactionException(e);
        }
        return rental;
    }
}