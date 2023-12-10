package in.javarush.sobaleva.hiber2.dao;

import in.javarush.sobaleva.hiber2.entity.Film;
import in.javarush.sobaleva.hiber2.service.NewFilmData;
import in.javarush.sobaleva.hiber2.service.FilmForRentalService;
import org.hibernate.Session;

public class FilmDao extends DaoImpl<Film, Short> {
    public FilmDao() {
        super(Film.class);
    }

    public Film addNewFilmForRent(NewFilmData filmData) {
        Film film = null;
        try (Session session = getSession()) {
            film = FilmForRentalService.addNewFilmForRent(session, this, filmData);
        } catch (Exception e) {
            handleTransactionException(e);
        }
        return film;
    }
}