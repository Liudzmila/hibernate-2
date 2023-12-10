package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_category")
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Or specify the appropriate cascade type
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Or specify the appropriate cascade type
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public FilmCategory() {
    }

    public FilmCategory(FilmCategoryId id, Film film, Category category, LocalDateTime lastUpdate) {
        this.id = id;
        this.film = film;
        this.category = category;
        this.lastUpdate = lastUpdate;
    }

    public FilmCategoryId getId() {
        return id;
    }

    public void setId(FilmCategoryId id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategory that = (FilmCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(film, that.film) && Objects.equals(category, that.category) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, category, lastUpdate);
    }

    @Override
    public String toString() {
        return "FilmCategory{" +
                "id=" + id +
                ", film=" + film +
                ", category=" + category +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}