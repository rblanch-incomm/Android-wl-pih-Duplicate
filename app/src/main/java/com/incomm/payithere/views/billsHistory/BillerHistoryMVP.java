package com.incomm.payithere.views.billsHistory;

import com.incomm.payithere.BasePresenter;
import com.incomm.payithere.BaseView;
import com.incomm.payithere.models.BillsHistoryItem;
import com.incomm.payithere.models.services.response.BillerAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aashish Godambe on 9/5/17.
 */

public interface BillerHistoryMVP {
    interface View extends BaseView<Presenter> {
        void displayTitle(String title);
        void showError(String error);
        void showBillsHistory(ArrayList<BillsHistoryItem> billsList);
        void showNoBillsHistory();
        void setGeneralTextColor(String generalTextColor);
        void onFragmentSelected();
        void onFragmentUnselected();
        void setThemePrimary(String themePrimary);
        void displayDetailFragment(BillsHistoryItem item);
    }

    interface Presenter extends BasePresenter {
        void OnBillerHistoryCallError(String code);
        void OnBillerHistoryCallSuccess(List<BillerAccount> billsList);
    }
    interface Repository{
        String getTitle();
        void getBillsList();
        String getRightChevron();
        String getGeneralTextColor();
        String getThemePrimary();
        String getPendingText();
        String getPendingTextColor();
        String getProcessedText();
        String getProcessedTextColor();
        String getReversedText();
        String getReversedTextColor();
        String getAnalyticsID();
    }
}
