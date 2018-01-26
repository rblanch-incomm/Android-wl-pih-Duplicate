package com.incomm.payithere.views.billerCategory;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.BillerCategoryItem;
import com.incomm.payithere.models.cms.BillerCategoryIcon;
import com.incomm.payithere.models.services.response.BillerCategory;
import com.incomm.payithere.models.services.response.BillerGroup;

import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface BillerCategoryMVP {
    interface View extends BaseView<Presenter> {
        void displayTitle(String title);
        void showError(String error);
        void displayCategoryList(List<BillerCategoryItem> billerList);
    }

    interface Presenter extends BasePresenter {
        void getBillerCategories();
        void OnCategoryListCallSuccess(List<BillerCategory> categoryList);
        void OnCategoryListCallError(String error);
        void getTitle();
        String getGeneralTextColor();
        String getThemePrimary();
        String getRightChevron();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getThemePrimary();
        String getRightChevron();
        List<BillerCategoryIcon> getCategoryIcons();
        String getAnalyticsID();
    }
}
