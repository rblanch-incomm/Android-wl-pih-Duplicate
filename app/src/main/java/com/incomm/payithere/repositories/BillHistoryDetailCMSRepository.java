package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.models.cms.BillHistoryDetailView;
import com.incomm.payithere.views.billHistoryDetail.BillHistoryDetailMVP;

/**
 * Created by bjennings on 10/18/2017.
 */

public class BillHistoryDetailCMSRepository implements BillHistoryDetailMVP.Repository {
    private BillHistoryDetailView cmsView;

    public BillHistoryDetailCMSRepository() {
        cmsView = CMSViewManager.getInstance().getBillHistoryDetailView();
    }

    @Override
    public String getUsedAtLabel() {
        return cmsView.getUsedAtLabel();
    }

    @Override
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public String getUsedOnLabel() {
        return cmsView.getUsedOnLabel();
    }

    @Override
    public String getAccountNumberLabel() {
        return cmsView.getAccountNumberLabel();
    }

    @Override
    public String getEreceiptButtonText() {
        return cmsView.getEreceiptButtonText();
    }

    @Override
    public String getNewPaymentButtonText() {
        return cmsView.getNewPaymentButtonText();
    }

    @Override
    public String getTitle() { return cmsView.getTitle(); }

}
