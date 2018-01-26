package com.incomm.payithere.views.changepasswordsuccess;


import android.app.Activity;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.models.cms.ChangePasswordSuccessView;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

/**
 * Created by Jerome Davis on 4/13/17.
 */

public class ChangePasswordSuccessPresenter extends ViewPresenter implements ChangePasswordSuccessMVP.Presenter {

    private ChangePasswordSuccessMVP.View view;
    private ChangePasswordSuccessView cmsView;


    public ChangePasswordSuccessPresenter(ChangePasswordSuccessMVP.View view) {
        this.view = view;
    }


    @Override
    public void getViewElements() {
        cmsView = (ChangePasswordSuccessView) DocumentsManager.getInstance().getCmsResource(ChangePasswordSuccessView.class);
        view.setupUi();
        view.setViewFeatures();
    }


    public String getLabelColor(){
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    public String getButtonColor(){
        return ColorSchemeManager.getInstance().getPositiveButton();
    }

    public String getButtonTitleColor(){
        return ColorSchemeManager.getInstance().getPositiveButtonTitle();
    }


    public String getBodyText() {
        return cmsView.getBody();
    }

    public String getContinueButtontitle() {
        return cmsView.getContinueButtonTitle();
    }

    public String getTitle() {
        return cmsView.getTitle();
    }

    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
}
