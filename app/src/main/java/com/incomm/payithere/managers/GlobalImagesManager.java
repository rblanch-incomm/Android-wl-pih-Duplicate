package com.incomm.payithere.managers;

import com.contentful.vault.Asset;
import com.incomm.payithere.models.cms.GlobalImages;

/**
 * Created by agodambe on 9/1/2017.
 */

public class GlobalImagesManager implements BaseDocumentsManager {

    private static GlobalImagesManager instance;

    public static GlobalImagesManager getInstance() {
        if(instance == null) {
            instance = new GlobalImagesManager();
        }

        return instance;
    }
    private GlobalImages globalImages;

    public GlobalImagesManager(){
        globalImages = (GlobalImages) DocumentsManager.getInstance().getCmsResource(GlobalImages.class);
    }

    public String getName() {
        return globalImages.getName();
    }

    public Asset getSplashImage() {
        return globalImages.getSplashImage();
    }

    public Asset getTitleLogo() {
        return globalImages.getTitleLogo();
    }

    public Asset getBackground() {
        return globalImages.getBackground();
    }


    public Asset getBackButton() {
        return globalImages.getBackButton();
    }

    public Asset getNavigationBar() {
        return globalImages.getNavigationBar();
    }

    public Asset getChevronRight() {
        return globalImages.getChevronRight();
    }

    public Asset getChevronRightSecondary() {
        return globalImages.getChevronRightSecondary();
    }

    public Asset getCallButton() {
        return globalImages.getCallButton();
    }

    public Asset getDirectionsButton() {
        return globalImages.getDirectionsButton();
    }


    @Override
    public void setup() {

    }
}


