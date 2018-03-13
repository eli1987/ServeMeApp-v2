package com.example.oryossipof.alphahotal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class HotelHousekeepingActivity extends Activity {

    private int drawableNames[] = {R.drawable.toilertries,R.drawable.towel2,R.drawable.papertoilet,R.drawable.babybed,R.drawable.bed,R.drawable.clean};
    private String  descriptionDes[] = {"Toiletries","Towel","Paper toilet","baby bed","bedding","Clean room"};
    private String  description[];
    private  String roomNum;
    private ProgressDialog progress ;
    private String department = "HouseKeeping";
    private ListView mListView ;
    private Context context;
    private  BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hotel_housekeeping);

        description = new String[] {getResources().getString(R.string.Toiletries_str),getResources().getString(R.string.Towel_str),getResources().getString(R.string.Paper_str),getResources().getString(R.string.baby_bed_str),getResources().getString(R.string.bedding_str),getResources().getString(R.string.Clean_room_str)};

        mListView = (ListView) findViewById(R.id.lv_housekeeping);
        final CutomAdapter2 adapter = new CutomAdapter2();
        mListView.setAdapter(adapter);
        context=this;
        roomNum = getIntent().getStringExtra("roomNum");
        progress= new ProgressDialog(HotelHousekeepingActivity.this);
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
            textview.setText(description[i]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    BackgroundWorker bg = new BackgroundWorker(HotelHousekeepingActivity.this);
                    bg.execute("insertNewRequest",roomNum,department,descriptionDes[index],"");
                    progress.setMessage(getResources().getString(R.string.Delivring_request_str));
                    progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progress.setIndeterminate(false);
                    progress.setCancelable(false);
                    progress.setCanceledOnTouchOutside(false);
                    progress.setProgress(0);
                    progress.show();

                    registerReceiver(receiver =new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String result = (String)intent.getExtras().getString("result");

                            progress.setProgress(100);
                            progress.dismiss();
                            //alertDialog.show();
                            if(result.equals("New requests accepted successfully")) {
                                Toast.makeText(HotelHousekeepingActivity.this, getResources().getString(R.string.New_request_accepted_successfully_str), Toast.LENGTH_SHORT).show();

                                unregisterReceiver(receiver);

                            }
                            else
                            {
                                Toast.makeText(HotelHousekeepingActivity.this, getResources().getString(R.string.Connection_error_try_again_later_str), Toast.LENGTH_SHORT).show();
                                unregisterReceiver(receiver);
                            }

                        }

                    }, new IntentFilter("resultIntent4"));
                }

            });

            return view;

        }
    }
}
