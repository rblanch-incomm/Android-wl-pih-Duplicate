package com.incomm.payithere.views.billHistoryDetail;

import android.app.Activity;

import com.incomm.payithere.models.BillsHistoryItem;
import com.incomm.payithere.models.services.response.PaymentPostResponse;
import com.incomm.payithere.models.services.response.PaymentWithEreceipt;
import com.incomm.payithere.services.PaymentDetailService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by bjennings on 10/12/2017.
 */

public class BillHistoryDetailsPresenter implements BillHistoryDetailMVP.Presenter {
    private BillHistoryDetailMVP.View view;
    private BillsHistoryItem bill;
    private PaymentDetailService service;
    private BillHistoryDetailMVP.Repository repository;
    private NumberFormat numberFormat;

    public BillHistoryDetailsPresenter(BillHistoryDetailMVP.View view, BillsHistoryItem bill,
                                       PaymentDetailService service, BillHistoryDetailMVP.Repository repository) {
        this.view = view;
        this.bill = bill;
        this.service = service;
        this.repository = repository;
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public void getViewElements() {
        view.showProgressDialog();
        view.setupUi();
        service.getPaymentDetails(bill.getToken(), this);
    }

    public String getTitle() {
        return repository.getTitle();
    }

    @Override
    public void displayBillerName() {
        view.displayBillerName(bill.getBillerName());
    }

    @Override
    public void displayBillAmount() {
       // view.displayBillAmount(ActivityUtils.strCleanMoneyValueString(bill.getAmount()));
    }

    @Override
    public void displayBillCreated() {
        view.displayBillCreated(new SimpleDateFormat("MM/dd/yyyy").format(bill.getTimestamp()));
    }

    @Override
    public void displayLabels() {
        view.displayLabels(repository.getUsedAtLabel(),
                repository.getUsedOnLabel(),
                repository.getAccountNumberLabel(),
                repository.getEreceiptButtonText(),
                repository.getNewPaymentButtonText());
    }

    @Override
    public void paymentDetailSuccess(PaymentWithEreceipt details) {
        String[] usedTime = details.getEreceipt().getTimestamp().split(" ");

        view.displayBillAmount(numberFormat.format(Float.parseFloat(details.getAmount())));
        view.displayAmount(details.getEreceipt().getAmount());
        view.displayFee(details.getEreceipt().getFee());
        view.displayTotal(details.getEreceipt().getTotal());
        view.displayConfirmationNumber(details.getEreceipt().getConfirmationNumber().toString());
        view.displayAmountDesc(details.getEreceipt().getDescription());
        view.displayRetailerName(details.getAuthorizedAgent());
        view.displayRetailerAddress(details.getFormattedAuthorizedAgentAddress());
        view.displayUsedTime(String.format(Locale.US, "%s at %s %s", usedTime[0], usedTime[1], usedTime[2]));
        view.displayAccountNumber(details.getUserData().get("account_number"));

        view.hideProgressDialog();
    }

    @Override
    public void paymentDetailFailure(String error) {
        view.hideProgressDialog();
        view.showErrorDialog(error);
    }

    @Override
    public void ereceiptButtonClicked() {
        //TODO implement this
        view.showTodoDialog();
    }

    @Override
    public void newPaymentButtonClicked() {
        view.showProgressDialog();
        service.getPayAgain(bill.getBillerIdToken(), this);
    }

    @Override
    public void payAgainCallback(PaymentPostResponse paymentPostResponse) {
        view.hideProgressDialog();
        view.showCreateNewSlip(paymentPostResponse);
    }
    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }
}
