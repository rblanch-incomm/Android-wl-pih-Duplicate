package com.incomm.payithere.factories;

import com.incomm.payithere.managers.CMSViewManager;
import com.incomm.payithere.views.changepassword.ChangePasswordFragmentRoot;
import com.incomm.payithere.views.faqs.FaqQuestionAnswerFragment;
import com.incomm.payithere.views.faqs.FaqsFragmentRoot;
import com.incomm.payithere.views.forgotpassword.ForgotPasswordFragmentRoot;
import com.incomm.payithere.views.more.MoreFragmentRoot;

import java.util.HashMap;

/**
 * Created by agodambe on 9/8/2017.
 */

public class TitleFactory {

    private static HashMap<String,String> titles = new HashMap<>();
    private static TitleFactory instance;
    public TitleFactory() {
    }
    public static TitleFactory getInstance() {
        if(instance == null) {
            instance = new TitleFactory();
        }

        return instance;
    }

    public void putTitle(String key,String value){
        titles.put(key,value);
    }

    public String getTitle(String identifier) {
       String title = titles.get(identifier);
        if(null != title){
            return title;
        }else {
            return "";
        }
    }
}
