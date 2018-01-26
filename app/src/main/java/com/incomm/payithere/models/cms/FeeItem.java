package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("feeItem")
public class FeeItem extends Resource {
    @Field
    String name;
    @Field
    String type;
    @Field
    String amount;
    @Field
    String description;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getType() {
        return ActivityUtils.checkNull(type);
    }

    public String getAmount() {
        return ActivityUtils.checkNull(amount);
    }

    public String getDescription() {
        return ActivityUtils.checkNull(description);
    }
}
