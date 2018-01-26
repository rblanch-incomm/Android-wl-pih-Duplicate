package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("legalDocumentViewCopy")
public class LegalDocumentViewCopy extends LegalDocumentView {

    @Field
    String copy;
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

    public String getCopy() {
        return ActivityUtils.checkNull(copy);
    }

}
