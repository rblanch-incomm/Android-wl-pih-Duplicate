package com.incomm.payithere.views.help;

import android.app.Activity;

import com.contentful.vault.Asset;
import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.Feature;

import java.util.List;

/**
 * Created by bjennings on 10/11/2017.
 */

public interface HelpMVP {
    interface View extends BaseView<HelpMVP.Presenter> {
        void displayMailingAddress(String mailingAddress);
        void displayPhoneNumber(String phoneNumber);
        void displayDeviceId();
        void displayCallConfirmation(String message, String positiveButton, String negativeButton);
        void makePhoneCall(String phoneNumber);
        void createRecyclerView(List<Feature> features, String textColor, Asset chevron);
        void launchFragment(String key);
        void setFragmentTitle(String title);
        void displayCallLabel(String callSupport);
        void displayMailLabel(String mailSupport);
        void displayDeviceIdLabel(String deviceId);
    }

    interface Presenter extends BasePresenter {
        void displayMailingAddress();
        void displayPhoneNumber();
        void displayDeviceId();
        void setFragmentTitle();
        void displayCallLabel();
        void displayMailLabel();
        void displayDeviceIdLabel();
        void onPhoneClick();
        void confirmPhoneCall();
        void onItemClicked(int position);
        String getAnalyticsId();
    }

    interface Repository {
        String getTitle();
        String getAddressLabel();
        String getPhoneLabel();
        String getPhoneNumber();
        String getMailingAddress();
        String getDeviceIdLabel();
        String getCallMessage();
        String getNegativeButton();
        String getPositivePhoneButton();
        List<Feature> getFeatures();
        String getAnalyticsId();
    }
}
