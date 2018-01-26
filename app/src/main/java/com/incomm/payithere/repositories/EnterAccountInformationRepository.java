package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.EnterAccountInformationView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.views.enteraccountinformation.EnterAccountInformationMVP;

/**
 * Created by rblanch on 11/6/17.
 */

public class EnterAccountInformationRepository implements EnterAccountInformationMVP.Repository {

    private EnterAccountInformationView cmsView;
    public EnterAccountInformationRepository() {
        cmsView = CMSViewManager.getInstance().getEnterAccountInformationView();
    }
    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getConfirmPrefix() {
        return null;
    }

    @Override
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public FeatureContextualHelp getContextualHelp() {
        return cmsView.getContextualHelp();
    }

    @Override
    public String getInstructionsText() {
        return cmsView.getInstructionsText();
    }

    @Override
    public String getSubmitButtonTitle() {
        return cmsView.getSubmitButtonTitle();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }
}
