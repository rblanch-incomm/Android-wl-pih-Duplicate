package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.ConfirmAccountView;
import com.incomm.payithere.views.confirmAccountDetails.ConfirmAccountDetailsMVP;

/**
 * Created by agodambe on 9/28/2017.
 */

public class ConfirmAccountDetailsCMSRepository implements ConfirmAccountDetailsMVP.Repository {

    private ConfirmAccountView cmsView;
    public ConfirmAccountDetailsCMSRepository() {
        cmsView = CMSViewManager.getInstance().getConfirmAccountView();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    @Override
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public String getConfirmAccountQuestion() {
        return cmsView.getConfirmAccountQuestion();
    }

    @Override
    public String getConfirmAccountButton() {
        return cmsView.getConfirmAccountButton();
    }

    @Override
    public String getDeclineButtonTitle() {
        return cmsView.getDeclineButtonTitle();
    }

    @Override
    public String getPositiveButtonBackground() {
        return ColorSchemeManager.getInstance().getPositiveButton();
    }

    @Override
    public String getNegativeButtonBackground() {
        return ColorSchemeManager.getInstance().getNegativeButton();
    }

    @Override
    public String getPositiveBtnTextColor() {
        return ColorSchemeManager.getInstance().getPositiveButtonTitle();
    }

    @Override
    public String getNegativeBtnTextColor() {
        return ColorSchemeManager.getInstance().getNegativeButtonTitle();
    }

}
