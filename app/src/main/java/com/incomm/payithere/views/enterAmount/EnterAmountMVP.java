package com.incomm.payithere.views.enterAmount;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.PaymentPost;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface EnterAmountMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title);
        void displayAccountIdLabel(String text);
        void displayMaxPaymentLabel(String label);
        void displayPaymentAmountLabel(String label);
        void displayFeeLabel(String label);
        void displayTotalAmountLabel(String label);
        void displayDueDatePlaceholder(String placeHolder);
        void displayNotificationText(String text);
        void displayCreatePaymentButton(String title);
        void displayEnterAmountPlaceHolder(String text);
        void displayPaymentSlip(PaymentPost payment);
        void displayContextualHelpLabel();
    }

    interface Presenter extends BasePresenter {
        void setTitle();
        String getGeneralTextColor();
        void getAccountIdLabel();
        void getMaxPaymentLabelText();
        void getPaymentAmountLabelText();
        void getFeeLabelText();
        void getTotalAmountLabelText();
        void getDueDatePlaceholderText();
        void getNotificationLabelText();
        FeatureContextualHelp getContextHelpText();
        void getCreatePaymentButtonTitle();
        void getEnterAmountPlaceHolder();
        void enterAmountSuccess(PaymentPost payment);
        void enterAmountFailure(String s);
        void createPaymentSlip(String totalAmount,String billerId,String paymentId);
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getAccountIdLabel();
        String getEnterAmountPlaceHolder();
        String getMaxPaymentLabelText();
        String getPaymentAmountLabelText();
        String getFeeLabelText();
        String getTotalAmountLabelText();
        String getDueDatePlaceholderText();
        String getNotificationLabelText();
        FeatureContextualHelp getContextHelpText();
        String getCreatePaymentButtonTitle();
        String getAnalyticsID();
    }

    interface Service {
        void enterAmount(final EnterAmountMVP.Presenter presenter,String totalAmount, String billerId, String paymentId);
    }
}
