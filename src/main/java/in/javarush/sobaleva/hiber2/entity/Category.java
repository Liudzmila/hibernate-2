package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category", schema = "movie")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte categoryId;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany(mappedBy = "categories")
    private Set<Film> films = new HashSet<>();

    public Category() {
    }

    public Category(Byte categoryId, String name, LocalDateTime lastUpdate, Set<Film> films) {
        this.categoryId = categoryId;
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.films = films;
    }

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        films.add(film);
        film.getCategories().add(this);
    }

    public void removeFilm(Film film) {
        films.remove(film);
        film.getCategories().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(name, category.name) && Objects.equals(lastUpdate, category.lastUpdate) && Objects.equals(films, category.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name, lastUpdate, films);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", films=" + films +
                '}';
    }
}