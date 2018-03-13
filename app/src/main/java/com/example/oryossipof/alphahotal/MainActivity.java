package com.example.oryossipof.alphahotal;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends Activity {
    private String[] descriptions ;
    private int[] drawables = {R.drawable.reception, R.drawable.housekeeping,  R.drawable.securityagent ,R.drawable.information, R.drawable.roomservice, R.drawable.main, R.drawable.feedback2};
    private Class[] activities  ={ReceptionActivity.class, HotelHousekeepingActivity.class, SecurityActivity.class,InformationActivity.class, RoomServiceActivity.class, MaintenanceActivity.class, FeedbackMenuActivity.class};
    private ArrayList<SelectOption> numbers = new ArrayList<>();

    private FeatureCoverFlow coverFlow;
    private CustomAdapter customAdapter;
    private TextSwitcher textSwitcher;
    private  String roomNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        descriptions = new String[]{getResources().getString(R.string.Reception_str), getResources().getString(R.string.Housekeeping_str), getResources().getString(R.string.Security_str), getResources().getString(R.string.Information_str), getResources().getString(R.string.Room_Service_str), getResources().getString(R.string.Maintenance_str), getResources().getString(R.string.Feedback_str)};
        initData();

      roomNum = getIntent().getStringExtra("roomNum");


        textSwitcher = (TextSwitcher) findViewById(R.id.title);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                TextView text = (TextView)inflater.inflate(R.layout.layout_title , null);
                return  text;
            }
        });


        //Setting Animations..
        Animation in = AnimationUtils.loadAnimation(this , R.anim.slide_to_top);
        Animation out = AnimationUtils.loadAnimation(this , R.anim.slide_out_bottom);

        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);


        //Setting adapter for coverflow..
        customAdapter = new CustomAdapter(numbers , this, roomNum);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverFlow);
        coverFlow.setAdapter(customAdapter);


        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                final int pos = position;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textSwitcher.setText(numbers.get(pos).getDescription().toString());
                    }
                });

            }

            @Override
            public void onScrolling() {

            }
        });
    }



    private void initData() {

        for(int i = 0 ; i < descriptions.length ; i++){
            numbers.add(new SelectOption(descriptions[i], drawables[i], activities[i]));

        }
    }
}
