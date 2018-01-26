package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by jayma on 5/5/2017.
 */

@ContentType("appLinkOutView")
public class AppLinkOutView extends LinkOutView{

    //these two fields are distinct to AppLinkOutView
    @Field
    String appLink;
    @Field
    String openAppMessage;

    //The rest of the fields are copied from LinkOutView
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
    public String getAppLink() {
        return ActivityUtils.checkNull(appLink);
    }

    public String getOpenAppMessage() {
        return ActivityUtils.checkNull(openAppMessage);
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
        return linkOutIcon.url();
    }
}
