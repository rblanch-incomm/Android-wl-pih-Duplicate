package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Aashish Godambe on 10/24/17.
 */
@ContentType("createSlipEntryView")
public class CreatePaymentSlipView extends View {
    @Field
    String categoriesButtonText;
    @Field
    String searchBarPlaceholder;
    @Field
    List<TopBiller> topBillers;
    @Field
    String name;
    @Field
    String title;
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

    public String getCategoriesButtonText() {
        return ActivityUtils.checkNull(categoriesButtonText);
    }

    public String getSearchBarPlaceholder() {
        return ActivityUtils.checkNull(searchBarPlaceholder);
    }

    public List<TopBiller> getTopBillers() {
        return topBillers;
    }
}
