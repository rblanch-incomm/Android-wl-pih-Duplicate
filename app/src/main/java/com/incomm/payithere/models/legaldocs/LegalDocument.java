package com.incomm.payithere.models.legaldocs;

/**
 * Created by jayma on 4/4/2017.
 */

public class LegalDocument implements LegalDocumentInterface{
    private String title;
    private String name;

    public LegalDocument(String title, String name){
        this.title = title;
        this.name = name;
    }

    public String getTitle(){
        return title;
    }

    public String getName() { return name; }
}
