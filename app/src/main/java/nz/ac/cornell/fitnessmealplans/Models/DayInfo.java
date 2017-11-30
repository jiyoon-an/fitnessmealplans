package nz.ac.cornell.fitnessmealplans.Models;

/**
 * Created by HJS on 2016-05-08.
 */
public class DayInfo {

    private String date;
    private boolean bMonth;
    private boolean havingPlan;
    private boolean upDown;

    public String getDate() { return date; }
    public boolean isbMonth() {
        return bMonth;
    }
    public boolean isHavingPlan() { return havingPlan; }
    public boolean isUpDown() { return upDown; }
    public void setDate(String date) {
        this.date = date;
    }
    public void setbMonth(boolean bMonth) {
        this.bMonth = bMonth;
    }
    public void setHavingPlan(boolean havingPlan) { this.havingPlan = havingPlan; }
    public void setUpDown(boolean upDown) {this.upDown = upDown;}
}