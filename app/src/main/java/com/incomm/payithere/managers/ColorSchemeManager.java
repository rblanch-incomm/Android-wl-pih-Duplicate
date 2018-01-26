package com.incomm.payithere.managers;

import com.incomm.payithere.models.cms.ColorScheme;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by jayma on 4/6/2017.
 */

public class ColorSchemeManager {

    private static ColorSchemeManager instance;

    private ColorScheme colorScheme;

    public static ColorSchemeManager getInstance() {
        if(instance == null) {
            instance = new ColorSchemeManager();
        }

        return instance;
    }
    public ColorSchemeManager(){
        colorScheme = (ColorScheme) DocumentsManager.getInstance().getCmsResource(ColorScheme.class);
    }

    //all of the Getters reference the ViewConfig object

    public String getName() {
        return ActivityUtils.checkColorNull(colorScheme.getName());
    }

    public String getThemePrimary() {
        return ActivityUtils.checkColorNull(colorScheme.getThemePrimary());
    }

    public String getThemeSecondary() {
        return ActivityUtils.checkColorNull(colorScheme.getThemeSecondary());
    }

    public String getGeneralText() {
        return ActivityUtils.checkColorNull(colorScheme.getGeneralText());
    }

    public String getPositiveButton() {
        return ActivityUtils.checkColorNull(colorScheme.getPositiveButton());
    }

    public String getPositiveButtonTitle() {
        return ActivityUtils.checkColorNull(colorScheme.getPositiveButtonTitle());
    }

    public String getNegativeButton() {
        return ActivityUtils.checkColorNull(colorScheme.getNegativeButton());
    }

    public String getNegativeButtonTitle() {
        return ActivityUtils.checkColorNull(colorScheme.getNegativeButtonTitle());
    }

}
