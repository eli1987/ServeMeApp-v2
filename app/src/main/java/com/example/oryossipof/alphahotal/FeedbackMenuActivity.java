package com.example.oryossipof.alphahotal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackMenuActivity extends Activity {
    public final String BOOKING_ADDRESS = "https://www.booking.com/hotel/il/grand-beach-tel-aviv.he.html?aid=318615;label=Hebrew_Israel_HE_IL_44751382046-b54ePFlPEwDySRepOPD0WwS219301755770%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap1t1%3Aneg;sid=622130f2c3321d95eb2cbd85b74ebd5c;dest_id=-781545;dest_type=city;dist=0;hapos=1;hpos=1;room1=A%2CA;sb_price_type=total;srepoch=1512832361;srfid=ad0dfa181b8c10841d3ad2954cf323bada140fd2X1;srpvid=df5e6af48488045c;type=total;ucfs=1&#tab-main";
    public final String TRIPADVISOR_ADDRESS = "https://www.tripadvisor.co.il/Hotel_Review-g293984-d308679-Reviews-Grand_Beach_Hotel-Tel_Aviv_Tel_Aviv_District.html";
    private int drawableNames[] = {R.drawable.workers,R.drawable.foodrat,R.drawable.booking,R.drawable.tripadv,R.drawable.feedback2};
    private String  feedback[];

    private Class[] connections = {MainActivity.class, MainActivity.class, MainActivity.class,MainActivity.class, FeedbackActivity.class};

    private ListView mListView ;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback_menu);
        feedback =new String[]{getResources().getString(R.string.Employees_Rating_str),getResources().getString(R.string.Food_Rating_str),"Booking.com","Tripadvisor.com",getResources().getString(R.string.Hotel_Feedback_str)};


        mListView = (ListView) findViewById(R.id.lv_housekeeping);
        final CutomAdapter2 adapter = new CutomAdapter2();
        mListView.setAdapter(adapter);
        context=this;


    }

    class CutomAdapter2 extends BaseAdapter
    {


        @Override
        public int getCount() {
            return drawableNames.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final int index = i;
            view = getLayoutInflater().inflate(R.layout.listview_layout,null);
            ImageView iv= (ImageView) view.findViewById(R.id.imageviewlayout);
            TextView textview = (TextView) view.findViewById(R.id.textviewLayout);
            iv.setImageResource(drawableNames[i]);
            textview.setText(feedback[i]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent  intent;
                    switch(index)
                    {

                        case 0:
                             Toast.makeText(FeedbackMenuActivity.this, "workers", Toast.LENGTH_SHORT).show();
                             break;

                        case 1:
                            Toast.makeText(FeedbackMenuActivity.this, "Food", Toast.LENGTH_SHORT).show();
                            break;

                        case 2:
                             intent = new Intent(Intent.ACTION_VIEW, Uri.parse(BOOKING_ADDRESS));
                             startActivity(intent);
                             break;

                        case 3:
                             intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TRIPADVISOR_ADDRESS));
                             startActivity(intent);
                             break;

                        case 4:
                            intent = new Intent(FeedbackMenuActivity.this, connections[index]);
                            startActivity(intent);
                            break;


                    }

                }
            });

            return view;
        }
    }
}
