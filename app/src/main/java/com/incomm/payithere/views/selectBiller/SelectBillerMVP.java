package com.incomm.payithere.views.selectBiller;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.services.response.BillerGroup;

import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface SelectBillerMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title);
        void showBillersList(List<BillerGroup> billerList);
        void setRightChevron(String rightChevron);
        void displayQueryData(List<BillerGroup> mFilteredList);
        void showWhenListEmpty();
        void displayPaymentFragment(BillerGroup billerGroup);
    }

    interface Presenter extends BasePresenter {
        void setTitle();
        String getGeneralTextColor();
        void setBillersList(String category);
        void OnSingleCategoryCallSuccess(List<BillerGroup> billerList);
        void OnSingleCategoryCallError(String error);
        String getGlobalTextColor();
        void getRightChevron();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getThemePrimary();
        String getRightChevron();
        String getAnalyticsId();
    }

    interface Service{
        void getBillerCategory(SelectBillerPresenter selectBillerPresenter, String category);
    }
}
