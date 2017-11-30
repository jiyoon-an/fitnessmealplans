package nz.ac.cornell.fitnessmealplans.Models;

import java.util.ArrayList;

/**
 * It is a class of Category.
 * It includes category's id and Name
 * Category also includes the list of menu which is in the low level
 *
 * @author Jiyoon An
 * @date 03/06/16
 */
public class Category {
    private String categoryId;
    private String categoryName;
    private ArrayList<Menu> menuList = new ArrayList<Menu>();

    public Category(String categoryId, String categoryName, ArrayList<Menu> menuList) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.menuList = menuList;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList() {
        this.menuList = menuList;
    }
}
