package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Aashish Godambe on 10/24/17.
 */
@ContentType("billerCategoryIconItem")
public class BillerCategoryIcon extends Resource {

    @Field
    String categoryId;
    @Field
    Asset icon;

    public String getCategoryId() {
        return ActivityUtils.checkNull(categoryId);
    }

    public Asset getIcon() {
        return icon;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setIcon(Asset icon) {
        this.icon = icon;
    }
}
