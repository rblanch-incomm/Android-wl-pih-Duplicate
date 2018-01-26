package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.BillerCategoryIcon;
import com.incomm.payithere.models.cms.BillerCategoryView;
import com.incomm.payithere.models.cms.BillsHistoryView;
import com.incomm.payithere.views.billerCategory.BillerCategoryMVP;
import com.incomm.payithere.views.billsHistory.BillerHistoryMVP;

import java.util.List;

/**
 * Created by agodambe on 9/28/2017.
 */

public class BillerCategoryCMSRepository implements BillerCategoryMVP.Repository {

    private BillerCategoryView cmsView;
    public BillerCategoryCMSRepository() {
        cmsView = CMSViewManager.getInstance().getBillerCategoryView();
    }

    @Override
    public String getAnalyticsID() {
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
    public String getThemePrimary() {
        return ColorSchemeManager.getInstance().getThemePrimary();
    }

    @Override
    public String getRightChevron() {
        return GlobalImagesManager.getInstance().getChevronRight().url();
    }

    @Override
    public List<BillerCategoryIcon> getCategoryIcons() {
        return cmsView.getCategoryIcons();
    }

}
