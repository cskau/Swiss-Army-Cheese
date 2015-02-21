package com.example.swissarmycheese;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.Method;


public class Util {

  static final String TAG = Util.class.getCanonicalName();

  public static Boolean isLocationServiceEnabled(Context context) {
    // ..
    try {
      int locationMode = Settings.Secure.getInt(
          context.getContentResolver(),
          Settings.Secure.LOCATION_MODE);
      return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    } catch(Exception e) {
      // nop
    }

    // ..
    String providerAllowed = Settings.Secure.getString(
        context.getContentResolver(),
        Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
    return (providerAllowed != null && !providerAllowed.equals(""));
  }


  static public Boolean setMockLocation(
      Context context,
      double lat,
      double lng,
      float accuracy
      ) {

    if (!isLocationServiceEnabled(context)) {
      Log.e(TAG, "Location Service not enabled!");
    }

    LocationManager locationManager = (LocationManager)context.getSystemService(
        Context.LOCATION_SERVICE);

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

    return true;
  }


  static public Boolean toggleWifi(Context context) {
    WifiManager wifiManager = (WifiManager)context.getSystemService(
        Context.WIFI_SERVICE);
    return toggleWifi(
        wifiManager,
        wifiManager.isWifiEnabled());
  }

  static public Boolean toggleWifi(Context context, Boolean enable) {
    return toggleWifi(
        (WifiManager)context.getSystemService(Context.WIFI_SERVICE),
        enable);
  }

  static public Boolean toggleWifi(WifiManager wifiManager, Boolean enable) {
    wifiManager.setWifiEnabled(enable);
    return wifiManager.isWifiEnabled();
  }

}
