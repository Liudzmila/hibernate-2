package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_actor")
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Or specify the appropriate cascade type
    @JoinColumn(name = "actor_id", insertable = false, updatable = false)
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Or specify the appropriate cascade type
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public FilmActor() {
    }

    public FilmActor(FilmActorId id, Actor actor, Film film, LocalDateTime lastUpdate) {
        this.id = id;
        this.actor = actor;
        this.film = film;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActor filmActor = (FilmActor) o;
        return Objects.equals(id, filmActor.id) && Objects.equals(actor, filmActor.actor) && Objects.equals(film, filmActor.film) && Objects.equals(lastUpdate, filmActor.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actor, film, lastUpdate);
    }

    @Override
    public String toString() {
        return "FilmActor{" +
                "id=" + id +
                ", actor=" + actor +
                ", film=" + film +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}