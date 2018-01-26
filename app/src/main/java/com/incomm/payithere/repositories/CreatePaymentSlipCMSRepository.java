package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.managers.GlobalImagesManager;
import com.incomm.payithere.models.cms.CreatePaymentSlipView;
import com.incomm.payithere.models.cms.TopBiller;
import com.incomm.payithere.views.CreatePaymentSlip.CreatePaymentSlipMVP;

import java.util.List;

/**
 * Created by agodambe on 9/28/2017.
 */

public class CreatePaymentSlipCMSRepository implements CreatePaymentSlipMVP.Repository {

    private CreatePaymentSlipView cmsView;
    public CreatePaymentSlipCMSRepository() {
        cmsView = CMSViewManager.getInstance().getCreatePaymentSlipView();
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
    public String getAnalyticsID() {
        return cmsView.getAnalyticsId();
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
    public List<TopBiller> getTopBillers() {
        return cmsView.getTopBillers();
    }

    @Override
    public String getSearchBarPlaceholder() {
        return cmsView.getSearchBarPlaceholder();
    }

    @Override
    public String getCategoriesButtonText() {
        return cmsView.getCategoriesButtonText();
    }


}
