package com.incomm.payithere.views.billsHistory;

import android.app.Activity;
import android.util.Log;

import com.incomm.payithere.models.BillsHistoryItem;
import com.incomm.payithere.models.services.response.BillerAccount;
import com.incomm.payithere.models.services.response.Payment;
import com.incomm.payithere.services.BillerHistoryService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.ViewPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by agodambe on 9/28/2017.
 */

public class BillerHistoryPresenter extends ViewPresenter implements BillerHistoryMVP.Presenter {

    private BillerHistoryMVP.View view;
    private BillerHistoryMVP.Repository repository;
    private BillerHistoryService service;

    public BillerHistoryPresenter(BillerHistoryMVP.View view, BillerHistoryMVP.Repository repository) {
        this.view = view;
        this.repository = repository;
        service = new BillerHistoryService();
    }

    @Override
    public void getViewElements() {
        view.setupUi();
    }

    @Override
    public void OnBillerHistoryCallError(String code) {

    }

    @Override
    public void OnBillerHistoryCallSuccess(List<BillerAccount> billsList) {
        ArrayList<BillsHistoryItem> billsItemList = new ArrayList<>();
        for (int i = 0; i < billsList.size(); i++) {
            BillerAccount account = billsList.get(i);
            List<Payment> payments = account.getPayments();
            for (Payment payment : payments) {
                if(payment.getStatus().equals("pending") || payment.getStatus().equals("processed") || payment.getStatus().equals("reversed")){
                    BillsHistoryItem item = new BillsHistoryItem();
                    item.setBillerName(account.getBiller().getName());
                    item.setStatus(payment.getStatus());
                    item.setAmount(payment.getAmount());
                    item.setToken(payment.getToken());
                    item.setBillerIdToken(account.getToken());// this is the token needed for pay again
                    if (payment.getStatus().equals("pending")) {
                        item.setTextColor(repository.getPendingTextColor());
                        item.setText(repository.getPendingText());
                    } else if (payment.getStatus().equals("processed")) {
                        item.setTextColor(repository.getProcessedTextColor());
                        item.setText(repository.getProcessedText());
                    } else if (payment.getStatus().equals("reversed")) {
                        item.setTextColor(repository.getReversedTextColor());
                        item.setText(repository.getReversedText());
                    }
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(payment.getTimestamp());
                        if (payment.getStatus().equals("pending")) {
                            date = ActivityUtils.determineExpirationDateBillerHistory(date);
                        }
                        item.setTimestamp(date);
                    }catch (ParseException e){
                        item.setTimestamp(new Date());
                    }
                    if (item.getTimestamp().after(new Date()) || !item.getStatus().equals("pending")) {
                        billsItemList.add(item);
                    }
                }

            }
        }
        Collections.sort(billsItemList, new Comparator<BillsHistoryItem>() {
            @Override
            public int compare(BillsHistoryItem b1, BillsHistoryItem b2) {
                return b2.getTimestamp().compareTo(b1.getTimestamp());
            }
        });
        if (billsItemList != null && billsItemList.size() > 0) {
            view.showBillsHistory(billsItemList);
        } else {
            view.showNoBillsHistory();
        }
    }

    public void getBillsList() {
        service.getBillerHistory(this);
    }

    public String getRightChevron() {
        String chevron = ActivityUtils.checkNull(repository.getRightChevron());
        return chevron;
    }

    public void getGlobalTextColor() {
        view.setGeneralTextColor(repository.getGeneralTextColor());
    }

    public void getThemePrimary() {
        view.setThemePrimary(repository.getThemePrimary());
    }

    public void getTitle() {
        String title = ActivityUtils.checkNull(repository.getTitle());
        view.displayTitle(title);
    }

    public String getAnalyticsId() {
        return repository.getAnalyticsID();
    }
}
