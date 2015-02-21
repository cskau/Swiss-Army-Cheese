package com.example.swissarmycheese;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class MainActivity extends Activity {

  LocationManager locationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

    locationManager.requestLocationUpdates(
        LocationManager.GPS_PROVIDER,
        0,
        0,
        new LocationListener() {
            @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override public void onProviderEnabled(String provider) {}
            @Override public void onProviderDisabled(String provider) {}
            @Override public void onLocationChanged(Location location) {}
            }
        );

    Util.setMockLocation(locationManager, 0.0, 0.0, 500);
  }
}
