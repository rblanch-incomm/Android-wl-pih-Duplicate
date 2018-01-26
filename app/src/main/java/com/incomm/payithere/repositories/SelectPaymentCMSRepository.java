package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.cms.SelectPaymentFeeView;
import com.incomm.payithere.views.selectPayment.SelectPaymentMVP;

/**
 * Created by agodambe on 9/28/2017.
 */

public class SelectPaymentCMSRepository implements SelectPaymentMVP.Repository {

    // Move this file to repositories

    // Replace CMSView with actual view
    private SelectPaymentFeeView cmsView;
    public SelectPaymentCMSRepository() {
        cmsView = CMSViewManager.getInstance().getSelectPaymentView();
    }
    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
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
    public String getFeeLabel() {
        return cmsView.getFeeLabelText();
    }

    @Override
    public String getPostingTimeLabel() {
        return cmsView.getPostingTimeLabelText();
    }

    @Override
    public FeatureContextualHelp getContextualHelpLabel() {
        return cmsView.getContextualHelp();
    }

}
