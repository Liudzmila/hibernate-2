package in.javarush.sobaleva.hiber2.service;

import in.javarush.sobaleva.hiber2.dao.CustomerDao;
import in.javarush.sobaleva.hiber2.entity.*;
import org.hibernate.Session;

public class CustomerAddressService {
    private static final String GREEN = "\u001B[32m";
    private static final String RESET_GREEN = "\u001B[0m";

    public static Customer createCustomerWithAddress(Session session, CustomerDao customerDao, NewCustomerData customerData, NewAddressData addressData) {
        Customer customer = null;
        try {
            session.beginTransaction();

            Address address = new Address();
            address.setAddress(addressData.getAddress());
            address.setAddress2(addressData.getAddress2());
            address.setDistrict(addressData.getDistrict());
            address.setCity(session.get(City.class, addressData.getCityId()));
            address.setPostalCode(addressData.getPostalCode());
            address.setPhone(addressData.getPhone());
            address.setLastUpdate(addressData.getLastUpdate());

            customer = new Customer();
            customer.setStore(session.get(Store.class, customerData.getStoreId()));
            customer.setFirstName(customerData.getFirstName());
            customer.setLastName(customerData.getLastName());
            customer.setEmail(customerData.getEmail());
            customer.setActive(customerData.isActive());
            customer.setCreateDate(customerData.getCreateDate());
            customer.setLastUpdate(customerData.getLastUpdate());

            customer.setAddress(address);

            session.persist(address);
            session.persist(customer);
            session.flush();

            session.getTransaction().commit();

            printCustomerInformation(customer, customerData, addressData);
        } catch (Exception e) {
            customerDao.handleTransactionException(e);
        }
        return customer;
    }

    private static void printCustomerInformation(Customer customer, NewCustomerData customerData, NewAddressData addressData) {
        System.out.println(GREEN + "Создан новый клиент ID " + customer.getCustomerId() + "  магазина ID " + customerData.getStoreId() +
                "\nИмя: " + customerData.getFirstName() +
                "\nФамилия: " + customerData.getLastName() +
                "\nEmail: " + customerData.getEmail() +
                "\nАдрес: " + addressData.getAddress() +
                "\nАдрес 2: " + addressData.getAddress2() +
                "\nРайон: " + addressData.getDistrict() +
                "\nГород: " + customer.getAddress().getCity().getCityName() +
                "\nПочтовый индекс: " + addressData.getPostalCode() +
                "\nТелефон: " + addressData.getPhone() +
                RESET_GREEN);
        System.out.println("Данные внесены в базу данных");
    }
}