package team9.RetailManagementDB;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
public class VaadinUI extends UI {
    @Autowired
    private CustomerService service;
    @Autowired
    private CategoryService categoryService;

    private Customer customer;
    private Binder<Customer> customerBinder = new Binder<>(Customer.class);

    private Category category;
    private Binder<Category> categoryBinder = new Binder<>(Category.class);

    private Grid<Customer> customerGrid = new Grid<>(Customer.class);
    private TextField customerID = new TextField("CustomerID");
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField billAddress = new TextField("Bill Address");
    private TextField billCity = new TextField("Bill City");
    private TextField billState = new TextField("Bill State");
    private TextField billZip = new TextField("Bill Zip");
    private TextField shipAddress = new TextField("Ship Address");
    private TextField shipCity = new TextField("Ship City");
    private TextField shipState = new TextField("Ship State");
    private TextField shipZip = new TextField("Ship Zip");
    private TextField phone = new TextField("Phone");
    private TextField email = new TextField("Email");
    private Button customerSave = new Button("Save", e -> saveCustomer());

    private Grid<Category> categoryGrid = new Grid<>(Category.class);
    private TextField categoryName = new TextField("Category Name");
    private TextField subcategoryName = new TextField("Subcategory Name");
    private Button categorySave = new Button("Save", e -> saveCategory());


    @Override
    protected void init(VaadinRequest request) {
        updateCategoryGrid();
        updateCustomerGrid();
        categoryGrid.setColumns("categoryName", "subcategoryName");
        categoryGrid.addSelectionListener(e -> updateCategoryForm());
        categoryBinder.bindInstanceFields(this);


        customerGrid.setColumns("customerID", "firstName", "lastName", "billAddress", "billCity", "billState", "billZip",
                "shipAddress", "shipCity", "shipState", "shipZip", "phone", "email");
        customerGrid.addSelectionListener(e -> updateCustomerForm());
        customerBinder.bindInstanceFields(this);

        VerticalLayout layout = new VerticalLayout(customerGrid, customerID, firstName, lastName, billAddress, billCity,
                billState, billZip, shipAddress, shipCity, shipState, shipZip, phone, email, customerSave,
                categoryGrid, categoryName, subcategoryName, categorySave);
        setContent(layout);
    }

    private void updateCustomerGrid() {
        List<Customer> customers = service.findAll();
        customerGrid.setItems(customers);
        setCustomerFormVisible(false);
    }

    private void updateCustomerForm() {
        if (customerGrid.asSingleSelect().isEmpty()) {
            setCustomerFormVisible(false);
        } else {
            customer = customerGrid.asSingleSelect().getValue();
            customerBinder.setBean(customer);
            setCustomerFormVisible(true);
        }
    }

    private void setCustomerFormVisible(boolean visible) {
        customerID.setVisible(visible);
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        billAddress.setVisible(visible);
        billCity.setVisible(visible);
        billState.setVisible(visible);
        billZip.setVisible(visible);
        shipAddress.setVisible(visible);
        shipCity.setVisible(visible);
        shipState.setVisible(visible);
        shipZip.setVisible(visible);
        phone.setVisible(visible);
        email.setVisible(visible);
        customerSave.setVisible(visible);
    }

    private void saveCustomer() {
        service.update(customer);
        updateCustomerGrid();
    }

    private void updateCategoryGrid() {
        List<Category> categories = categoryService.findAll();
        categoryGrid.setItems(categories);
        setCategoryFormVisible(false);
    }

    private void updateCategoryForm() {
        if (categoryGrid.asSingleSelect().isEmpty()) {
            setCategoryFormVisible(false);
        } else {
            category = categoryGrid.asSingleSelect().getValue();
            categoryBinder.setBean(category);
            setCategoryFormVisible(true);
        }
    }

    private void setCategoryFormVisible(boolean visible) {
        categoryName.setVisible(visible);
        subcategoryName.setVisible(visible);
        categorySave.setVisible(visible);
    }

    private void saveCategory() {
        categoryService.update(category);
        updateCategoryGrid();
    }
}