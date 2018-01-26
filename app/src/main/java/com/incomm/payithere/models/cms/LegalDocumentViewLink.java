package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("legalDocumentViewLink")
public class LegalDocumentViewLink extends LegalDocumentView {

    @Field
    String url;
    @Field
    String name;
    @Field
    String title;
    @Field
    String abbreviated;

    public String getAbbreviated() {
        return ActivityUtils.checkNull(abbreviated);
    }

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getUrl() {
        return ActivityUtils.checkNull(url);
    }
}
