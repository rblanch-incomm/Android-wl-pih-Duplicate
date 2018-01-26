package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
public abstract class LegalDocumentView extends Resource {

    @Field
    String name;
    @Field
    String title;
    @Field
    String abbreviated;
    @Field
    String copy;
    @Field
    Asset image;
    @Field
    String url;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getAbbreviated() {
        return ActivityUtils.checkNull(abbreviated);
    }

    public String getCopy() {
        return ActivityUtils.checkNull(copy);
    }

    public Asset getImage() {
        return image;
    }

    public String getUrl() {
        return ActivityUtils.checkNull(url);
    }
}
