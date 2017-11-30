package nz.ac.cornell.fitnessmealplans.Dairy;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;

/**
 * This activity includes AddMenuActivity, ChooseMealActivity and MenuListActivity.
 * This operate as a group of activities
 *
 * @autor Jiyoon An
 * @date 03/06/2016
 *
 * @param userID user's id which this application is carrying while the system is operating
 * @param todayDate today's date
 * @param selectedDate the date which this application is carrying while the system is operating
 * @param group the object of DairyActivity
 * @param history the array of view
 * @param flag the flag which check breakfast, lunch or dinner
 * @param mIdList
 *
 */
public class DairyActivity extends ActivityGroup {

    private String userID = null;
    private String todayDate = null;
    private String selectedDate = null;

    public static DairyActivity group;
    private ArrayList<View> history;
    public static String flag;
    private ArrayList<String> mIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = this.getIntent();
        setUserID(intent.getExtras().getString("userID"));
        setTodayDate(intent.getExtras().getString("todayDate"));
        setSelectedDate(getParent().getIntent().getStringExtra("selectedDate"));

        mIdList = new ArrayList<String>();                              //Whenever the tab on Dairy is calles, mIdList is definded newly
        Intent chIntent = new Intent(this,MenuListActivity.class);
        chIntent.putExtra("userID", getUserID());
        chIntent.putExtra("selectedDate", getSelectedDate());
        startChildActivity("MenuListActivity", chIntent);

        getParent().getIntent().putExtra("selectedDate", getTodayDate());
    }

    @Override
    public void finishFromChild(Activity child) {
        LocalActivityManager manager = getLocalActivityManager();
        int index = mIdList.size()-1;

        if (index < 1) {
            finish();
            return;
        }

        manager.destroyActivity(mIdList.get(index), true);
        mIdList.remove(index);
        index--;
        String lastId = mIdList.get(index);
        Intent lastIntent = manager.getActivity(lastId).getIntent();
        Window newWindow = manager.startActivity(lastId, lastIntent);
        setContentView(newWindow.getDecorView());
    }

    /**
     * When new activity in this group is started, system use this method instead of startActivity
     * @param Id
     * @param intent
     */
    public void startChildActivity(String Id, Intent intent) {
        Window window = getLocalActivityManager().startActivity(Id,intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        if (window != null) {
            mIdList.add(Id);
            setContentView(window.getDecorView());
        }
    }

    /**
     * This method is only used in this activitygroup
     */
    @Override
    public void onBackPressed () {
        int length = mIdList.size();

        if ( length > 1) {
            Activity current = getLocalActivityManager().getActivity(mIdList.get(length-1));
            current.finish();
        }
    }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getTodayDate() { return todayDate; }
    public void setTodayDate(String todayDate) { this.todayDate = todayDate; }

    public String getSelectedDate() { return selectedDate; }
    public void setSelectedDate(String selectedDate) { this.selectedDate = selectedDate; }

}
