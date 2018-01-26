package com.incomm.payithere.utils;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by rblanch on 12/1/17.
 */

public class GoogleAnalyticsUtils {
    private static final GoogleAnalyticsUtils ourInstance = new GoogleAnalyticsUtils();
    FirebaseAnalytics firebaseAnalytics;

    public void sendData(Activity activity, String screenName) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        firebaseAnalytics.setCurrentScreen(activity, screenName, null);
    }

    public static GoogleAnalyticsUtils getInstance() {
        return ourInstance;
    }

    private GoogleAnalyticsUtils() {

    }
}
