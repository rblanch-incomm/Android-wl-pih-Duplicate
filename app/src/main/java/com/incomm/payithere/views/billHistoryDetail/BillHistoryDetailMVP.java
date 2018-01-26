package com.incomm.payithere.views.billHistoryDetail;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.services.response.PaymentPostResponse;
import com.incomm.payithere.models.services.response.PaymentWithEreceipt;

/**
 * Created by bjennings on 10/12/2017.
 */

public interface BillHistoryDetailMVP {
    interface View extends BaseView<Presenter> {
        void displayLabels(String usedAt, String usedOn, String account, String receiptButton, String newPaymentButton);
        void displayBillerName(String name);
        void displayBillAmount(String amount);
        void displayBillCreated(String date);
        void displayRetailerName(String name);
        void displayRetailerAddress(String address);
        void displayUsedTime(String time);
        void displayAccountNumber(String accountNumber);
        void showTodoDialog();
        void showCreateNewSlip(PaymentPostResponse paymentPostResponse);
        void showErrorDialog(String errorCode);
        void showProgressDialog();
        void hideProgressDialog();
        void displayAmount(String amount);
        void displayFee(String fee);
        void displayTotal(String total);
        void displayAmountDesc(String description);
        void displayConfirmationNumber(String number);
    }

    interface Presenter extends BasePresenter {
        void paymentDetailSuccess(PaymentWithEreceipt details);
        void paymentDetailFailure(String error);
        void ereceiptButtonClicked();
        void newPaymentButtonClicked();
        void displayBillerName();
        void displayBillAmount();
        void displayBillCreated();
        void displayLabels();
        void payAgainCallback(PaymentPostResponse paymentPostResponse);
    }

    interface Repository {
        String getUsedAtLabel();
        String getUsedOnLabel();
        String getAccountNumberLabel();
        String getEreceiptButtonText();
        String getNewPaymentButtonText();
        String getTitle();
        String getAnalyticsID();
    }
}
