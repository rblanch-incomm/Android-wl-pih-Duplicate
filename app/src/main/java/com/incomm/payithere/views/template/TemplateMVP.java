package com.incomm.payithere.views.template;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.services.response.BillerCategory;

import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface TemplateMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title);
    }

    interface Presenter extends BasePresenter {
        void setTitle();
        String getGeneralTextColor();
        String getPositiveButtonBackground();
        String getNegativeButtonBackground();
        String getPositiveBtnTextColor();
        String getNegativeBtnTextColor();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getPositiveButtonBackground();
        String getNegativeButtonBackground();
        String getPositiveBtnTextColor();
        String getNegativeBtnTextColor();
        String getAnalyticsId();
    }
}
