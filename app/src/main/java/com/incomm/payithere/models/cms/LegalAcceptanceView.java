package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("legalAcceptanceView")
public class LegalAcceptanceView extends ViewWithDocuments {

    @Field
    List<FeatureLegalDocument> legalDocumentFeatures;
    @Field
    String acceptanceToggleLabelText;
    @Field
    String acceptButtonTitle;
    @Field
    String declineAlertTitle;
    @Field
    String declineAlertMessage;
    @Field
    String declineAlertPositiveButtonTitle;
    @Field
    String declineAlertNegativeButtonTitle;
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

    public List<FeatureLegalDocument> getLegalDocumentFeatures() {
        return legalDocumentFeatures;
    }

    public String getAcceptanceToggleLabelText() {
        return ActivityUtils.checkNull(acceptanceToggleLabelText);
    }

    public String getAcceptButtonTitle() {
        return ActivityUtils.checkNull(acceptButtonTitle);
    }

    public String getDeclineAlertTitle() {
        return ActivityUtils.checkNull(declineAlertTitle);
    }

    public String getDeclineAlertMessage() {
        return ActivityUtils.checkNull(declineAlertMessage);
    }

    public String getDeclineAlertPositiveButtonTitle() {
        return ActivityUtils.checkNull(declineAlertPositiveButtonTitle);
    }

    public String getDeclineAlertNegativeButtonTitle() {
        return ActivityUtils.checkNull(declineAlertNegativeButtonTitle);
    }
}
