package android.code.hjs.fitnessmealplans;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by HJS on 2016-05-01.
 */
public class CalendarActivity extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener
{
    public static int SUNDAY        = 1;
    public static int MONDAY        = 2;
    public static int TUESDAY       = 3;
    public static int WEDNSESDAY    = 4;
    public static int THURSDAY      = 5;
    public static int FRIDAY        = 6;
    public static int SATURDAY      = 7;

    private TextView tvCalendarTitle;
    private GridView gvCalandarView;

    private ArrayList<DayInfo> mDayList;
    private CalendarAdapter mCalendarAdapter;

    Calendar mLastMonthCalendar;
    Calendar mThisMonthCalendar;
    Calendar mNextMonthCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_calendar, container, false);

        //previous, next Button
        Button btnPrevious = (Button)v.findViewById(R.id.btnPrevious);
        Button btnNext = (Button)v.findViewById(R.id.btnNext);

        tvCalendarTitle = (TextView)v.findViewById(R.id.tvCalendarTitle);
        gvCalandarView = (GridView)v.findViewById(R.id.gvCalandarView);

        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        gvCalandarView.setOnItemClickListener(this);

        mDayList = new ArrayList<DayInfo>();

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //Create this month calendar instance
        mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
    }

    /**
     * Calendar Sets up
     *
     * @param calendar calendar entity what shown screen
     */
    private void getCalendar(Calendar calendar)
    {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;

        mDayList.clear();

        //get the day what is starting in this month
        //if starting day is on Sunday, changed index to 1( on sunday to on next sunday)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        Log.e("the last month last day", calendar.get(Calendar.DAY_OF_MONTH)+"");

        // get the last date of last month
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, 1);
        Log.e("the first day", calendar.get(Calendar.DAY_OF_MONTH)+"");

        if(dayOfMonth == SUNDAY)
        {
            dayOfMonth += 7;
        }

        lastMonthStartDay -= (dayOfMonth-1)-1;

        setTvCalendarTitle(mThisMonthCalendar.get(Calendar.MONTH)
                         , mThisMonthCalendar.get(Calendar.YEAR));
        DayInfo day;

        Log.e("DayOfMonth", dayOfMonth+"");

        for(int i=0; i<dayOfMonth-1; i++)
        {
            int date = lastMonthStartDay+i;
            day = new DayInfo();
            day.setDate(Integer.toString(date));
            day.setbMonth(false);

            mDayList.add(day);
        }
        for(int i=1; i <= thisMonthLastDay; i++)
        {
            day = new DayInfo();
            day.setDate(Integer.toString(i));
            day.setbMonth(true);

            mDayList.add(day);
        }
        for(int i=1; i<42-(thisMonthLastDay+dayOfMonth-1)+1; i++)
        {
            day = new DayInfo();
            day.setDate(Integer.toString(i));
            day.setbMonth(false);
            mDayList.add(day);
        }

        initCalendarAdapter();
    }

    /**
     * Return Last Calendar Object
     *
     * @param calendar
     * @return LastMonthCalendar
     */
    private Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);

        setTvCalendarTitle(mThisMonthCalendar.get(Calendar.MONTH)
                         , mThisMonthCalendar.get(Calendar.YEAR));

        return calendar;
    }

    /**
     * retun next month Calendar Object
     *
     * @param calendar
     * @return NextMonthCalendar
     */
    private Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);

        setTvCalendarTitle(mThisMonthCalendar.get(Calendar.MONTH)
                , mThisMonthCalendar.get(Calendar.YEAR));

        return calendar;
    }

    // when date click , event
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
    {
        //Toast.makeText(getApplicationContext(), "Selected : " + curData, Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(), "select " + position + " : " + arg3, Toast.LENGTH_LONG ).show();
    }

    //click previous, next month
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnPrevious:
                mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
            case R.id.btnNext:
                mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
        }
    }

    private void initCalendarAdapter()
    {
        mCalendarAdapter = new CalendarAdapter(getContext(), R.layout.day, mDayList);
        gvCalandarView.setAdapter(mCalendarAdapter);
    }

    //setting Calendar Tittle TextView
    public void setTvCalendarTitle(int month, int year)
    {
        // Calendar tittle setting
        String tmpTitle=null;

        switch (month + 1){
            case 1:
                tmpTitle ="Jan. "+ year;
                break;
            case 2:
                tmpTitle ="Feb. "+ year;
                break;
            case 3:
                tmpTitle ="Mar. "+ year;
                break;
            case 4:
                tmpTitle ="Apr. "+ year;
                break;
            case 5:
                tmpTitle ="May. "+ year;
                break;
            case 6:
                tmpTitle ="Jun. "+ year;
                break;
            case 7:
                tmpTitle ="Jul. "+ year;
                break;
            case 8:
                tmpTitle ="Aug. "+ year;
                break;
            case 9:
                tmpTitle ="Sep. "+ year;
                break;
            case 10:
                tmpTitle ="Oct. "+ year;
                break;
            case 11:
                tmpTitle ="Nov. "+ year;
                break;
            case 12:
                tmpTitle ="Dec. "+ year;
                break;
        }
        tvCalendarTitle.setText(tmpTitle);
    }
}
