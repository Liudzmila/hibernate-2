package in.javarush.sobaleva.hiber2.service;

import in.javarush.sobaleva.hiber2.dao.FilmDao;
import in.javarush.sobaleva.hiber2.entity.*;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmForRentalService {

    private static final String YELLOW = "\u001B[33m";
    private static final String RESET_YELLOW = "\u001B[0m";

    private static final String BLUE = "\u001B[34m";
    private static final String RESET_BLUE = "\u001B[0m";

    public static Film addNewFilmForRent(Session session, FilmDao filmDao, NewFilmData filmData) {
        Film newFilm = null;
        try {
            session.beginTransaction();
            newFilm = createFilmFromData(session, filmData);
            Inventory inventory = createInventoryForFilm(session, newFilm, filmData.getStoreId());

            session.persist(newFilm);
            session.persist(inventory);

            session.flush();
            session.getTransaction().commit();

            printFilmAndInventoryInformation(session, newFilm, filmData, inventory);
        } catch (Exception e) {
            filmDao.handleTransactionException(e);
        }
        return newFilm;
    }

    private static Film createFilmFromData(Session session, NewFilmData filmData) {
        Film newFilm = new Film();
        newFilm.setTitle(filmData.getFilmTitle());
        newFilm.setDescription(filmData.getFilmDescription());
        newFilm.setReleaseYear(filmData.getReleaseYear());
        newFilm.setRentalRate(filmData.getRentalRate());
        newFilm.setReplacementCost(filmData.getReplacementCost());
        newFilm.setLastUpdate(LocalDateTime.now());
        newFilm.setRentalDuration(filmData.getRentalDuration());
        newFilm.setRating(filmData.getRating());
        newFilm.setLength(filmData.getLength());

        Set<String> allowedSpecialFeatures = getFilteredSpecialFeatures(filmData.getSpecialFeatures());
        newFilm.setSpecialFeatures(allowedSpecialFeatures);

        Language language = session.load(Language.class, filmData.getLanguageId());
        newFilm.setLanguage(language);

        Set<Actor> actors = filmData.getActorsIds().stream()
                .map(actorId -> session.load(Actor.class, actorId))
                .collect(Collectors.toSet());
        newFilm.setActors(actors);

        Set<Category> categories = filmData.getCategoriesIds().stream()
                .map(categoryId -> session.load(Category.class, categoryId))
                .collect(Collectors.toSet());
        newFilm.setCategories(categories);

        FilmText filmText = new FilmText();
        filmText.setTitle(filmData.getFilmTitle());
        filmText.setDescription(filmData.getFilmDescription());

        newFilm.setFilmText(filmText);
        filmText.setFilm(newFilm);

        return newFilm;
    }

    private static Inventory createInventoryForFilm(Session session, Film film, Byte storeId) {
        Inventory inventory = new Inventory();
        inventory.setLastUpdate(LocalDateTime.now());
        inventory.setFilm(film);
        inventory.setStore(session.get(Store.class, storeId));
        film.addInventory(inventory);
        return inventory;
    }

    private static void printFilmAndInventoryInformation(Session session, Film newFilm, NewFilmData filmData, Inventory inventory) {
        System.out.println(YELLOW + "Создан новый фильм ID " + newFilm.getFilmId() + "  для аренды: " +
                "\nНазвание: " + filmData.getFilmTitle() +
                "\nОписание: " + filmData.getFilmDescription() +
                "\nГод выпуска: " + filmData.getReleaseYear() +
                "\nСтоимость аренды: " + filmData.getRentalRate() +
                "\nДлительность аренды: " + filmData.getRentalDuration() +
                "\nЗамена стоимости: " + filmData.getReplacementCost() +
                "\nЯзык: " + session.get(Language.class, filmData.getLanguageId()).getName() +
                "\nЯзык оригинала: " + session.get(Language.class, filmData.getOriginalLanguageId()).getName() +
                "\nРейтинг: " + filmData.getRating() +
                "\nДлительность: " + filmData.getLength() +
                "\nОсобенности: " + filmData.getSpecialFeatures() +
                RESET_YELLOW);
        System.out.println(BLUE + "Создан инвентарь с ID " + inventory.getInventoryId() + " в магазине ID " + filmData.getStoreId() + RESET_BLUE);
        System.out.println("Данные внесены в базу данных");
    }

    private static Set<String> getFilteredSpecialFeatures(Set<String> specialFeatures) {
        return specialFeatures.stream()
                .map(String::toLowerCase)
                .filter(s -> s.equals("trailers") || s.equals("commentaries") || s.equals("deleted scenes") || s.equals("behind the scenes"))
                .map(s -> {
                    switch (s) {
                        case "trailers":
                            return "Trailers";
                        case "commentaries":
                            return "Commentaries";
                        case "deleted scenes":
                            return "Deleted Scenes";
                        case "behind the scenes":
                            return "Behind the Scenes";
                        default:
                            return "";
                    }
                })
                .collect(Collectors.toSet());
    }
}