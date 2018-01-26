package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.HelpView;
import com.incomm.payithere.views.help.HelpMVP.Repository;

import java.util.List;

/**
 * Created by bjennings on 10/11/2017.
 */

public class HelpRepository implements Repository {
    private HelpView cmsView;

    public HelpRepository() {
        cmsView = CMSViewManager.getInstance().getHelpView();
    }
    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
    @Override
    public String getTitle() { return cmsView.getTitle(); }

    @Override
    public String getPhoneNumber() {
        return cmsView.getCustomerCarePhoneNumber();
    }

    @Override
    public String getMailingAddress() { return cmsView.getMailingAddress(); }

    @Override
    public String getDeviceIdLabel() { return cmsView.getDeviceIdentifierLabel(); }

    @Override
    public String getCallMessage() { return cmsView.getCallNumberMessage(); }

    @Override
    public String getNegativeButton() { return cmsView.getNegativeButtonTitle(); }

    @Override
    public String getPositivePhoneButton() { return cmsView.getCallPositiveButtonTitle(); }

    @Override
    public List<Feature> getFeatures() { return cmsView.getFeatures(); }

    @Override
    public String getPhoneLabel() { return cmsView.getCallSupportLabel(); }

    @Override
    public String getAddressLabel() { return cmsView.getMailSupportLabel(); }
}
