package com.example.swissarmycheese;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class MainActivity extends Activity {

  static final String TAG = MainActivity.class.getCanonicalName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Util.setMockLocation(this, 0.0, 0.0, 500);
  }
}
