package in.javarush.sobaleva.hiber2.entity.converter;

import in.javarush.sobaleva.hiber2.entity.enums.FilmRating;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FilmRatingConverter implements AttributeConverter<FilmRating, String> {
    @Override
    public String convertToDatabaseColumn(final FilmRating attribute) {
        return (attribute != null) ? attribute.getFilmRating() : null;
    }

    @Override
    public FilmRating convertToEntityAttribute(final String dbData) {
        return FilmRating.fromString(dbData);
    }
}