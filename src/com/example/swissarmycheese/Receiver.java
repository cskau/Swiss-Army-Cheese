package com.example.swissarmycheese;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class Receiver extends BroadcastReceiver {

  static final String TAG = Receiver.class.getCanonicalName();

  @Override
  public void onReceive(Context context, Intent intent) {
    try {
      Float lat = intent.getFloatExtra("lat", 0.0f);
      Float lng = intent.getFloatExtra("lng", 0.0f);
      Float accuracy = intent.getFloatExtra("acc", 500.0f);

      Log.i(
          TAG,
          "Setting mock location: (" + lat + ", " + lng + ", " + accuracy + ")");

      Util.setMockLocation(context, lat, lng, accuracy);
    } catch (Exception e) {
      Log.i(TAG, e.toString());
    }
  }

}
