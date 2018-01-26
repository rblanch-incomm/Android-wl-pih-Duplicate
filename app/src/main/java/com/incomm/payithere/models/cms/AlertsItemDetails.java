package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("alertsItemDetails")
public class AlertsItemDetails extends Resource {

    @Field
    String name;
    @Field
    String channel;
    @Field
    String emailLabel;
    @Field
    String smsLabel;
    @Field
    String thresholdLabel;
    @Field
    String aboveThresholdPlaceholderText;
    @Field
    String belowThresholdPlaceholderText;
    @Field
    Asset infoIcon;
    @Field
    String infoBody;
    @Field
    String saveButtonTitle;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getEmailLabel() {
        return ActivityUtils.checkNull(emailLabel);
    }

    public String getSmsLabel() {
        return ActivityUtils.checkNull(smsLabel);
    }

    public String getThresholdLabel() {
        return ActivityUtils.checkNull(thresholdLabel);
    }

    public String getAboveThresholdPlaceholderText() {
        return ActivityUtils.checkNull(aboveThresholdPlaceholderText);
    }

    public String getBelowThresholdPlaceholderText() {
        return ActivityUtils.checkNull(belowThresholdPlaceholderText);
    }

    public Asset getInfoIcon() {
        return infoIcon;
    }

    public String getInfoBody() {
        return ActivityUtils.checkNull(infoBody);
    }

    public String getSaveButtonTitle() {
        return ActivityUtils.checkNull(saveButtonTitle);
    }
}
