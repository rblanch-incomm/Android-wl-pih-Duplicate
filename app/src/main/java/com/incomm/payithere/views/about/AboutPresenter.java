package com.incomm.payithere.views.about;

import android.app.Activity;

import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.models.cms.AboutView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

/**
 * Created by Jerome Davis on 9/12/17.
 */

public class AboutPresenter extends ViewPresenter implements AboutMVP.Presenter{

    AboutFragment view;
    AboutView cmsAboutView;

    public AboutPresenter(AboutFragment view){
        this.view = view;
    }

    public void getViewElements() {
        cmsAboutView = (AboutView) DocumentsManager.getInstance().getCmsResource(AboutView.class);
        view.setupUi();
    }

    public String getName() {
        return cmsAboutView.getName();
    }

    public String getTitle(){
        return cmsAboutView.getTitle();
    }

    public String getBody(){
        return cmsAboutView.getBody();
    }

    public String getLogoUrl(){
        return cmsAboutView.getLogo().url();
    }

    public FeatureContextualHelp getFeatureContextualHelp() {
        return cmsAboutView.getFeatureContextualHelp();
    }

    public String getAnalyticsId() {
        return cmsAboutView.getAnalyticsId();
    }
}
