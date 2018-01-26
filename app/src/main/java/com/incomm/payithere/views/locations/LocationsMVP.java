package com.incomm.payithere.views.locations;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.services.response.Store;

import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface LocationsMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title);
        void fragmentBecameVisible();
        void showStoresOnMap(List<Store> storesList);
        void showSearchIcon(String icon);
        void showPlaceholderText(String text);
        void showCurrentLocationIcon(String location);
        void showCallIcon(String callIcon);
        void showDirectionsIcon(String directions);
        void displayDirectionsText(String text);
    }

    interface Presenter extends BasePresenter {
        void setTitle();
        String getGeneralTextColor();
        String getThemeColor();
        String getPositiveButtonBackground();
        String getNegativeButtonBackground();
        String getPositiveBtnTextColor();
        String getNegativeBtnTextColor();
        void getStoresByLocation(double latitude, double longitude);
        void OnStoresByLocationSuccess(List<Store> storesList);
        void OnStoresByLocationError(String s);
        void getStoresByAddress(String mAddress);
        void getSearchIcon();
        void getDirectionsText();
        void getSearchPlaceholderText();
        String getSearchRadius();
        void getCurrentLocationIcon();
        void getCallIcon();
        void getDirectionsIcon();
        void cancelAllCalls();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getDirectionsText();
        String getPositiveButtonBackground();
        String getNegativeButtonBackground();
        String getPositiveBtnTextColor();
        String getNegativeBtnTextColor();
        String getSearchIcon();
        String getThemeColor();
        String getSearchPlaceholderText();
        String getSearchRadius();
        String getCurrentLocationIcon();
        String getCallIcon();
        String getDirectionsIcon();
        String getAnalyticsId();
    }

    interface Service {
        void getStoresByLocation(final LocationsPresenter presenter, String latitude,String longitude,String radius);
        void getStoresByAddress(final LocationsPresenter presenter, String address,String radius);
        void cancelAll();
    }
}
