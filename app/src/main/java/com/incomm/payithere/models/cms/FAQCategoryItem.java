package com.incomm.payithere.models.cms;

import android.os.Parcel;
import android.os.Parcelable;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("faqCategoryItem")
public class FAQCategoryItem extends Resource implements Parcelable {

    @Field
    String name;
    @Field
    String category;
    @Field
    List<FAQQuestionItem> questions;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getCategory() {
        return ActivityUtils.checkNull(category);
    }

    public List<FAQQuestionItem> getQuestions() {
        return questions;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.questions);
        dest.writeString(this.category);
        dest.writeString(this.name);
    }

    public FAQCategoryItem() {
    }

    protected FAQCategoryItem(Parcel in) {
        this.questions = new ArrayList<FAQQuestionItem>();
        in.readList(this.questions, FAQQuestionItem.class.getClassLoader());
        this.category = in.readString();
        this.name = in.readString();
    }

    public static final Creator<FAQCategoryItem> CREATOR = new Creator<FAQCategoryItem>() {
        @Override
        public FAQCategoryItem createFromParcel(Parcel source) {
            return new FAQCategoryItem(source);
        }

        @Override
        public FAQCategoryItem[] newArray(int size) {
            return new FAQCategoryItem[size];
        }
    };
}
