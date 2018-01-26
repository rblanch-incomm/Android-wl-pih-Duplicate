package com.incomm.payithere.views.confirmAccountDetails;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.services.response.PaymentPost;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface ConfirmAccountDetailsMVP {
    interface View extends BaseView<Presenter> {
        void showError(String error);
        void displayTitle(String title);
        void displayQuestion(String question);
        void displayConfirmButton(String confirm);
        void displayDeclineButton(String decline);
        void displayNextPage(PaymentPost payment);
    }

    interface Presenter extends BasePresenter {
        void setTitle();
        String getGeneralTextColor();
        void confirmAccount(String billerId, String paymentId);
        void confirmAccountSuccess(PaymentPost payment);
        void confirmAccountFailure(String s);
        String getPositiveButtonBackground();
        String getNegativeButtonBackground();
        String getPositiveBtnTextColor();
        String getNegativeBtnTextColor();
    }
    interface Repository{
        String getTitle();
        String getGeneralTextColor();
        String getConfirmAccountQuestion();
        String getConfirmAccountButton();
        String getDeclineButtonTitle();
        String getPositiveButtonBackground();
        String getNegativeButtonBackground();
        String getPositiveBtnTextColor();
        String getNegativeBtnTextColor();
        String getAnalyticsID();
    }
    interface Service{
        void confirmAccount(ConfirmAccountDetailsMVP.Presenter presenter,String billerId,String paymentId);
    }
}
