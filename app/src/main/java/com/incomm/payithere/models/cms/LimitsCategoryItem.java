package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("limitsCategoryItem")
public class LimitsCategoryItem extends Resource {

    @Field
    String name;
    @Field
    String category;
    @Field
    List<LimitItem> items;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getCategory() {
        return ActivityUtils.checkNull(category);
    }

    public List<LimitItem> getLimitItems() {
        return items;
    }
}
