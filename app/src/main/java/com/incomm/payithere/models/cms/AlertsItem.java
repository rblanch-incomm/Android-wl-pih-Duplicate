package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("alertsItem")
public class AlertsItem extends Resource {

    @Field
    String name;
    @Field
    String title;
    @Field
    String key;

    boolean isAlertOn, isEmailAlertOn, isSmsAlertOn;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getKey() {
        return ActivityUtils.checkNull(key);
    }

    public void setEmailAlertOn(boolean isEmailAlertOn) {
        this.isEmailAlertOn = isEmailAlertOn;
    }

    public void setSmsAlertOn(boolean isSmsAlertOn) {
        this.isSmsAlertOn = isSmsAlertOn;
    }

    public boolean getEmailAlertOn() {
        return this.isEmailAlertOn;
    }

    public boolean getSmsAlertOn() {
        return this.isSmsAlertOn;
    }

    public boolean getAlertOn() {
        if (isEmailAlertOn || isSmsAlertOn) {
            isAlertOn = true;
        } else {
            isAlertOn = false;
        }
        return isAlertOn;
    }
}
