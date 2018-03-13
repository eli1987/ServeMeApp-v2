package com.example.oryossipof.alphahotal;

        import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ReceptionActivity extends Activity {
    private String telNumber ="035433333";
    private ProgressDialog progress ;
    private int [] drawableName = {R.drawable.call3,R.drawable.trip,R.drawable.tour,R.drawable.food,R.drawable.taxi,R.drawable.weakup};
    String receptionDesc[] = {"Call","Book a Trip","Recommend tours","Recommend restaurants","Get Taxi","Wake up call"};
    String reception[];
    private ListView mListView ;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reception);


        reception = new String[] {getResources().getString(R.string.call_str), getResources().getString(R.string.book_trip_str), getResources().getString(R.string.Recommend_tours_str), getResources().getString(R.string.Recommend_restaurants_str),getResources().getString(R.string.Get_Taxi_str),getResources().getString(R.string.Wake_up_call_str)};
        mListView = (ListView) findViewById(R.id.lv_housekeeping);
        final CutomAdapter2 adapter = new CutomAdapter2();
        mListView.setAdapter(adapter);
        context=this;

        progress= new ProgressDialog(ReceptionActivity.this);
    }

    class CutomAdapter2 extends BaseAdapter
    {


        @Override
        public int getCount() {
            return drawableName.length;
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
            iv.setImageResource(drawableName[i]);
            textview.setText(reception[i]);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    switch(index)
                    {
                        case 0:
                            // startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("035433333")));
                            CallService.callPhoneNumber(ReceptionActivity.this,telNumber);

                            break;

                        case 4:  //Get Taxi
                            intent = new Intent(ReceptionActivity.this, BookTaxiActivity.class);
                            startActivity(intent);
                            break;


                        default:



                            break;

                    }
                }
            });

            return view;
        }
    }
}
