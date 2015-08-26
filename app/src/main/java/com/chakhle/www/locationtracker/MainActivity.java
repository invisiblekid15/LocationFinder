package com.chakhle.www.locationtracker;

<<<<<<< HEAD
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
=======
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
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
=======
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
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
    static int vendorNumber = 0;

    protected LocationManager locationManager;
    protected Button retrieveLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
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


=======
        retrieveLocationButton = (Button) findViewById(R.id.button);
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
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
<<<<<<< HEAD
                Add.setText("");
=======
                Add.setTextContent("");
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
            }
        });

        resetName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Name.setText("");
=======
                Name.setTextContent("");
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
            }
        });

        resetPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
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
=======
                Phone.setTextContent("");
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
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
<<<<<<< HEAD
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
=======
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
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
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
<<<<<<< HEAD
            /*String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();*/
=======
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
>>>>>>> 2035e49211335ad530b00e43fed4a9b6f5c7fa92
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


