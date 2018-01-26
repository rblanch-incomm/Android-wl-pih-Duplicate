package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.models.legaldocs.LegalDocument;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("importantInformationView")
public class ImportantInformationView extends ViewWithDocuments {
    @Field
    List<FeatureLegalDocument> legalDocuments;
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
        return legalDocuments;
    }

    public LegalDocument[] getLegalDocuments() {
        ArrayList<LegalDocument> ret = new ArrayList<>();

        for (FeatureLegalDocument l : legalDocuments) {
            ret.add(new LegalDocument(l.getTitle(), l.getName()));
        }

        return ret.toArray(new LegalDocument[0]);
    }
}
