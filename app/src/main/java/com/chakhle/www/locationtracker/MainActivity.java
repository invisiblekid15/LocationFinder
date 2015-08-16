package com.chakhle.www.locationtracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; //in meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    protected final Button resetPhone = (Button) findViewById(R.id.numberReset);
    protected final Button resetAdd = (Button) findViewById(R.id.addressReset);
    protected final Button resetName = (Button) findViewById(R.id.nameReset);
    protected final Text Phone = (Text) findViewById(R.id.vendorPhone);
    protected final Text Add = (Text) findViewById(R.id.vendorAddress);
    protected final Text Name = (Text) findViewById(R.id.vendorName);
    static int vendorNumber = 0;

    protected LocationManager locationManager;
    protected Button retrieveLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveLocationButton = (Button) findViewById(R.id.button);
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
                Add.setTextContent("");
            }
        });

        resetName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Name.setTextContent("");
            }
        });

        resetPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phone.setTextContent("");
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
        if(location != null)
        {
            try {
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput("vendor.txt", MODE_APPEND));
                String phone = Phone.getWholeText().toString();
                String name = Name.getWholeText().toString();
                String addr = Add.getWholeText().toString();

                String message = String.format("Saving Current Location \\n Longitude: %1$s \\n Latitude: %2$s",
                        location.getLongitude(), location.getLatitude());
                vendorNumber += 1;
                String adtofile = vendorNumber + ". " + name + "\n" + addr + "\n" + phone + "\n";
                out.write(adtofile);
                out.close();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
            catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
            }
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
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
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


