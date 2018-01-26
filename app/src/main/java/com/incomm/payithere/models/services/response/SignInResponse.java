package com.incomm.payithere.models.services.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class SignInResponse{
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