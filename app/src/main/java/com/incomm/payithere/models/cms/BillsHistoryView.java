package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("billDashboardView")
public class BillsHistoryView extends View {

    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String pendingStatusText;
    @Field
    String pendingStatusColor;
    @Field
    String processedStatusText;
    @Field
    String processedStatusColor;
    @Field
    String reversedStatusText;
    @Field
    String reversedStatusColor;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getPendingStatusText() {
        return pendingStatusText;
    }

    public String getPendingStatusColor() {
        return pendingStatusColor;
    }

    public String getProcessedStatusText() {
        return processedStatusText;
    }

    public String getProcessedStatusColor() {
        return processedStatusColor;
    }

    public String getReversedStatusText() {
        return reversedStatusText;
    }

    public String getReversedStatusColor() {
        return reversedStatusColor;
    }
}
