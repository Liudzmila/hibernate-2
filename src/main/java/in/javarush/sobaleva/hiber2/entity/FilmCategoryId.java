package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmCategoryId implements Serializable {

    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "category_id")
    private Short categoryId;

    public FilmCategoryId() {
    }

    public FilmCategoryId(Short filmId, Short categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    public Short getFilmId() {
        return filmId;
    }

    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryId that = (FilmCategoryId) o;
        return Objects.equals(filmId, that.filmId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }

    @Override
    public String toString() {
        return "FilmCategoryId{" +
                "filmId=" + filmId +
                ", categoryId=" + categoryId +
                '}';
    }
}