package com.incomm.payithere.models.services.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class SignInRequest{
  @SerializedName("user")
  @Expose
  private User user;
  public void setUser(User user){
   this.user=user;
  }
  public User getUser(){
   return user;
  }
}