package com.incomm.payithere.views.CreatePaymentSlip;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.TopBiller;
import com.incomm.payithere.models.services.response.BillerGroup;

import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface CreatePaymentSlipMVP {
    interface View extends BaseView<Presenter> {
        void displayTitle(String title);
        void showError(String error);
        void displayBillerList(List<BillerGroup> billerList);
        void showNoBillersFound();
        void displayTopBillers(List<TopBiller> topBillers);
        void setSearchBarPlaceHolder(String searchBarPlaceholder);
        void setCategoryButtonText(String categoriesButtonText);
        void setRightChevron(String rightChevron);
        void setThemePrimary(String color);
    }

    interface Presenter extends BasePresenter {
        void getTopBillers();
        void getBillerByName(String query);
        void OnBillerByNameCallSuccess(List<BillerGroup> billerList);
        void OnBillerByNameCallError(String error);
        void getRightChevron();
        void getTitle();
        void getThemePrimary();
        void getSearchBarPlaceholder();
        void getCategoriesButtonText();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getThemePrimary();
        String getRightChevron();
        List<TopBiller> getTopBillers();
        String getSearchBarPlaceholder();
        String getCategoriesButtonText();
        String getAnalyticsID();
    }
}
