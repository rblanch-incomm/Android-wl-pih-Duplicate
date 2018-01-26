package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.SignUpSuccessView;
import com.incomm.payithere.views.signUpSuccess.SignUpSucessMVP;

/**
 * Created by agodambe on 9/14/2017.
 */

public class SignUpSuccessCMSRepository implements SignUpSucessMVP.Repository {

    private SignUpSuccessView cmsView;
    public SignUpSuccessCMSRepository() {
        cmsView = CMSViewManager.getInstance().getSignUpSuccessView();
    }


    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
    @Override
    public String getBodyText() {
        return cmsView.getBody();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getContinueButton() {
        return cmsView.getContinueButtonTitle();
    }

    @Override
    public String getSignUpButtonColor() {
        return ColorSchemeManager.getInstance().getPositiveButton();
    }

    @Override
    public String getButtonTextColor() {
        return ColorSchemeManager.getInstance().getPositiveButtonTitle();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

}
