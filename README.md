# ImageSlide
ImageSlide is an app for Android which can be used in order to view images from different sites. 

# Setup
The files are done through Android studio, and so compiling is best done through Android Studio. Download Android Studio and the necessary drivers needed for your computer to run it. There are two ways to do this:

## Through Emulator
**The emulator through Android Studio only functions with Intel Processors. AMD processors will have to use an external phone to run app.**

1. Download the default packages checked in the SDK Standalone Manager.
2. Create a Virtual Device to run the app in.
3. Run the Program

## Using an Android Phone
1. Enable Developer Mode and USB Debugging in Android phone. http://developer.android.com/tools/device.html
2. Download and install driver for your phone.
3. Run the program and use your connected phone.

# Navigating App
The App has the main gallery, which shows all the images, and two settings on the top-right. In Image Sites you can change the different Reddit subreddits which you want to grab your images from.

# Running tests
Automated tests are run through Espresso, download and install required sdk packages for Espresso/android testing.  If using Android Studio, right click on the test called EspressoTest inside the androidTest directory and select run.
https://google.github.io/android-testing-support-library/docs/espresso/setup/