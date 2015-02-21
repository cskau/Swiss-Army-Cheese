package com.example.swissarmycheese;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


public class Receiver extends BroadcastReceiver {

  static final String TAG = Receiver.class.getCanonicalName();

  LocationManager locationManager;

  @Override
  public void onReceive(Context context, Intent intent) {
    if (locationManager == null) {
      locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
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
    }

    try {
      Float lat = intent.getFloatExtra("lat", 0.0f);
      Float lng = intent.getFloatExtra("lng", 0.0f);
      Float accuracy = intent.getFloatExtra("acc", 500.0f);

      Util.setMockLocation(locationManager, lat, lng, accuracy);
    } catch (Exception e) {
      Log.i(TAG, e.toString());
    }
  }

}
