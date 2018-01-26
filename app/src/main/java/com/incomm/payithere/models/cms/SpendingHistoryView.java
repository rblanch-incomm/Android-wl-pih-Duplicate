package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("spendingHistoryView")
public class SpendingHistoryView extends View {
    @Field
    String accountTitlePrimary;
    @Field
    String accountTitleSecondary;
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

    public String getAccountTitlePrimary() {
        return ActivityUtils.checkNull(accountTitlePrimary);
    }

    public String getAccountTitleSecondary() {
        return ActivityUtils.checkNull(accountTitleSecondary);
    }
}
