package com.incomm.payithere.repositories;

import com.contentful.vault.Asset;
import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.models.cms.GettingStartedView;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.views.walkthrough.WalkThroughMVP;

import java.util.List;

/**
 * Created by Jerome Davis on 9/25/17.
 */

public class WalkThroughCMSRepository implements WalkThroughMVP.WalkThroughRepository {

    private GettingStartedView cmsView;

    public WalkThroughCMSRepository() {
        cmsView = CMSViewManager.getInstance().getWalkThroughView();
    }
    @Override
    public String getAnalyticsId() {
        return cmsView.getAnalyticsId();
    }
    @Override
    public String getName() {
        return cmsView.getName();
    }

    @Override
    public String getTitle() {
        return ActivityUtils.checkNull(cmsView.getTitle());
    }

    @Override
    public String getChannel() {
        return cmsView.getChannel();
    }

    @Override
    public List<Asset> getImages() {
        return cmsView.getImages();
    }

    @Override
    public List<Asset> getImagesAlternateResolution() {
        return cmsView.getImagesAlternateResolution();
    }

    @Override
    public String getContinueButtonTitle() {
        return cmsView.getContinueButtonTitle();
    }

}
