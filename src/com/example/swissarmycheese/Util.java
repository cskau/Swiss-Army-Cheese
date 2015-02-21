package com.example.swissarmycheese;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import java.lang.reflect.Method;


public class Util {

  static public void setMockLocation(
      LocationManager locationManager,
      double lat,
      double lng,
      float accuracy
      ) {
    locationManager.addTestProvider(
        LocationManager.GPS_PROVIDER,
        false, // requiresNetwork
        false, // requiresSatellite
        false, // requiresCell
        false, // hasMonetaryCost
        false, // supportsAltitude
        false, // supportsSpeed
        false, // supportsBearing
        android.location.Criteria.POWER_LOW,
        android.location.Criteria.ACCURACY_FINE
        );

    Location newLocation = new Location(LocationManager.GPS_PROVIDER);

    newLocation.setLatitude(lat);
    newLocation.setLongitude(lng);
    newLocation.setAccuracy(accuracy);
    newLocation.setTime(System.currentTimeMillis());

    // http://jgrasstechtips.blogspot.jp/2012/12/android-incomplete-location-object.html
    try {
      Method locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
      if (locationJellyBeanFixMethod != null) {
        locationJellyBeanFixMethod.invoke(newLocation);
      }
    } catch(Exception e) {
      // nop
    }

    locationManager.setTestProviderEnabled(
        LocationManager.GPS_PROVIDER,
        true
        );

    locationManager.setTestProviderStatus(
        LocationManager.GPS_PROVIDER,
        LocationProvider.AVAILABLE,
        null,
        System.currentTimeMillis()
        );

    locationManager.setTestProviderLocation(
        LocationManager.GPS_PROVIDER,
        newLocation
        );
  }

}
