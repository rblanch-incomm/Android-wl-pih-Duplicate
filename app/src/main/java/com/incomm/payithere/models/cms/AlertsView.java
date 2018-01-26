package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("alertsView")
public class AlertsView extends View {
    @Field
    String alertOnLabel;
    @Field
    String alertOffLabel;
    @Field
    List<AlertsItem> alertItems;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
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

    public String getAlertOnLabel() {
        return ActivityUtils.checkNull(alertOnLabel);
    }

    public String getAlertOffLabel() {
        return ActivityUtils.checkNull(alertOffLabel);
    }

    public List<AlertsItem> getAlertItems() {
        return alertItems;
    }
}
