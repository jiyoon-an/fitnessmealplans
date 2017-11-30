package nz.ac.cornell.fitnessmealplans.Models;

/**
 * It is a class of MealPlan.
 * It includes data of meal plan of the day
 *
 * @author Jiyoon An
 * @date 03/06/16
 *
 * @param planId is the Id of meal plan and it indicate the date
 * @param userId is the id of user who planned the menu
 * @param codeId is the code of user's preference
 * @param breakfast is the meal paln of breakfast
 * @param lunch is the meal plan of lunch
 * @param dinner is the meal plan of dinner
 * @param calories is the total calories of the meal plan
 */
public class MealPlan {

    private String planId;
    private String userId;
    private String codeId;
    private String breakfast;
    private String lunch;
    private String dinner;
    private double calories;

    public MealPlan(String planId, String userId, String codeId, String breakfast, String lunch, String dinner, double calories) {
        this.planId = planId;
        this.userId = userId;
        this.codeId = codeId;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.calories = calories;
    }

    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getBreakfast() {
        return this.breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return this.lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return this.dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public double getCalories() { return this.calories; }

    public void setCalories(double calories) { this.calories = calories; }
}
