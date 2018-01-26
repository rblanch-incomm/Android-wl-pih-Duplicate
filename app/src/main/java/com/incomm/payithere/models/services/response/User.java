package com.incomm.payithere.models.services.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class User{
  @SerializedName("zip")
  @Expose
  private String zip;
  @SerializedName("phone")
  @Expose
  private String phone;
  @SerializedName("last_name")
  @Expose
  private String last_name;
  @SerializedName("first_name")
  @Expose
  private String first_name;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("token")
  @Expose
  private String token;
  public void setZip(String zip){
   this.zip=zip;
  }
  public String getZip(){
   return zip;
  }
  public void setPhone(String phone){
   this.phone=phone;
  }
  public String getPhone(){
   return phone;
  }
  public void setLast_name(String last_name){
   this.last_name=last_name;
  }
  public String getLast_name(){
   return last_name;
  }
  public void setFirst_name(String first_name){
   this.first_name=first_name;
  }
  public String getFirst_name(){
   return first_name;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
  public void setToken(String token){
   this.token=token;
  }
  public String getToken(){
   return token;
  }
}