package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 10/6/17.
 */
@ContentType("billDashboardNoHistory")
public class BillDashboardNoHistoryView extends View {

    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String bodyText;
    @Field
    String instructionsHeaderText;
    @Field
    String createSlipButtonTitle;
    @Field
    List<String> stepsText;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public String getInstructionsHeaderText() {
        return instructionsHeaderText;
    }

    public String getCreateSlipButtonTitle() {
        return createSlipButtonTitle;
    }

    public List<String> getStepsText() {
        return stepsText;
    }
}
