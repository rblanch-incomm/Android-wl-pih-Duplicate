package com.incomm.payithere.managers;

import android.content.Context;
import android.util.Log;

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.Logger;
import com.contentful.vault.Asset;
import com.contentful.vault.Resource;
import com.contentful.vault.SyncCallback;
import com.contentful.vault.SyncConfig;
import com.contentful.vault.SyncResult;
import com.contentful.vault.Vault;
import com.incomm.payithere.BuildConfig;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.models.cms.CMSGlobalDocument;
import com.incomm.payithere.models.cms.Error;
import com.incomm.payithere.models.cms.Error$Fields;
import com.incomm.payithere.models.cms.FeatureLegalDocument;
import com.incomm.payithere.models.cms.FeatureLegalDocument$Fields;

import java.util.List;

/**
 * Created by Jerome Davis on 8/28/17.
 */

public class DocumentsManager extends SyncCallback {

    private static DocumentsManager instance = null;

    public static final String LOCALE = "en-US";
    public static final String CMS_CHANNEL_NAME = "Android";

    private Vault vault;
    private CDAClient mCdaClient;
    private Context mContext;

    private DocumentSyncListener syncCallbackListener;

    public interface DocumentSyncListener {
        void onSynchCompleted(boolean completed);
    }

    public static DocumentsManager getInstance() {
        if(instance == null) {
            instance = new DocumentsManager(PayItHereApplication.getContext());
        }

        return instance;
    }

    public DocumentsManager(Context context) {
        String spaceId = BuildConfig.CONTENTFUL_SPACE;
        String apiToken = BuildConfig.CONTENFUL_API;

        mContext = context;
        mCdaClient = CDAClient.builder()
                //prod
                .setSpace(spaceId)
                .setToken(apiToken) // PROD
                //.preview()
                .setLogLevel(Logger.Level.FULL)
                .setLogger(new Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("CONTENTFUL:", message);
                    }
                })
                .build();
        vault = Vault.with(mContext, CMSGlobalDocument.class);
    }

    public void syncCMSDocs(final DocumentSyncListener syncCallbackListener) {

        this.syncCallbackListener = syncCallbackListener;

        vault.requestSync(SyncConfig.builder()
                .setClient(mCdaClient)
                .setInvalidate(true)
                .build(), this);
    }

    @Override
    public void onResult(SyncResult result) {
        if (result.isSuccessful()) {
            syncCallbackListener.onSynchCompleted(true);
            Vault.cancel(this);
        } else {
            syncCallbackListener.onSynchCompleted(false);
        }
    }

    //todo refactor the calling method to use getCmsResourceWhereChannel()
//    public GettingStartedView getGettingStartedView() {
//        GettingStartedView view;
//        view = vault.fetch(GettingStartedView.class).where(GettingStartedView$Fields.CHANNEL + "=?", CMS_CHANNEL_NAME).first();
//        return view;
//    }
//
    public FeatureLegalDocument getFeatureLegalDocument(String name) {
        return vault.fetch(FeatureLegalDocument.class)
                .where(FeatureLegalDocument$Fields.NAME + "=?", name)
                .first();
    }
//
//    public FeatureSection getFeatureSection(String name) {
//        return vault.fetch(FeatureSection.class)
//                .where(FeatureSection$Fields.NAME + "=?", name)
//                .first();
//    }

    public Error getError(String errorCode) {
        Error error = vault.fetch(Error.class).where(Error$Fields.ERROR_CODE + "=?", errorCode).first();
        return error;
    }

    public Resource getCmsResource(Class<? extends Resource> cmsResourceClass) {
        return vault.fetch(cmsResourceClass).first();
    }

    public Resource getCmsResourceWhereChannel(Class<? extends Resource> cmsResourceClass, String channel) {
        return vault.fetch(cmsResourceClass).where(channel + "=?", CMS_CHANNEL_NAME).first();
    }

    public List<Asset> getAssets() {
        return vault.fetch(Asset.class).all();
    }
}