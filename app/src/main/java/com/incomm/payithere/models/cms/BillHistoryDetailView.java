package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

/**
 * Created by bjennings on 10/18/2017.
 */

@ContentType("billDetailsView")
public class BillHistoryDetailView extends View {
    @Field
    String usedAtLabel;
    @Field
    String usedOnLabel;
    @Field
    String forAccountLabel;
    @Field
    String viewPdfButtonLabel;
    @Field
    String newPaymentButtonLabel;
    @Field
    String title;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getUsedAtLabel() {
        return ActivityUtils.checkNull(usedAtLabel);
    }

    public String getUsedOnLabel() {
        return ActivityUtils.checkNull(usedOnLabel);
    }

    public String getAccountNumberLabel() {
        return ActivityUtils.checkNull(forAccountLabel);
    }

    public String getEreceiptButtonText() {
        return ActivityUtils.checkNull(viewPdfButtonLabel);
    }

    public String getNewPaymentButtonText() { return ActivityUtils.checkNull(newPaymentButtonLabel); }

    @Override
    public String getTitle() { return ActivityUtils.checkNull(title); }
}
