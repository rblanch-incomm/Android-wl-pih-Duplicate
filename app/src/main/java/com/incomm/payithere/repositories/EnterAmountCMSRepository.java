package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.EnterAmountView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.views.enterAmount.EnterAmountMVP;
import com.incomm.payithere.views.template.TemplateMVP;

/**
 * Created by agodambe on 9/28/2017.
 */

public class EnterAmountCMSRepository implements EnterAmountMVP.Repository {

    private EnterAmountView cmsView;
    public EnterAmountCMSRepository() {
        cmsView = CMSViewManager.getInstance().getEnterAmountView();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    public String getAccountIdLabel() {
        return cmsView.getAccountIdLabel();
    }

    @Override
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public String getEnterAmountPlaceHolder() {
        return cmsView.getEnterPaymentPlaceholderText();
    }

    @Override
    public String getMaxPaymentLabelText() {
        return cmsView.getMaxPaymentLabelText();
    }

    @Override
    public String getPaymentAmountLabelText() {
        return cmsView.getPaymentAmountLabelText();
    }

    @Override
    public String getFeeLabelText() {
        return cmsView.getFeeLabelText();
    }

    @Override
    public String getTotalAmountLabelText() {
        return cmsView.getTotalAmountLabelText();
    }

    @Override
    public String getDueDatePlaceholderText() {
        return cmsView.getDueDatePlaceholderText();
    }

    @Override
    public String getNotificationLabelText() {
        return cmsView.getNotificationLabelText();
    }

    @Override
    public FeatureContextualHelp getContextHelpText() {
        return cmsView.getContextHelpTest();
    }

    @Override
    public String getCreatePaymentButtonTitle() {
        return cmsView.getCreatePaymentButtonTitle();
    }

}
