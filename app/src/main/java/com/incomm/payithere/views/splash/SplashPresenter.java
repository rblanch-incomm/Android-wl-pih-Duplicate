package com.incomm.payithere.views.splash;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

import com.contentful.vault.Asset;
import com.incomm.payithere.managers.DocumentsManager;
import com.incomm.payithere.managers.PreferencesManager;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by Jerome Davis on 8/25/17.
 */

public class SplashPresenter implements SplashMVP.Presenter, DocumentsManager.DocumentSyncListener {

    private SplashMVP.View view;
    private List<Asset> imageList;
    private boolean isFirstLaunch;
    private DisplayImageOptions imageOptions;
    private ImageLoader imageManager = ImageLoader.getInstance();

    private int numOfImagesDownloaded = -1;
    private int numOfImages = 0;

    public SplashPresenter(SplashMVP.View view) {
        this.view = view;
        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();
        isFirstLaunch = PreferencesManager.getInstance().getIsFirstLaunch();
    }
    @Override
    public void getViewElements() {
        syncCms();
    }

    private void syncCms() {
        DocumentsManager.getInstance().syncCMSDocs(this);
    }

    @Override
    public void onSynchCompleted(boolean completed) {
        if (completed) {
            imageList = DocumentsManager.getInstance().getAssets();
            numOfImages = imageList.size();
            getImages();
        } else {
            handleIsFirstLaunch(isFirstLaunch);
        }
    }

    private void getImages() {
        numOfImagesDownloaded++;

        if (numOfImagesDownloaded < numOfImages) {
            downloadImage(numOfImagesDownloaded);
        } else {
            handleIsFirstLaunch(isFirstLaunch);
        }
    }
    private void downloadImage(final int imageNumber) {

        imageManager.loadImage(imageList.get(imageNumber).url(), imageOptions, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                handleIsFirstLaunch(isFirstLaunch);
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                getImages();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                handleIsFirstLaunch(isFirstLaunch);
            }
        });
    }

    private void handleIsFirstLaunch(boolean isFirstLaunch) {
        if (isFirstLaunch) {
            view.launchWalkthroughs();
        } else {
            view.launchLoginVIew();
        }
    }

    @Override
    public void getSplashBackgroundImage() {

    }

    @Override
    public void getSplashLogo() {

    }
}
