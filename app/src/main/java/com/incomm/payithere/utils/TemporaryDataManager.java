package com.incomm.payithere.utils;

/**
 * Created by rblanch on 11/17/17.
 */

public class TemporaryDataManager {
    private static final TemporaryDataManager ourInstance = new TemporaryDataManager();
    private String email;
    private String firstName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = ActivityUtils.checkNull(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static TemporaryDataManager getInstance() {
        return ourInstance;
    }

    private TemporaryDataManager() {
    }
}
