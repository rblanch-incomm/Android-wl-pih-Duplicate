package com.incomm.payithere.managers;

import com.incomm.payithere.models.cms.Error;

/**
 * Created by Jerome Davis on 9/4/17.
 */

public class ErrorDisplayManager {
    private static ErrorDisplayManager instance;

    private Error errors;

    public static ErrorDisplayManager getInstance() {
        if(instance == null) {
            instance = new ErrorDisplayManager();
        }

        return instance;
    }
    public Error getErrorMessage(String errorCode) {
        Error error = DocumentsManager.getInstance().getError(errorCode);
        return error;
    }
}
