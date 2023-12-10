package in.javarush.sobaleva.hiber2.entity;

import in.javarush.sobaleva.hiber2.entity.converter.FilmRatingConverter;
import jakarta.persistence.*;
import in.javarush.sobaleva.hiber2.entity.converter.SpecialFeaturesConverter;
import in.javarush.sobaleva.hiber2.entity.enums.FilmRating;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "film", schema = "movie")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Short releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "TINYINT UNSIGNED DEFAULT 3")
    private Short rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2, columnDefinition = "DECIMAL(4,2) DEFAULT 4.99")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2, columnDefinition = "DECIMAL(5,2) DEFAULT 19.99")
    private BigDecimal replacementCost;


    @Convert(converter = FilmRatingConverter.class)
    @Column(name = "rating", columnDefinition = "enum ('G', 'PG', 'PG-13', 'R', 'NC-17') default 'G' null")
    private FilmRating rating;
    @Convert(converter = SpecialFeaturesConverter.class)
    @Column(name = "special_features", columnDefinition = "set ('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes') null")
    private Set<String> specialFeatures;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL)
    private FilmText filmText;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private Set<Inventory> inventories = new HashSet<>();

    public Film() {
    }

    public Film(Short filmId, String title, String description, Short releaseYear, Language language, Language originalLanguage, Short rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost, FilmRating rating, Set<String> specialFeatures, LocalDateTime lastUpdate, FilmText filmText, Set<Category> categories, Set<Actor> actors) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.originalLanguage = originalLanguage;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
        this.filmText = filmText;
        this.categories = categories;
        this.actors = actors;
    }

    public Short getFilmId() {
        return filmId;
    }

    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public FilmRating getRating() {
        return rating;
    }

    public void setRating(FilmRating rating) {
        this.rating = rating;
    }

    public Set<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(Set<String> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public FilmText getFilmText() {
        return filmText;
    }

    public void setFilmText(FilmText filmText) {
        this.filmText = filmText;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    // Добавление актера к фильму
    public void addActor(Actor actor) {
        actors.add(actor);
        actor.getFilms().add(this);
    }

    // Удаление актера из фильма
    public void removeActor(Actor actor) {
        actors.remove(actor);
        actor.getFilms().remove(this);
    }

    // Добавление актера к фильму
    public void addCategory(Category category) {
        categories.add(category);
        category.getFilms().add(this);
    }

    // Удаление актера из фильма
    public void removeCategory(Category category) {
        categories.remove(category);
        category.getFilms().remove(this);
    }

    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
        inventory.setFilm(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(filmId, film.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", language=" + language +
                ", originalLanguage=" + originalLanguage +
                ", rentalDuration=" + rentalDuration +
                ", rentalRate=" + rentalRate +
                ", length=" + length +
                ", replacementCost=" + replacementCost +
                ", rating=" + rating +
                ", specialFeatures=" + specialFeatures +
                ", lastUpdate=" + lastUpdate +
                ", filmText=" + filmText +
                ", actors=" + actors +
                ", categories=" + categories +
                ", inventories=" + inventories +
                '}';
    }
}