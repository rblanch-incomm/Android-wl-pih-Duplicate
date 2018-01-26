package com.incomm.payithere;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerome Davis on 8/25/17.
 */

public class PayItHereApplication extends Application {

    private static PayItHereApplication instance;

    private static String token,title = "";
    private static boolean isFromConfirmAcc = false;

    public static void setTitle(String title) {
        PayItHereApplication.title = title;
    }

    public static String getTitle() {
        return title;
    }

    public static boolean isIsFromConfirmAcc() {
        return isFromConfirmAcc;
    }

    public static void setIsFromConfirmAcc(boolean isFromConfirmAcc) {
        PayItHereApplication.isFromConfirmAcc = isFromConfirmAcc;
    }

    public static String getToken() {
        return token;
    }
    public static void setToken(String value) {
        token = value;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        configureImageLoader();
        System.out.println("Hey");
        System.out.println("Apple");
    }

  /*  @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }*/

    private void configureImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY -
                        2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(this)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static Context getContext() {
        return instance;
    }

}
