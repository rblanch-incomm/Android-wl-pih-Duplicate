package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.ReloadLocationsView;
import com.incomm.payithere.views.locations.LocationsMVP;
import com.incomm.payithere.views.template.TemplateMVP;

/**
 * Created by agodambe on 9/28/2017.
 */

public class LocationsCMSRepository implements LocationsMVP.Repository {

    //TODO: Add the view in CMSGlobalDocuments, Create a getter in CMSManager and replace getView() with actual view.
    // Move this file to repositories

    // Replace CMSView with actual view
    private ReloadLocationsView cmsView;
    public LocationsCMSRepository() {
        cmsView = CMSViewManager.getInstance().getLocationsView();
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
    public String getDirectionsText() {
        return cmsView.getGetDirectionsText();
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
    public String getSearchIcon() {
        return cmsView.getSearchIcon().url();
    }

    @Override
    public String getThemeColor() {
        return ColorSchemeManager.getInstance().getThemePrimary();
    }

    @Override
    public String getSearchPlaceholderText() {
        return cmsView.getSearchPlaceholderText();
    }

    @Override
    public String getSearchRadius() {
        return cmsView.getSearchRadius();
    }

    @Override
    public String getCurrentLocationIcon() {
        return cmsView.getCurrentLocationIcon().url();
    }

    @Override
    public String getCallIcon() {
        return cmsView.getCallIcon().url();
    }

    @Override
    public String getDirectionsIcon() {
        return cmsView.getDirectionsIcon().url();
    }
}
