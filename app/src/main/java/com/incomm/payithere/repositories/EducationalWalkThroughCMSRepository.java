package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.models.cms.EducationalSlide;
import com.incomm.payithere.models.cms.EducationalWalkthroughView;
import com.incomm.payithere.views.educational.EducationalMVP;

import java.util.List;

/**
 * Created by Jerome Davis on 10/25/17.
 */

public class EducationalWalkThroughCMSRepository implements EducationalMVP.Repository {

    private EducationalWalkthroughView cmsView;

    public EducationalWalkThroughCMSRepository() {
        cmsView = CMSViewManager.getInstance().getEducationalWalkThroughs();
    }
    @Override
    public String getName(){
        return cmsView.getName();
    }

    @Override
    public String getTitle() {
        return cmsView.getTitle();
    }

    @Override
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
    }

    @Override
    public String getSkipButtonLabel() {
        return cmsView.getSkipButtonLabel();
    }

    @Override
    public String getCreatePaymentButtonLabel() {
        return cmsView.getCreatePaymentLabel();
    }

    @Override
    public List<EducationalSlide> getEducationalSlides() {
        return cmsView.getEducationalSlides();
    }


}
