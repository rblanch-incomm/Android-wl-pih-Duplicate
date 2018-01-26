package com.incomm.payithere.views.locations;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.models.services.response.Store;
import com.incomm.payithere.utils.ActivityUtils;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.incomm.payithere.utils.Constants.IS_ACCESS_LOCATION_NEVER_ASK_AGAIN;
import static com.incomm.payithere.utils.Constants.IS_CALL_PHONE_NEVER_ASK_AGAIN;
import static com.incomm.payithere.utils.Constants.IS_FROM_TAB;


/**
 * Created by agodambe on 10/18/2017.
 */

public class LocationsPresenter implements LocationsMVP.Presenter{
    private LocationsMVP.View view;
    private LocationsMVP.Repository repository;
    private LocationsMVP.Service service;
    private PreferencesManager preferencesManager;

    public LocationsPresenter(LocationsMVP.View view, LocationsMVP.Repository repository,LocationsMVP.Service service) {
        this.view = view;
        this.repository = repository;
        this.service = service;
        preferencesManager = PreferencesManager.getInstance();
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void setTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    @Override
    public String getGeneralTextColor() {
        return repository.getGeneralTextColor();

    }

    @Override
    public String getThemeColor() {
        return repository.getThemeColor();
    }

    @Override
    public String getPositiveButtonBackground() {
        return repository.getPositiveButtonBackground();
    }

    @Override
    public String getNegativeButtonBackground() {
        return repository.getNegativeButtonBackground();
    }

    @Override
    public String getPositiveBtnTextColor() {
        return repository.getPositiveBtnTextColor();
    }

    @Override
    public String getNegativeBtnTextColor() {
        return repository.getNegativeBtnTextColor();
    }

    @Override
    public void getStoresByLocation(double latitude, double longitude) {
        service.getStoresByLocation(this,String.valueOf(latitude),String.valueOf(longitude),getSearchRadius());
    }

    @Override
    public void OnStoresByLocationSuccess(List<Store> storesList) {
        if(storesList.isEmpty()){
            view.showError("20006");
        }else {
            view.showStoresOnMap(storesList);
        }
    }

    @Override
    public void OnStoresByLocationError(String s) {

    }

    @Override
    public void getStoresByAddress(String address) {
        if(address.isEmpty()){
            view.showError("20308");
            return;
        }
        service.getStoresByAddress(this,address,getSearchRadius());
    }

    @Override
    public void getSearchIcon() {
        String icon = ActivityUtils.checkNull(repository.getSearchIcon());
        view.showSearchIcon(icon);
    }

    @Override
    public void getDirectionsText() {
        String text = ActivityUtils.checkNull(repository.getDirectionsText());
        view.displayDirectionsText(text);
    }

    @Override
    public void getSearchPlaceholderText() {
        String text = ActivityUtils.checkNull(repository.getSearchPlaceholderText());
        view.showPlaceholderText(text);
    }

    @Override
    public String getSearchRadius() {
        return ActivityUtils.checkNull(repository.getSearchRadius());
    }

    @Override
    public void getCurrentLocationIcon() {
        String location = ActivityUtils.checkNull(repository.getCurrentLocationIcon());
        view.showCurrentLocationIcon(location);
    }

    @Override
    public void getCallIcon() {
        String callIcon = ActivityUtils.checkNull(repository.getCallIcon());
        view.showCallIcon(callIcon);
    }

    @Override
    public void getDirectionsIcon() {
        String directions = ActivityUtils.checkNull(repository.getDirectionsIcon());
        view.showDirectionsIcon(directions);
    }

    @Override
    public void cancelAllCalls() {
        service.cancelAll();
    }

    public boolean isCallPhoneNeverAskAgainClicked(){
        return preferencesManager.isCallPhoneNeverAskAgainClicked();
    }


    public boolean isAccessLocationNeverAskAgainClicked(){
        boolean is = preferencesManager.isAccessLocationsNeverAskAgainClicked();
        return is;
    }

    public boolean isFromTab(){
        return preferencesManager.isFromTab();
    }

    public void setCallPhoneNeverAskAgainClicked(boolean isClicked) {
        preferencesManager.setSharedPreferences(IS_CALL_PHONE_NEVER_ASK_AGAIN, isClicked);
    }

    public void setAccessLocationNeverAskAgainClicked(boolean isClicked) {
        preferencesManager.setSharedPreferences(IS_ACCESS_LOCATION_NEVER_ASK_AGAIN, isClicked);
    }

    public void displayLocationSettingsRequest(Context context, final Activity activity, GoogleApiClient googleApiClient) {

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(activity,LocationsFragment.REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    public void setIsFromTab(boolean b) {
        PreferencesManager.getInstance().setSharedPreferences(IS_FROM_TAB, b);
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsId();
    }
}
