package com.incomm.payithere.views.template;

import com.incomm.payithere.managers.ColorSchemeManager;

/**
 * Created by agodambe on 9/28/2017.
 */

public class TemplateCMSRepository implements TemplateMVP.Repository {

    //TODO: Add the view in CMSGlobalDocuments, Create a getter in CMSManager and replace getView() with actual view.
    // Move this file to repositories

    // Replace CMSView with actual view
    //private CMSView cmsView;
    public TemplateCMSRepository() {
        //cmsView = CMSViewManager.getInstance().getView();
    }


    @Override
    public String getTitle() {
        return "Template Fragment";
    }

    @Override
    public String getGeneralTextColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
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

    @Override
    public String getAnalyticsId() {
        return null;
//        return cmsView.getAnalytics;
    }
}
