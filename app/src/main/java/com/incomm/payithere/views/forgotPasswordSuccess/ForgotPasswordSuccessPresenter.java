package com.incomm.payithere.views.forgotPasswordSuccess;


import android.app.Activity;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.models.cms.ChangePasswordSuccessView;
import com.incomm.payithere.models.cms.ForgotPasswordSuccessView;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;
import com.incomm.payithere.views.changepasswordsuccess.ChangePasswordSuccessMVP;

/**
 * Created by Jerome Davis on 4/13/17.
 */

public class ForgotPasswordSuccessPresenter extends ViewPresenter implements ForgotPasswordSuccessMVP.Presenter {

    private ForgotPasswordSuccessMVP.View view;
    private ForgotPasswordSuccessView cmsView;


    public ForgotPasswordSuccessPresenter(ForgotPasswordSuccessMVP.View view) {
        this.view = view;
    }


    @Override
    public void getViewElements() {
        cmsView = (ForgotPasswordSuccessView) DocumentsManager.getInstance().getCmsResource(ForgotPasswordSuccessView.class);
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
