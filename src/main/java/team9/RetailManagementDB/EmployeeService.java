package team9.RetailManagementDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM Employee",
                (rs, rowNum) -> new Employee(rs.getString("employeeID"), rs.getString("firstName"), rs.getString(
                        "lastName"), rs.getString("streeAddress"), rs.getString("city"), rs.getString("state"),
                        rs.getString("zip"), rs.getString("customerID"), rs.getDouble("salary")));
    }

    public void update(Employee employee) {
        jdbcTemplate.update(
                "UPDATE Employee SET firstName=?, lastName=?, salary=?, streetAddress=?, " +
                        "city=?, state=?, zip=?, storeID=? WHERE employeeID=?",
                employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getStreetAddress(),
                employee.getCity(), employee.getState(), employee.getZip(), employee.getStoreID(), employee.getEmployeeID());
    }

}
