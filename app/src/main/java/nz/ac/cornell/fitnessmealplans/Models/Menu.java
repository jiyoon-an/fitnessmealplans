package nz.ac.cornell.fitnessmealplans.Models;

/**
 * It is a class of Menu
 * It includes the data of menu.
 * This can indicate the menu and personal menu
 *
 * @author Jiyoon An
 * @date 03/06/16
 *
 * @param menuId is the id of menu
 * @param menuName is the name of the menu
 * @param amountis the amount of the menu
 * @param caloreis is the calorie of the menu
 * @param codeId is the preference type of the menu. It can includes preference more than one
 * @param categoryid is the category type of the menu. It can includes category more than one
 * @param userId is the userId, this will be called only when it's for personal menu
 */
public class Menu {
    private String menuId;
    private String menuName;
    private String amount;
    private double calories;
    private String codeId;
    private String categoryId;
    private String userId;

    public Menu(String menuName, double calories) {
        this.menuName = menuName;
        this.calories = calories;
    }

    public Menu(String menuId, String menuName, String amount, double calories, String codeId, String categoryId, String userId) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.amount = amount;
        this.calories = calories;
        this.codeId = codeId;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
