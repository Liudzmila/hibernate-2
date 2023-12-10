package in.javarush.sobaleva.hiber2.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "country", schema = "movie")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short countryId;

    @Column(name = "country", nullable = false, length = 50)
    private String countryName;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Country() {
    }

    public Country(Short countryId, String countryName, LocalDateTime lastUpdate) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.lastUpdate = lastUpdate;
    }

    public Short getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        Country country = (Country) o;
        return Objects.equals(countryId, country.countryId) && Objects.equals(countryName, country.countryName) && Objects.equals(lastUpdate, country.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, countryName, lastUpdate);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}