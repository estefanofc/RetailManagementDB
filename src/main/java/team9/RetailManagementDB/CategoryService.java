package team9.RetailManagementDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoryService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> findAll() {
        System.out.println("HHHHRHRHRHRhrh");
        return jdbcTemplate.query("SELECT CategoryName, SubcategoryName FROM Category",
                (rs, rowNum) -> new Category(rs.getString("categoryName"), rs.getString("subcategoryName")));
    }

    public void update(Category category) {
        jdbcTemplate.update("UPDATE Category SET subCategoryName=?, description=? WHERE categoryName=?",
                category.getSubcategoryName(), category.getCategoryName());
    }
}
