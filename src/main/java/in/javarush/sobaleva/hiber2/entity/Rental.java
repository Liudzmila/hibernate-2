package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rental", schema = "movie")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Short rentalId;

    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Rental() {
    }

    public Rental(Short rentalId, LocalDateTime rentalDate, Inventory inventory, Customer customer, LocalDateTime returnDate, Staff staff, LocalDateTime lastUpdate) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventory = inventory;
        this.customer = customer;
        this.returnDate = returnDate;
        this.staff = staff;
        this.lastUpdate = lastUpdate;
    }

    public Short getRentalId() {
        return rentalId;
    }

    public void setRentalId(Short rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
        Rental rental = (Rental) o;
        return Objects.equals(rentalId, rental.rentalId) && Objects.equals(rentalDate, rental.rentalDate) && Objects.equals(inventory, rental.inventory) && Objects.equals(customer, rental.customer) && Objects.equals(returnDate, rental.returnDate) && Objects.equals(staff, rental.staff) && Objects.equals(lastUpdate, rental.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, rentalDate, inventory, customer, returnDate, staff, lastUpdate);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", rentalDate=" + rentalDate +
                ", inventory=" + inventory +
                ", customer=" + customer +
                ", returnDate=" + returnDate +
                ", staff=" + staff +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}