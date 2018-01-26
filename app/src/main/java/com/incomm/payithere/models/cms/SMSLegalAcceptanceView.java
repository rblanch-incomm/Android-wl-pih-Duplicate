package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 5/1/17.
 */
@ContentType("smsLegalAcceptanceView")
public class SMSLegalAcceptanceView extends ViewWithDocuments {

    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String body;
    @Field
    String positiveButtonText;
    @Field
    String negativeButtonText;

    @Field
    List<FeatureLegalDocument> legalDocumentFeatures;

    @Override
    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    @Override
    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    @Override
    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getBody() {
        return ActivityUtils.checkNull(body);
    }

    public String getPositiveButtonText() {
        return ActivityUtils.checkNull(positiveButtonText);
    }

    public String getNegativeButtonText() {
        return ActivityUtils.checkNull(negativeButtonText);
    }

    @Override
    public List<FeatureLegalDocument> getLegalDocumentFeatures() {
        return legalDocumentFeatures;
    }
}
