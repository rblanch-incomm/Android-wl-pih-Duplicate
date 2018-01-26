package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("appUpdate")
public class AppUpdate extends Resource {
    @Field
    String name;
    @Field
    String channel;
    @Field
    String currentVersion;
    @Field
    String minimumVersion;
    @Field
    String appStoreUrl;
    @Field
    String newVersionAvailableMessage;
    @Field
    String forceUpdateMessage;
    @Field
    String updateButtonTitle;
    @Field
    String declineUpdateButtonTitle;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getCurrentVersion() {
        return ActivityUtils.checkNull(currentVersion);
    }

    public String getMinimumVersion() {
        return ActivityUtils.checkNull(minimumVersion);
    }

    public String getAppStoreUrl() {
        return ActivityUtils.checkNull(appStoreUrl);
    }

    public String getNewVersionAvailableMessage() {
        return ActivityUtils.checkNull(newVersionAvailableMessage);
    }

    public String getForceUpdateMessage() {
        return ActivityUtils.checkNull(forceUpdateMessage);
    }

    public String getUpdateButtonTitle() {
        return ActivityUtils.checkNull(updateButtonTitle);
    }

    public String getDeclineUpdateButtonTitle() {
        return ActivityUtils.checkNull(declineUpdateButtonTitle);
    }
}
