package com.incomm.payithere.models.services.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class ChangePasswordRequest {
  @SerializedName("user")
  @Expose
  private ChangePassUser user;
  public void setUser(ChangePassUser user){
   this.user=user;
  }
  public ChangePassUser getUser() {
    return user;
  }

}