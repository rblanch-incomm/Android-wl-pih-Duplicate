package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("colorScheme")
public class ColorScheme extends Resource {

    @Field
    String name;
    @Field
    String themePrimary;
    @Field
    String themeSecondary;
    @Field
    String generalText;
    @Field
    String positiveButton;
    @Field
    String positiveButtonTitle;
    @Field
    String negativeButton;
    @Field
    String negativeButtonTitle;

    public void setName(String name) {this.name = name;}

    public void setThemePrimary(String themePrimary) {this.themePrimary = themePrimary;}

    public void setThemeSecondary(String themeSecondary) {
        this.themeSecondary = themeSecondary;
    }

    public void setGeneralText(String generalText) {
        this.generalText = generalText;
    }

    public void setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
    }

    public void setPositiveButtonTitle(String positiveButtonTitle) {
        this.positiveButtonTitle = positiveButtonTitle;
    }

    public void setNegativeButton(String negativeButton) {
        this.negativeButton = negativeButton;
    }

    public void setNegativeButtonTitle(String negativeButtonTitle) {
        this.negativeButtonTitle = negativeButtonTitle;
    }

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getThemePrimary() {
        return ActivityUtils.checkColorNull(themePrimary);
    }

    public String getThemeSecondary() {
        return ActivityUtils.checkColorNull(themeSecondary);
    }

    public String getGeneralText() {
        return ActivityUtils.checkColorNull(generalText);
    }

    public String getPositiveButton() {
        return ActivityUtils.checkColorNull(positiveButton);
    }

    public String getPositiveButtonTitle() {
        return ActivityUtils.checkColorNull(positiveButtonTitle);
    }

    public String getNegativeButton() {
        return ActivityUtils.checkColorNull(negativeButton);
    }

    public String getNegativeButtonTitle() {
        return ActivityUtils.checkColorNull(negativeButtonTitle);
    }
}
