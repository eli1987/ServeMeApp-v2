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

public class SecurityActivity extends Activity {
    private String department = "Security";
    private ListView mListView ;
    private Context context;
    private BroadcastReceiver receiver;
    private String roomNum;
    private String telNumber ="035433333";
    private ProgressDialog progress ;
    private int [] drawable = {R.drawable.safe,R.drawable.bellboy,R.drawable.opendor,R.drawable.firstaid,R.drawable.calll};
    String security[];
    String securityDesc[] = {"Open Safe", "BellBoy", "Open Door", "First Aid", "Call"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_security);

        security = new String[] {getResources().getString(R.string.open_safe_str),getResources().getString(R.string.bellboy_str),getResources().getString(R.string.open_door_str),getResources().getString(R.string.first_aid_str),getResources().getString(R.string.call_str)};


        mListView = (ListView) findViewById(R.id.lv_housekeeping);
        final CutomAdapter2 adapter = new CutomAdapter2();
        mListView.setAdapter(adapter);
        context=this;
        roomNum = getIntent().getStringExtra("roomNum");
        progress= new ProgressDialog(SecurityActivity.this);

    }

    class CutomAdapter2 extends BaseAdapter
    {


        @Override
        public int getCount() {
            return drawable.length;
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
            iv.setImageResource(drawable[i]);
            textview.setText(security[i]);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    switch(index)
                    {
                        case 4:
                            // startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("035433333")));
                            CallService.callPhoneNumber(SecurityActivity.this,telNumber);

                            break;

                        default:
                            BackgroundWorker bg = new BackgroundWorker(SecurityActivity.this);
                            bg.execute("insertNewRequest", roomNum, department, securityDesc[index], "");
                            progress.setMessage(getResources().getString(R.string.Delivring_request_str));
                            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progress.setIndeterminate(false);
                            progress.setCancelable(false);
                            progress.setCanceledOnTouchOutside(false);
                            progress.setProgress(0);
                            progress.show();

                            registerReceiver(receiver = new BroadcastReceiver() {
                                @Override
                                public void onReceive(Context context, Intent intent) {
                                    String result = (String) intent.getExtras().getString("result");
                                    progress.setProgress(100);
                                    progress.dismiss();
                                    //alertDialog.show();
                                    if (result.equals("New requests accepted successfully")) {
                                        Toast.makeText(SecurityActivity.this, getResources().getString(R.string.New_request_accepted_successfully_str), Toast.LENGTH_SHORT).show();

                                        unregisterReceiver(receiver);

                                    } else {
                                        Toast.makeText(SecurityActivity.this,getResources().getString(R.string.Connection_error_try_again_later_str), Toast.LENGTH_SHORT).show();
                                        unregisterReceiver(receiver);
                                    }


                                }

                            }, new IntentFilter("resultIntent4"));


                            break;

                    }
                }
            });

            return view;
        }



    }

}
