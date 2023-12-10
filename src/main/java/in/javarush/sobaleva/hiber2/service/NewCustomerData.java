package in.javarush.sobaleva.hiber2.service;

import java.time.LocalDateTime;

public class NewCustomerData {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Byte storeId;
    private final Boolean active;
    private final LocalDateTime createDate;
    private final LocalDateTime lastUpdate;

    public NewCustomerData(String firstName, String lastName, String email, Byte storeId, Boolean active, LocalDateTime createDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = LocalDateTime.now();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}