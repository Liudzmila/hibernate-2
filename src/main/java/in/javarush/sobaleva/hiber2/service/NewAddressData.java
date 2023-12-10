package in.javarush.sobaleva.hiber2.service;

import java.time.LocalDateTime;

public class NewAddressData {
    private final String address;
    private final String address2;
    private final String district;
    private final Short cityId;
    private final String postalCode;
    private final String phone;
    private final LocalDateTime lastUpdate;

    public NewAddressData(String address, String address2, String district, short cityId, String postalCode, String phone, LocalDateTime lastUpdate) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getDistrict() {
        return district;
    }

    public Short getCityId() {
        return cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
