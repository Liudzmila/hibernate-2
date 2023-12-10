package in.javarush.sobaleva.hiber2.dao;

import in.javarush.sobaleva.hiber2.entity.Customer;
import in.javarush.sobaleva.hiber2.service.NewAddressData;
import in.javarush.sobaleva.hiber2.service.NewCustomerData;
import in.javarush.sobaleva.hiber2.exception.DataAccessException;
import org.hibernate.Session;
import in.javarush.sobaleva.hiber2.service.CustomerAddressService;

public class CustomerDao extends DaoImpl<Customer, Short> {
    public CustomerDao() {
        super(Customer.class);
    }

    public Customer createCustomerWithAddress(NewCustomerData customerData, NewAddressData addressData) throws DataAccessException {
        Customer customer = null;
        try (Session session = getSession()) {
            customer = CustomerAddressService.createCustomerWithAddress(session, this, customerData, addressData);
        } catch (Exception e) {
            handleTransactionException(e);
        }
        return  customer;
    }
}