package com.incomm.payithere.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.utils.Constants;

/**
 * Created by Jerome Davis on 3/13/17.
 */
// Constant variables are in the util.Constants interface
public class PreferencesManager implements Constants {

    private static PreferencesManager instance = null;

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static PreferencesManager getInstance() {
        if (instance == null) {
            instance = new PreferencesManager(PayItHereApplication.getContext());
        }

        return instance;
    }

    public PreferencesManager(Context context) {
        this.context = context;
        // set this name to name of app
        sharedPreferences = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public boolean getIsFirstLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true);
    }

    public boolean isFromTab() {
        return sharedPreferences.getBoolean(IS_FROM_TAB, false);
    }

    public boolean showLocationsPermission() {
        return sharedPreferences.getBoolean(SHOW_LOCATIONS_PERMISSION, false);
    }

    public boolean isCallPhoneNeverAskAgainClicked() {
        return sharedPreferences.getBoolean(IS_CALL_PHONE_NEVER_ASK_AGAIN, false);
    }

    public boolean refreshLocationsView() {
        return sharedPreferences.getBoolean(REFRESH_LOCATIONS_VIEW, true);
    }

    public boolean isAccessLocationsNeverAskAgainClicked() {
        return sharedPreferences.getBoolean(IS_ACCESS_LOCATION_NEVER_ASK_AGAIN, false);
    }

    public boolean getIsFirstTimeLogin() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LOGIN, true);
    }

    public boolean isLegalDocumentsAccepted() {
        return sharedPreferences.getBoolean(IS_LEGAL_ACCEPTED, false);
    }

    public boolean isRememberLogin() {
        return sharedPreferences.getBoolean(IS_REMEMBER_LOGIN, false);
    }

    public String getRememberedUsername() {
        return sharedPreferences.getString(REMEBERED_USERNAME, "");
    }
    public boolean isTextMessgageAgreementAccepted() {
        return sharedPreferences.getBoolean(IS_TEXT_MESSAGE_AGREEMENT_ACCEPTED, false);
    }
    public void setSharedPreferences (String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }
    public void setSharedPreferences (String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }
    public void setSharedPreferences (String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }
    public void setSharedPreferences (String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }
    public void setSharedPreferences (String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }
}
