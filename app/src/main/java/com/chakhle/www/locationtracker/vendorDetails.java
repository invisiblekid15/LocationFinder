package com.chakhle.www.locationtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class vendorDetails extends Activity{

    protected CheckBox isNotStationary;
    protected CheckBox isLocalitySame;
    protected CheckBox isSmartPhone;
    protected EditText smartPhoneName;
    protected Button save;
    private static boolean localitySameVisible;
    private static boolean smartPhonePresent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_details);

        isNotStationary = (CheckBox) findViewById(R.id.isStationary);
        isLocalitySame = (CheckBox) findViewById(R.id.localitySame);
        isSmartPhone = (CheckBox) findViewById(R.id.isSmartPhone);

        smartPhoneName = (EditText) findViewById(R.id.smartphoneName);

        save = (Button) findViewById(R.id.button2);

        isLocalitySame.setVisibility(View.INVISIBLE);
        smartPhoneName.setVisibility(View.INVISIBLE);
        localitySameVisible = false;
        smartPhonePresent = false;

        isNotStationary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!localitySameVisible) {
                    isLocalitySame.setVisibility(View.VISIBLE);
                    localitySameVisible = true;
                } else {
                    isLocalitySame.setVisibility(View.INVISIBLE);
                    localitySameVisible = false;
                }
            }
        });

        isSmartPhone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!smartPhonePresent)
                {
                    smartPhoneName.setVisibility(View.VISIBLE);
                    smartPhonePresent = true;
                }
                else
                {
                    smartPhoneName.setVisibility(View.INVISIBLE);
                    smartPhonePresent = false;
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();
            }
        });
    }

    private String getTrueFalse(CheckBox check) {
        if (check.isChecked())
            return "true";
        else
            return "false";
    }

    private void saveDetails()
    {
        writeToFile out = new writeToFile();
        String details = getTrueFalse(isNotStationary) + "," + getTrueFalse(isLocalitySame) + "," + getTrueFalse(isSmartPhone) + "," + smartPhoneName.getText().toString() + ",";
        out.write(details);
        Intent intent = new Intent(vendorDetails.this.getApplicationContext(), food.class);
        startActivity(intent);
        finish();
    }

}
