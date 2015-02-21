# Swiss-Army-Cheese

A (will) do-it-all app to make Android app development and debugging easier.


## Building and installing

Building with Ant:

    ant debug

or

    ant release

Installing with ADB:

    adb -s $YOUR_DEVICE_SERIAL install -r bin/SwissArmyCheese-debug.apk

or

    adb -s $YOUR_DEVICE_SERIAL install -r bin/SwissArmyCheese-release-unsigned.apk


## Using the app to set mock GPS location

Setting a latitude and longitude:

    adb -s $YOUR_DEVICE_SERIAL am broadcast -n com.example.swissarmycheese/.Receiver --ef lat -34.174526 --ef lng 43.23

Setting lat/lng with accuracy=20 (default=600):

    adb -s $YOUR_DEVICE_SERIAL am broadcast -n com.example.swissarmycheese/.Receiver --ef lat -34.174526 --ef lng 43.23 --ef acc 20
