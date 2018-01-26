
package com.incomm.payithere.models.services.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoresResponse {

    @SerializedName("stores")
    @Expose
    private List<Store> stores = null;

    public List<Store> getStores() {
        return stores;
    }


}
