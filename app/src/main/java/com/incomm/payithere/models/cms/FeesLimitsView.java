package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("feesLimitsView")
public class FeesLimitsView extends View {

    @Field
    String feesSectionHeaderText;
    @Field
    List<FeesCategoryItem> feesCategories;
    @Field
    String limitsSectionHeaderText;
    @Field
    List<LimitsCategoryItem> limitsCategories;
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

    public String getFeesSectionHeaderText() {
        return ActivityUtils.checkNull(feesSectionHeaderText);
    }

    public List<FeesCategoryItem> getFeesCategories() {
        return feesCategories;
    }

    public String getLimitsSectionHeaderText() {
        return ActivityUtils.checkNull(limitsSectionHeaderText);
    }

    public List<LimitsCategoryItem> getLimitsCategories() {
        return limitsCategories;
    }
}
