package com.incomm.payithere.models.services.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class ChangePasswordResponse {
  @SerializedName("user")
  @Expose
  private User user;
  public User getUser(){
   return user;
  }
}