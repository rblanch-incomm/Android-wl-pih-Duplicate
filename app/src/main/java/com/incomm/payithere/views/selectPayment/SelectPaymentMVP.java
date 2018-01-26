package com.incomm.payithere.views.selectPayment;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.Option;

import java.util.ArrayList;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface SelectPaymentMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title);
        void displayPaymentType (ArrayList<Option> paymentType);
    }

    interface Presenter extends BasePresenter {
        String getGeneralTextColor();
        void retrieveFeeLabel();
        void retrievePostingTimeLabel();
        void retrieveContextualHelpLabel();
        void displayPaymentType();
    }
    interface Repository{
        String getTitle();
        String getFeeLabel();
        String getPostingTimeLabel();
        FeatureContextualHelp getContextualHelpLabel();
        String getGeneralTextColor();
        String getAnalyticsId();
    }
}
