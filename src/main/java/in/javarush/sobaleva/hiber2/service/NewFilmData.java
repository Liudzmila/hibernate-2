package in.javarush.sobaleva.hiber2.service;

import in.javarush.sobaleva.hiber2.entity.enums.FilmRating;

import java.math.BigDecimal;
import java.util.Set;

public class NewFilmData {
    private final String filmTitle;
    private final String filmDescription;
    private final Short releaseYear;
    private final BigDecimal rentalRate;
    private final Short rentalDuration;
    private final BigDecimal replacementCost;
    private final Short languageId;

    private final Short originalLanguageId;
    private final Set<Short> actorsIds;
    private final Set<Byte> categoriesIds;
    private final Byte storeId;
    private final FilmRating rating;
    private final Short length;
    private final Set<String> specialFeatures;

    public NewFilmData(String filmTitle, String filmDescription, Short releaseYear, BigDecimal rentalRate, Short rentalDuration, BigDecimal replacementCost, Short languageId, Short originalLanguageId, Set<Short> actorsIds, Set<Byte> categoriesIds, Byte storeId, FilmRating rating, Short length, Set<String> specialFeatures) {
        this.filmTitle = filmTitle;
        this.filmDescription = filmDescription;
        this.releaseYear = releaseYear;
        this.rentalRate = rentalRate;
        this.rentalDuration = rentalDuration;
        this.replacementCost = replacementCost;
        this.languageId = languageId;
        this.originalLanguageId = originalLanguageId;
        this.actorsIds = actorsIds;
        this.categoriesIds = categoriesIds;
        this.storeId = storeId;
        this.rating = rating;
        this.length = length;
        this.specialFeatures = specialFeatures;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public Short getLanguageId() {
        return languageId;
    }

    public Short getOriginalLanguageId() {
        return originalLanguageId;
    }

    public Set<Short> getActorsIds() {
        return actorsIds;
    }

    public Set<Byte> getCategoriesIds() {
        return categoriesIds;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public FilmRating getRating() {
        return rating;
    }

    public Short getLength() {
        return length;
    }

    public Set<String> getSpecialFeatures() {
        return specialFeatures;
    }
}