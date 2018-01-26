package com.incomm.payithere.models.cms;

import com.contentful.vault.Field;

import java.util.List;

/**
 * Created by Jerome Davis on 4/6/17.
 */

public class ViewWithDocuments extends View {
    @Field
    List<FeatureLegalDocument> legalDocumentFeatures;

    public List<FeatureLegalDocument> getLegalDocumentFeatures() {
        return legalDocumentFeatures;
    }
}
