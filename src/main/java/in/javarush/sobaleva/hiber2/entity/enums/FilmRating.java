package in.javarush.sobaleva.hiber2.entity.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FilmRating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private static final Map<String, FilmRating> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(FilmRating::getFilmRating, Function.identity()));

    private final String filmRating;

    FilmRating(final String filmRating) {
        this.filmRating = filmRating;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public static FilmRating fromString(final String filmRating) {
        return LOOKUP.get(filmRating);
    }
}