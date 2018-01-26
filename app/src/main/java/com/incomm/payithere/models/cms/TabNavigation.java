package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("tabNavigation")
public class TabNavigation extends Resource {
    @Field
    String name;
    @Field
    String channel;
    @Field
    List<Feature> tabs;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public List<Feature> getTabs() {
        return tabs;
    }
}
