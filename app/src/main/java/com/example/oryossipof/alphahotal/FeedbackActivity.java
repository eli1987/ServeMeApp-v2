package com.example.oryossipof.alphahotal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;

public class FeedbackActivity extends Activity {

    public static final String defualt = "N/A";
    //HttpPost httppost;
    private  String roomNum1;
    private StringBuffer buffer;
    private  String s;
    // HttpResponse response;
    //   HttpClient httpclient;
    //List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    private  RatingBar  ratingBar;
    private String rating1="0";

    private RatingBar  ratingBar2;
    private  String rating2="0";

    private RatingBar  ratingBar3;
    private  String rating3="0";

    private  RatingBar  ratingBar4;
    private  String rating4="0";

    private RatingBar  ratingBar5;
    private String rating5="0";

    private RatingBar  ratingBar6;
    private String rating6="0";

    private   RatingBar  ratingBar7;
    private String rating7="0";

    private  RatingBar  ratingBar8;
    private String rating8="0";

    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);


        addListenerOnRatingBar();
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        // TODO Auto-generated method stub

        btnSubmit = (Button) findViewById(R.id.feedback_page_button);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                String roomNum = sharedPreferences.getString("roomNum", defualt);
                roomNum1=roomNum;
                //new sendToPhp().execute("http://serviemeapp.freeiz.com/HotelFeed.php");


            }

        });



    }

    private void addListenerOnRatingBar() {
        // TODO Auto-generated method stub

        ratingBar = (RatingBar) findViewById(R.id.Recep_ratingBar);
        ratingBar2 = (RatingBar) findViewById(R.id.check_in_ratingBar);
        ratingBar3 = (RatingBar) findViewById(R.id.check_out_ratingBar);
        ratingBar4 = (RatingBar) findViewById(R.id.clean_ratingBar);
        ratingBar5 = (RatingBar) findViewById(R.id.comfort_ratingBar);
        ratingBar6 = (RatingBar) findViewById(R.id.maintenance_ratingBar);
        ratingBar7 = (RatingBar) findViewById(R.id.quality_of_food_ratingBar);
        ratingBar8 = (RatingBar) findViewById(R.id.service_ratingBar);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating1=String.valueOf(ratingBar.getRating());

            }
        });

        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating2=String.valueOf(ratingBar2.getRating());

            }
        });

        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating3=String.valueOf(ratingBar3.getRating());

            }
        });

        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating4=String.valueOf(ratingBar4.getRating());

            }
        });

        ratingBar5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating5=String.valueOf(ratingBar5.getRating());

            }
        });

        ratingBar6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating6=String.valueOf(ratingBar6.getRating());

            }
        });

        ratingBar7.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating7=String.valueOf(ratingBar7.getRating());

            }
        });

        ratingBar8.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                rating8=String.valueOf(ratingBar.getRating());

            }
        });



    }

 /*   private class sendToPhp extends AsyncTask<String, Void, String> {
        // TODO Auto-generated method stub
        @Override
        protected String doInBackground(String... urls) {
            // TODO Auto-generated method stub
            try {
                Log.w("myApp", rating1);
                //  HttpClient httpclient=new DefaultHttpClient();
                //httppost= new HttpPost(urls[0]);
                nameValuePairs = new ArrayList<NameValuePair>(9);
                nameValuePairs.add(new BasicNameValuePair("roomNumber3",roomNum1.trim()));
                nameValuePairs.add(new BasicNameValuePair("r1",rating1.trim()));
                nameValuePairs.add(new BasicNameValuePair("r2",rating2.trim()));
                nameValuePairs.add(new BasicNameValuePair("r3",rating3.trim()));
                nameValuePairs.add(new BasicNameValuePair("r4",rating4.trim()));
                nameValuePairs.add(new BasicNameValuePair("r5",rating5.trim()));
                nameValuePairs.add(new BasicNameValuePair("r6",rating6.trim()));
                nameValuePairs.add(new BasicNameValuePair("r7",rating8.trim()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //response=httpclient.execute(httppost);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);

                if(response.equalsIgnoreCase("ok")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(HotelFeedback.this,"Thank you for rating !!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    startActivity(new Intent(HotelFeedback.this, Selection_Screen.class));
                }else{
                    Toast.makeText(HotelFeedback.this,"There was a problem sending the rating , please try again later .", Toast.LENGTH_SHORT).show();

                }


            }catch(Exception e){
                System.out.println("Exception : " + e.getMessage());
                Toast.makeText(HotelFeedback.this,e.toString(), Toast.LENGTH_SHORT).show();
            }

            return null;
        }



    }
}
*/
}