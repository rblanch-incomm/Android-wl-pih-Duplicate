
package com.incomm.payithere.models.services.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.incomm.payithere.utils.ActivityUtils;

public class PaymentPost implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("accounts")
    @Expose
    private List<JsonObject> accounts = null;
    @SerializedName("user_data")
    @Expose
    private Map<String,String> userData;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("amount")
    @Expose
    private String  amount;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("next_step")
    @Expose
    private String nextStep;
    @SerializedName("biller")
    @Expose
    private BillerPost biller;
    @SerializedName("min_transaction_amount")
    @Expose
    private String minTransactionAmount;
    @SerializedName("max_transaction_amount")
    @Expose
    private String maxTransactionAmount;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("reminder")
    @Expose
    private String reminder;
    @SerializedName("biller_account_token")
    @Expose
    private String billerAccountToken;
    @SerializedName("validation_label")
    @Expose
    private String validationLabel;
    @SerializedName("validation_value")
    @Expose
    private String validationValue;
    @SerializedName("ask_user_for_amount")
    @Expose
    private Boolean askUserForAmount;
    @SerializedName("cashtie_account_id")
    @Expose
    private String cashtieAccountId;

    public String getToken() {
        return token;
    }

    public List<JsonObject> getAccounts() {
        return accounts;
    }

    public Map<String, String> getUserData() {
        return userData;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getAmount() {
        return amount;
    }

    public String getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public String getNextStep() {
        return nextStep;
    }

    public BillerPost getBiller() {
        return biller;
    }

    public String getMinTransactionAmount() {
        return minTransactionAmount;
    }

    public String getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getReminder() {
        return reminder;
    }

    public String getBillerAccountToken() {
        return billerAccountToken;
    }

    public String getValidationLabel() {
        return ActivityUtils.checkNull(validationLabel);
    }

    public String getValidationValue() {
        return ActivityUtils.checkNull(validationValue);
    }

    public Boolean getAskUserForAmount() {
        return askUserForAmount;
    }

    public String getCashtieAccountId() {
        return cashtieAccountId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeList(this.accounts);
        dest.writeInt(this.userData.size());
        for (Map.Entry<String, String> entry : this.userData.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeString(this.barcode);
        dest.writeString(this.amount);
        dest.writeString(this.fee);
        dest.writeString(this.status);
        dest.writeString(this.nextStep);
        dest.writeParcelable(this.biller, flags);
        dest.writeString(this.minTransactionAmount);
        dest.writeString(this.maxTransactionAmount);
        dest.writeString(this.timestamp);
        dest.writeString(this.reminder);
        dest.writeString(this.billerAccountToken);
        dest.writeString(this.validationLabel);
        dest.writeString(this.validationValue);
        dest.writeValue(this.askUserForAmount);
        dest.writeString(this.cashtieAccountId);
    }

    public PaymentPost() {
    }

    protected PaymentPost(Parcel in) {
        this.token = in.readString();
        this.accounts = new ArrayList<JsonObject>();
        in.readList(this.accounts, JsonObject.class.getClassLoader());
        int userDataSize = in.readInt();
        this.userData = new HashMap<String, String>(userDataSize);
        for (int i = 0; i < userDataSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.userData.put(key, value);
        }
        this.barcode = in.readString();
        this.amount = in.readString();
        this.fee = in.readString();
        this.status = in.readString();
        this.nextStep = in.readString();
        this.biller = in.readParcelable(BillerPost.class.getClassLoader());
        this.minTransactionAmount = in.readString();
        this.maxTransactionAmount = in.readString();
        this.timestamp = in.readString();
        this.reminder = in.readString();
        this.billerAccountToken = in.readString();
        this.validationLabel = in.readString();
        this.validationValue = in.readString();
        this.askUserForAmount = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.cashtieAccountId = in.readString();
    }

    public static final Parcelable.Creator<PaymentPost> CREATOR = new Parcelable.Creator<PaymentPost>() {
        @Override
        public PaymentPost createFromParcel(Parcel source) {
            return new PaymentPost(source);
        }

        @Override
        public PaymentPost[] newArray(int size) {
            return new PaymentPost[size];
        }
    };

    @Override
    public String toString() {
        return "PaymentPost{" +
                "token='" + token + '\'' +
                ", accounts=" + accounts +
                ", userData=" + userData +
                ", barcode='" + barcode + '\'' +
                ", amount='" + amount + '\'' +
                ", fee='" + fee + '\'' +
                ", status='" + status + '\'' +
                ", nextStep='" + nextStep + '\'' +
                ", biller=" + biller +
                ", minTransactionAmount='" + minTransactionAmount + '\'' +
                ", maxTransactionAmount='" + maxTransactionAmount + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", reminder='" + reminder + '\'' +
                ", billerAccountToken='" + billerAccountToken + '\'' +
                ", validationLabel='" + validationLabel + '\'' +
                ", validationValue='" + validationValue + '\'' +
                ", askUserForAmount=" + askUserForAmount +
                ", cashtieAccountId='" + cashtieAccountId + '\'' +
                '}';
    }
}
