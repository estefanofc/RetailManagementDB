package team9.RetailManagementDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT CustomerID, FirstName, LastName, BillAddress, BillCity, BillState, BillZip," +
                        " ShipAddress, ShipCity, ShipState, ShipZip, Phone, Email FROM Customer",
                (rs, rowNum) -> new Customer(rs.getString("CustomerID"), rs.getString("FirstName"), rs.getString(
                        "LastName"), rs.getString("BillAddress"), rs.getString("BillCity"), rs.getString(
                        "BillState"), rs.getString("BillZip"), rs.getString("ShipAddress"), rs.getString(
                        "ShipCity"), rs.getString("ShipState"), rs.getString("ShipZip"), rs.getString("Phone"),
                        rs.getString("Email")));
    }

    public void update(Customer customer) {
        jdbcTemplate.update(
                "UPDATE Customer SET firstName=?, lastName=?, billAddress=?, billCity=?, billState=?, " +
                        "billZip=?, shipAddress=?, shipCity=?, shipState=?, shipZip=?, phone=?, email=? WHERE " +
                        "customerID=?",
                customer.getFirstName(), customer.getLastName(), customer.getBillAddress(), customer.getBillCity(),
                customer.getBillState(), customer.getBillZip(), customer.getShipAddress(),
                customer.getShipCity(), customer.getShipState(), customer.getShipZip(),
                customer.getPhone(), customer.getEmail(), customer.getCustomerID());
    }

}

