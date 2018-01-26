package com.incomm.payithere.repositories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.models.cms.BillDashboardNoHistoryView;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.views.billsNoHistory.NoBillerHistoryMVP;

import java.util.List;

/**
 * Created by Jerome Davis on 10/6/17.
 */

public class NoBillerHistoryCMSRepository implements NoBillerHistoryMVP.NoBillerHistoryRepository {

    private BillDashboardNoHistoryView cms;

    public NoBillerHistoryCMSRepository() {
        cms = CMSViewManager.getInstance().getBillDashboardNoHistoryView();
    }
    public String getName() {
        return ActivityUtils.checkNull(cms.getName());
    }

    public String getChannel() {
        return ActivityUtils.checkNull(cms.getChannel());
    }

    public String getTitle() {
        return ActivityUtils.checkNull(cms.getTitle());
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(cms.getBodyText());
    }

    public String getInstructionsHeaderText() {
        return ActivityUtils.checkNull(cms.getInstructionsHeaderText());
    }

    public String getCreateSlipButtonTitle() {
        return ActivityUtils.checkNull(cms.getCreateSlipButtonTitle());
    }

    public List<String> getStepsText() {
        return cms.getStepsText();
    }

    @Override
    public String getAnalyticsId() {
        return cms.getAnalyticsId();
    }
}
