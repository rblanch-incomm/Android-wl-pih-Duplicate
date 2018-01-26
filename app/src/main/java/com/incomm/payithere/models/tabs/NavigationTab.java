package com.incomm.payithere.models.tabs;

import android.support.v4.app.Fragment;

/**
 * Created by agodambe on 1/26/2017.
 */

public class NavigationTab {

    private String imagePrimary;
    private String imageSec;
    private String identifier;
    private String name;

    private transient Fragment tabFrag;
    private transient int position;

    public NavigationTab(String name, String imagePrimary, String imageSec, String identifier, int position) {
        this.imagePrimary = imagePrimary;
        this.imageSec = imageSec;
        this.identifier = identifier;
        this.name = name;
        this.position = position;
    }

    public Fragment getTabFrag() {
        return tabFrag;
    }

    public void setTabFrag(Fragment tabFrag) {
        this.tabFrag = tabFrag;
    }

    public String getImagePrimary() {
        return imagePrimary;
    }

    public void setImagePrimary(String imagePrimary) {
        this.imagePrimary = imagePrimary;
    }

    public String getImageSec() {
        return imageSec;
    }

    public void setImageSec(String imageSec) {
        this.imageSec = imageSec;
    }

    public int getPosition() {

        return position;

    }

    public void setPosition(int position) {
        this.position = position;
    }



    /*public int getImageSec() {
        return Integer.decode(imageSec);
    }

    public void setImageSec(int imageSec) {
        this.imageSec = Integer.toHexString(imageSec);
    }

    public int getImagePrimary() {
        return Integer.decode(imagePrimary);
    }

    public void setImagePrimary(int imagePrimary) {
        this.imagePrimary = Integer.toString(imagePrimary);
    }*/



    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

