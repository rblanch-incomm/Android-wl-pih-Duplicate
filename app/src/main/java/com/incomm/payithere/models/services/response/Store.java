
package com.incomm.payithere.models.services.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("hours")
    @Expose
    private List<String> hours = null;
    @SerializedName("phones")
    @Expose
    private List<String> phones = null;
    @SerializedName("merchant")
    @Expose
    private Merchant merchant;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
