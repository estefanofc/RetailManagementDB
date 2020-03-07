package team9.RetailManagementDB;

public class Category {
    private String categoryName, subcategoryName;

    public Category(String categoryName, String subcategoryName) {
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

}
