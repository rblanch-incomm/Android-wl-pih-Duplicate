package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("linkOutView")
public class LinkOutView extends View {
    @Field
    Asset linkOutIcon;
    @Field
    String bodyText;
    @Field
    String continueButtonTitle;
    @Field
    String linkUrl;
    @Field
    String leavingAppMessage;
    @Field
    String positiveButtonTitle;
    @Field
    String negativeButtonTitle;
    @Field
    String name;
    @Field
    String title;
    @Field
    String subTitle;
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

    public Asset getLinkOutIcon() {
        return linkOutIcon;
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(bodyText);
    }

    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
    }

    public String getLinkUrl() {
        return ActivityUtils.checkNull(linkUrl);
    }

    public String getLeavingAppMessage() {
        return ActivityUtils.checkNull(leavingAppMessage);
    }

    public String getPositiveButtonTitle() {
        return ActivityUtils.checkNull(positiveButtonTitle);
    }

    public String getNegativeButtonTitle() {
        return ActivityUtils.checkNull(negativeButtonTitle);
    }

    public String getSubTitle(){ return ActivityUtils.checkNull(subTitle); }

    public String getLinkOutIconUrl(){
        return ActivityUtils.checkNull(linkOutIcon);
    }
}
