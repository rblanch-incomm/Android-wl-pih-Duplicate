package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.BillsHistoryView;
import com.incomm.payithere.views.billsHistory.BillerHistoryMVP;

/**
 * Created by agodambe on 9/28/2017.
 */

public class BillerHistoryCMSRepository implements BillerHistoryMVP.Repository {

    private BillsHistoryView cmsView;
    public BillerHistoryCMSRepository() {
        cmsView = CMSViewManager.getInstance().getBillsHistoryView();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public void getBillsList() {

    }

    @Override
    public String getRightChevron() {
        return GlobalImagesManager.getInstance().getChevronRight().url();
    }

    @Override
    public String getPendingText() {
        return cmsView.getPendingStatusText();
    }

    @Override
    public String getPendingTextColor() {
        return cmsView.getPendingStatusColor();
    }

    @Override
    public String getProcessedText() {
        return cmsView.getProcessedStatusText();
    }

    @Override
    public String getProcessedTextColor() {
        return cmsView.getProcessedStatusColor();
    }

    @Override
    public String getReversedText() {
        return cmsView.getReversedStatusText();
    }

    @Override
    public String getReversedTextColor() {
        return cmsView.getReversedStatusColor();
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    @Override
    public String getThemePrimary() {
        return ColorSchemeManager.getInstance().getThemePrimary();
    }


    @Override
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }
}
