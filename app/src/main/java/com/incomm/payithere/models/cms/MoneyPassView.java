package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("moneyPassView")
public class MoneyPassView extends View {
    @Field
    Asset moneyPassIcon;
    @Field
    String bodyText;
    @Field
    String continueButtonTitle;
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
    String channel;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public Asset getMoneyPassIcon() {
        return moneyPassIcon;
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(bodyText);
    }

    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
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
}
