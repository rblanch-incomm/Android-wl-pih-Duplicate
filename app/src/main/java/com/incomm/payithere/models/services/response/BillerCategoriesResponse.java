
package com.incomm.payithere.models.services.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerCategoriesResponse {

    @SerializedName("categories")
    @Expose
    private List<BillerCategory> categories = null;

    public List<BillerCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<BillerCategory> categories) {
        this.categories = categories;
    }

}
