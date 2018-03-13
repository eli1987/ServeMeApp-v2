package com.example.oryossipof.alphahotal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class questionnairesActivity extends AppCompatActivity {
    private Button sendBt;
    private RadioGroup radioGroup1,radioGroup2, radioGroup3,radioGroup4,radioGroup5,radioGroup6,radioGroup7,radioGroup8 ;
    private RadioButton adult,  male, vegeterian, vegan, married, children, pleasute, group;
    private EditText originText;
    private TextView err;
    private BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaires);

        sendBt = (Button) findViewById(R.id.sendbt);
        radioGroup1 = (RadioGroup) findViewById(R.id.group1);
        radioGroup2 = (RadioGroup) findViewById(R.id.group2);
        radioGroup3 = (RadioGroup) findViewById(R.id.group3);
        radioGroup4 = (RadioGroup) findViewById(R.id.group4);
        radioGroup5 = (RadioGroup) findViewById(R.id.group5);
        radioGroup6 = (RadioGroup) findViewById(R.id.group6);
        radioGroup7 = (RadioGroup) findViewById(R.id.group7);
        radioGroup8 = (RadioGroup) findViewById(R.id.group8);
        originText = (EditText) findViewById(R.id.originText);
        err = (TextView) findViewById(R.id.validationerr);

        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validate())
                {
                    err.setVisibility(View.VISIBLE);
                }
                else
                {
                    err.setVisibility(View.INVISIBLE);
                    String roomNum = getIntent().getStringExtra("roomNum");
                    String type = "questionnaires";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(questionnairesActivity.this);
                    String answers[] = getAnswers();
                    backgroundWorker.execute(type,answers[0],answers[1],answers[2],answers[3],answers[4],answers[5],answers[6],answers[7],answers[8],roomNum);

                    registerReceiver(receiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String result = (String)intent.getExtras().getString("result");


                            if(result.equals("Record updated successfully"))
                            {
                                Intent intent1 = new Intent(questionnairesActivity.this, MainActivity.class);
                                startActivity(intent1);
                                try {
                                    this.finalize();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                                unregisterReceiver(receiver);
                                finish();

                            }

                            else
                            {
                                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                alertDialog.setTitle("Questionnaires Result");
                                alertDialog.setMessage(result);
                                alertDialog.show();


                                unregisterReceiver(receiver);
                            }
                        }
                    }, new IntentFilter("resultIntent3"));

                }
            }
        });
    }

    public String[] getAnswers() {
        adult = (RadioButton) findViewById(R.id.over15bt);
        male = (RadioButton) findViewById(R.id.malebt);
        vegeterian = (RadioButton) findViewById(R.id.vegYesbt);
        vegan = (RadioButton) findViewById(R.id.veganYesbt);
        married = (RadioButton) findViewById(R.id.marriedYesbt);
        children = (RadioButton) findViewById(R.id.childrenYesbt);
        pleasute = (RadioButton) findViewById(R.id.pleasurebt);
        group = (RadioButton) findViewById(R.id.groupYesbt);



        String answers[] = new String[9];
        answers[0] = originText.getText().toString();
        if (adult.isChecked())
            answers[1] = "1";
        else
            answers[1] = "0";

        if (male.isChecked())
            answers[2] = "1";
        else
            answers[2] = "0";

        if (vegeterian.isChecked())
            answers[3] = "1";
        else
            answers[3] = "0";

        if (vegan.isChecked())
            answers[4] = "1";
        else
            answers[4] = "0";

        if (married.isChecked())

            answers[5] = "1";
        else
            answers[5] = "0";

        if (children.isChecked())
            answers[6] = "1";
        else
            answers[6] = "0";

        if (pleasute.isChecked())
            answers[7] = "1";
        else
            answers[7] = "0";
        if (group.isChecked())

            answers[8] = "1";
        else
            answers[8] = "0";




        return answers;


    }


    public boolean validate()
    {

        if (radioGroup1.getCheckedRadioButtonId() == -1 || radioGroup2.getCheckedRadioButtonId() == -1 || radioGroup3.getCheckedRadioButtonId() == -1 || radioGroup4.getCheckedRadioButtonId() == -1 || radioGroup5.getCheckedRadioButtonId() == -1 || radioGroup6.getCheckedRadioButtonId() == -1 || radioGroup7.getCheckedRadioButtonId() == -1 || radioGroup8.getCheckedRadioButtonId() == -1 || originText.getText().length() == 0)
        {
            return false;
        }

        return true;
    }


}
