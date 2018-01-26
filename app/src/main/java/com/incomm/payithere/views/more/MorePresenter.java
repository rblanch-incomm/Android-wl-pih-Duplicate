package com.incomm.payithere.views.more;


import android.app.Activity;
import android.util.Log;

import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.Feature;
import com.incomm.payithere.models.cms.GlobalImages;
import com.incomm.payithere.models.cms.MoreView;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

import java.util.List;


/**
 * Created by Jerome Davis on 4/13/17.
 */

public class MorePresenter extends ViewPresenter implements MoreMVP.Presenter {

    //private ColorSchemeManager colorSchemeManager;
    //private GlobalImagesManager globalImagesManager;
    private MoreMVP.View view;
    private MoreView moreView;
    private List<Feature> features;
    private String buttonLabelText,viewTitle;

    public MorePresenter(MoreMVP.View view) {
        //colorSchemeManager = PayItHereApplication.getColorSchemeManager();
        this.view = view;
        //globalImagesManager = PayItHereApplication.getGlobalImagesManager();
    }


    @Override
    public void getViewElements() {
        moreView = CMSViewManager.getInstance().getMoreView();
        features = moreView.getFeatures();
        Log.e("FEATURES", features.toString());
        viewTitle = moreView.getTitle();
        buttonLabelText = moreView.getLogoutButtonTitle();
        view.setupUi();
        view.setViewFeatures();
    }


    public List<Feature> getFeatures() {
        return features;
    }

    public String getButtonLabel() {
        return buttonLabelText;
    }

    public String getViewTitle() {
        return viewTitle;
    }

    public String getGlobalTextColor(){
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    public String getnextChevron(){
        return ActivityUtils.checkNull(GlobalImagesManager.getInstance().getChevronRight());
    }

    public String getGlobalLabelColor() {
        return ColorSchemeManager.getInstance().getGeneralText();
    }

    public String getGlobalButtonColor() {
        return ColorSchemeManager.getInstance().getPositiveButtonTitle();
    }

    public String getAnalyticsId() {
        return moreView.getAnalyticsId();
    }
}
