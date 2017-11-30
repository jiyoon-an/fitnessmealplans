package nz.ac.cornell.fitnessmealplans.Dairy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import nz.ac.cornell.fitnessmealplans.DB.DaoCategory;
import nz.ac.cornell.fitnessmealplans.DB.DaoPreference;
import nz.ac.cornell.fitnessmealplans.Models.Category;
import nz.ac.cornell.fitnessmealplans.Models.Menu;
import nz.ac.cornell.fitnessmealplans.Models.Preference;
import nz.ac.cornell.fitnessmealplans.R;

/**
 * This is an activity which shows options to enter about personal menu.
 * In this activity, users can customize their own special menu.
 *
 * @author Jiyoon An
 * @date 03/06/2016
 *
 * @param userID user's id which this application is carrying while the system is operating
 * @param selectedDate the date which this application is carrying while the system is operating
 * @param menuId menu's own ID.
 * @param tvName, tvCalory, tvAmount are the values which are connected with TextVeiw in xml file
 * @param btnAddMyMenu is the value which is connected with Button in xml file.
 * @param spCategory, spType are the values which are connected with spinner in xml file.
 * @param categoryList is the list of menu category. It is used for category spinner
 * @param codeList is the list of the user's preference. It is used for Preference spinner.
 * @param category code are the values which are selected by user
 * @param categoryId, codeId are the values which is the id of the category and preference
 * @param menu is the object of Menu calss.
 * @param flag is the value which checks the mode is add or edit.
 */

public class AddMenuActivity extends AppCompatActivity {

    private String userID = null;
    private String selectedDate = null;
    private String menuId = null;
    private EditText tvName, tvCalory, tvAmount;
    private Button btnAddMyMenu;
    private Spinner spCategory, spType;
    private ArrayList<Category> categoryList;
    private ArrayList<Preference> codeList;
    private String[] category, code;
    private String categoryId, codeId;
    private Menu menu;
    private String flag = "add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        Intent intent = this.getIntent();
        setUserID(intent.getExtras().getString("userID"));
        setSelectedDate(intent.getExtras().getString("selectedDate"));
        menuId = intent.getExtras().getString("menuId");

        tvName = (EditText) findViewById(R.id.tvName);
        tvAmount = (EditText) findViewById(R.id.tvAmount);
        spType = (Spinner) findViewById(R.id.spType);
        spCategory = (Spinner) findViewById(R.id.spCategory);
        tvCalory = (EditText) findViewById(R.id.tvCalory);
        btnAddMyMenu = (Button) findViewById(R.id.btnAddMyMenu);

        getSpinners();                                                  //Bring datas about category and preference from db
        makeSpinnerOption();                                            //Define the array from datas which is from db.

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCategory.setAdapter(adapter);                                 //attach the adapter for spinner

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, code);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spType.setAdapter(adapter);                                     //attach the adapter for spinner

        if(!menuId.equals("")) {                                        //when user choose the edit mode from choose activity
            setTitle("Edit My Menu");
            flag = "update";
            loadData();                                                 //bring the data about selected personal menu from db
            tvName.setText(menu.getMenuName());
            tvAmount.setText(menu.getAmount());
            tvCalory.setText(String.valueOf(menu.getCalories()));
            spType.setSelection(Integer.parseInt(menu.getCodeId())-1);
            spCategory.setSelection(Integer.parseInt(menu.getCategoryId())-1);
        } else {
            setTitle("Add My Menu");
        }

        switch(flag) {                                              //according the flag, system shows the add or edit mode activity.
            case "add" :
                btnAddMyMenu.setText("Add My Menu");
                break;
            case "update" :
                btnAddMyMenu.setText("Edit My Menu");
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DairyActivity parent = ((DairyActivity) getParent());
        parent.onBackPressed();
    }

    /**
     * When user click the button, this method will be execute.
     */
    public void onAddMyMenuClicked(View v) {
        if(tvName.getText().equals("") || tvCalory.getText().equals("")) {
            Toast.makeText(this, "Please set your menu", Toast.LENGTH_SHORT).show();
        } else {
            SaveDataThread savedata = new SaveDataThread();
            savedata.execute();
        }
    }

    /**
     * In this method, the information about menu user entered will be added or revised to db
     */
    private void saveData() {
        DaoCategory databaseAccess = DaoCategory.getInstance(this);
        databaseAccess.open();
        if(flag.equals("add")) {
            String menuID = databaseAccess.getPersonalMenuId();
            menuID = makeNewId(menuID);
            databaseAccess.setPersonalMenu(menuID, tvName.getText().toString(), tvAmount.getText().toString(),
                    Double.parseDouble(tvCalory.getText().toString()), codeId, categoryId, getUserID());
        } else if(flag.equals("update")) {
            databaseAccess.updatePersonalMenu(menuId, tvName.getText().toString(), tvAmount.getText().toString(),
                    Double.parseDouble(tvCalory.getText().toString()), codeId, categoryId, getUserID());
        }
        databaseAccess.close();
    }

    /**
     * This method calls 2 method for getting the data from db
     */
    private void getSpinners() {
        getCategorySpinner();
        getPreferenceSpinner();
    }

    /**
     * In this method, the data about category will be gathering from db.
     */
    private void getCategorySpinner() {
        DaoCategory databaseAccess = DaoCategory.getInstance(this);
        databaseAccess.open();
        categoryList = databaseAccess.getSimpleCategory();
        databaseAccess.close();
    }

    /**
     * In this method, the data about preference will be gatherinf from db.
     */
    private void getPreferenceSpinner() {
        DaoPreference databaseAccess = DaoPreference.getInstance(this);
        databaseAccess.open();
        codeList = databaseAccess.getPreference();
        databaseAccess.close();
    }

    /**
     * In this method, category and preference data will be convert to array to make spinner.
     */
    private void makeSpinnerOption() {
        category = new String[categoryList.size()];
        for(int i=0; i<categoryList.size(); i++) {
            category[i] = categoryList.get(i).getCategoryName();
        }

        code = new String[codeList.size()];
        for(int i=0; i<codeList.size(); i++) {
            code[i] = codeList.get(i).getCodeName();
        }
    }

    private void getCategoryId() {
        categoryId = categoryList.get(this.spCategory.getSelectedItemPosition()).getCategoryId();
    }

    private void getCodeId() {
        codeId = codeList.get(this.spType.getSelectedItemPosition()).getCodeId();
    }

    /**
     * In this method, the menuId is produced automatically when user want to add new personal menu.
     * @param menuID
     * @return
     */
    private String makeNewId(String menuID) {
        if(menuID != null) {
            menuID = menuID.substring(1);
            int index = Integer.parseInt(menuID);
            index++;
            menuID = "p" + String.valueOf(index);
        } else {
            menuID = "p1";
        }
        return menuID;
    }

    /**
     * In this method, the information about the personal menu will be called which user wants to edit
     */
    private void loadData() {
        DaoCategory databaseAccess = DaoCategory.getInstance(this);
        databaseAccess.open();
        menu = databaseAccess.getChosenPersonalMenu(menuId);
        databaseAccess.close();
    }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getSelectedDate() { return selectedDate; }
    public void setSelectedDate(String selectedDate) { this.selectedDate = selectedDate; }

    /**
     * This is a Thread class while saving the personal menu data to db.
     */
    class SaveDataThread extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(getParent(), "Fitness Meal Plan", "Saving Your Menu");
        }

        @Override
        protected Void doInBackground(Void... params) {
            getCategoryId();
            getCodeId();
            saveData();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            DairyActivity parentActivity = ((DairyActivity) getParent());
            parentActivity.onBackPressed();
        }
    }
}
