package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.SelectBillerView;
import com.incomm.payithere.views.selectBiller.SelectBillerMVP;

/**
 * Created by agodambe on 9/28/2017.
 */

public class SelectBillerCMSRepository implements SelectBillerMVP.Repository {

    //TODO: Add the view in CMSGlobalDocuments, Create a getter in CMSManager and replace getView() with actual view.
    // Move this file to repositories

    // Replace CMSView with actual view
    private SelectBillerView cmsView;
    public SelectBillerCMSRepository() {
        cmsView = CMSViewManager.getInstance().getSelectBillerView();
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
    public String getThemePrimary() {
        return ColorSchemeManager.getInstance().getThemePrimary();
    }

    @Override
    public String getRightChevron() {
        return GlobalImagesManager.getInstance().getChevronRight().url();
    }

}
