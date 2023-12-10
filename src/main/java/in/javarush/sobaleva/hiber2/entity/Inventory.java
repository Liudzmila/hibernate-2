package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "movie")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Short inventoryId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY)
    private List<Rental> rentals;

    public Inventory() {
    }

    public Inventory(Short inventoryId, Film film, Store store, LocalDateTime lastUpdate, List<Rental> rentals) {
        this.inventoryId = inventoryId;
        this.film = film;
        this.store = store;
        this.lastUpdate = lastUpdate;
        this.rentals = rentals;
    }

    public Short getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Short inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryId, inventory.inventoryId) && Objects.equals(film, inventory.film) && Objects.equals(store, inventory.store) && Objects.equals(lastUpdate, inventory.lastUpdate) && Objects.equals(rentals, inventory.rentals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, film, store, lastUpdate, rentals);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", film=" + film +
                ", store=" + store +
                ", lastUpdate=" + lastUpdate +
                ", rentals=" + rentals +
                '}';
    }
}