package com.incomm.payithere.models.cms;

import android.os.Parcel;
import android.os.Parcelable;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("faqQuestionItem")
public class FAQQuestionItem extends Resource implements Parcelable {

    @Field
    String name;
    @Field
    String question;
    @Field
    String answer;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getQuestion() {
        return ActivityUtils.checkNull(question);
    }

    public String getAnswer() {
        return ActivityUtils.checkNull(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.question);
        dest.writeString(this.answer);
    }

    public FAQQuestionItem() {
    }

    protected FAQQuestionItem(Parcel in) {
        this.name = in.readString();
        this.question = in.readString();
        this.answer = in.readString();
    }

    public static final Parcelable.Creator<FAQQuestionItem> CREATOR = new Parcelable.Creator<FAQQuestionItem>() {
        @Override
        public FAQQuestionItem createFromParcel(Parcel source) {
            return new FAQQuestionItem(source);
        }

        @Override
        public FAQQuestionItem[] newArray(int size) {
            return new FAQQuestionItem[size];
        }
    };
}
