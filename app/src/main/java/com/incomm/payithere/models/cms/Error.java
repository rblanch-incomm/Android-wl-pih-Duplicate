package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("error")
public class Error extends Resource {
    @Field
    String name;
    @Field
    Integer errorCode;
    @Field
    String title;
    @Field
    String message;
    @Field
    String dismissButtonTitle;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public Integer getErrorCode() {
        return ActivityUtils.checkNull(errorCode);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getMessage() {
        return ActivityUtils.checkNull(message);
    }

    public String getDismissButtonTitle() {
        return ActivityUtils.checkNull(dismissButtonTitle);
    }
}
