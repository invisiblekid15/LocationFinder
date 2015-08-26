package com.chakhle.www.locationtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; //in meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    protected Button resetPhone;
    protected Button resetAdd;
    protected Button resetName;
    protected Button resetOTime;
    protected Button resetCTime;
    protected EditText Phone;
    protected EditText Add;
    protected EditText Name;
    protected EditText OTime;
    protected EditText CTime;
    static int vendorNumber = 0;

    protected LocationManager locationManager;
    protected Button retrieveLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetPhone = (Button) findViewById(R.id.numberReset);
        retrieveLocationButton = (Button) findViewById(R.id.button);
        resetAdd = (Button) findViewById(R.id.addressReset);
        resetName = (Button) findViewById(R.id.nameReset);
        resetCTime = (Button) findViewById(R.id.cTimeReset);
        resetOTime = (Button) findViewById(R.id.oTimeReset);

        Phone = (EditText) findViewById(R.id.vendorPhone);
        Add = (EditText) findViewById(R.id.vendorAddress);
        Name = (EditText) findViewById(R.id.vendorName);
        OTime = (EditText) findViewById(R.id.openTime);
        CTime = (EditText) findViewById(R.id.closeTime);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );

        resetAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Add.setText("");
            }
        });

        resetName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Name.setText("");
            }
        });

        resetPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phone.setText("");
            }
        });

        resetOTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OTime.setText("");
            }
        });

        resetCTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CTime.setText("");
            }
        });

        retrieveLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrentLocation();
            }
        });
    }

    protected void saveCurrentLocation()
    {
        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        if(location == null)
        {
            new AlertDialog.Builder(this).setTitle("Location not given by gps, Please try again.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
        else if(location != null)
        {
                String phone = Phone.getText().toString();
                String name = Name.getText().toString();
                String addr = Add.getText().toString();
                String openTime = OTime.getText().toString();
                String closeTime = CTime.getText().toString();
                String message = String.format("Saving Current Location \\n Longitude: %1$s \\n Latitude: %2$s",
                        location.getLongitude(), location.getLatitude());
                vendorNumber += 1;
                String adtofile = "\n" + name + "," + addr + "," + phone + "," + location.getLatitude() + "," + location.getLongitude() + "," + location.getAccuracy() + "," + openTime + "," + closeTime + ",";
                writeToFile out = new writeToFile();
                out.write(adtofile);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), vendorDetails.class);
                startActivity(intent);
                finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            /*String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();*/
        }
        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(MainActivity.this, "Provider status changed",
                    Toast.LENGTH_LONG).show();
        }
        public void onProviderDisabled(String s) {
            Toast.makeText(MainActivity.this,
                    "Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_LONG).show();
        }
        public void onProviderEnabled(String s) {
            Toast.makeText(MainActivity.this,
                    "Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_LONG).show();
        }
    }
}


