package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("contactView")
public class HelpView extends View {

    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String mailSupportLabel;
    @Field
    String callSupportLabel;
    @Field
    String customerCarePhoneNumber;
    @Field
    String mailingAddress;
    @Field
    String deviceIdentifierLabel;
    @Field
    String callNumberMessage;
    @Field
    String positiveButtonTitle;
    @Field
    String negativeButtonTitle;
    @Field
    String callPositiveButtonTitle;
    @Field
    List<Feature> features;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getMailSupportLabel() { return ActivityUtils.checkNull(mailSupportLabel); }

    public String getCallSupportLabel() { return ActivityUtils.checkNull(callSupportLabel); }

    public String getCallNumberMessage() {
        return ActivityUtils.checkNull(callNumberMessage);
    }

    public String getPositiveButtonTitle() {
        return ActivityUtils.checkNull(positiveButtonTitle);
    }

    public String getNegativeButtonTitle() {
        return ActivityUtils.checkNull(negativeButtonTitle);
    }

    public String getCallPositiveButtonTitle() {
        return ActivityUtils.checkNull(callPositiveButtonTitle);
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

    public String getCustomerCarePhoneNumber() {
        return ActivityUtils.checkNull(customerCarePhoneNumber);
    }

    public String getMailingAddress() {
        return ActivityUtils.checkNull(mailingAddress);
    }

    public String getDeviceIdentifierLabel() {
        return ActivityUtils.checkNull(deviceIdentifierLabel);
    }

    public List<Feature> getFeatures() {
        return ActivityUtils.checkNull(features);
    }
}
