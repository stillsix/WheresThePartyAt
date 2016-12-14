package com.stillsix.wheresthepartyat;

/**
 * Created by sean.gallagher on 12/13/16.
 */
public class DisplayLocationData {
    private String mName;
    private String mPronunication;
    private String mMainImageURL;
    private String mThumbnailImageURL;
    //private Attributes[] mAttributes;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPronunication() {
        return mPronunication;
    }

    public void setPronunication(String pronunication) {
        mPronunication = pronunication;
    }

    public String getMainImageURL() {
        return mMainImageURL;
    }

    public void setMainImageURL(String mainImageURL) {
        mMainImageURL = mainImageURL;
    }

    public String getThumbnailImageURL() {
        return mThumbnailImageURL;
    }

    public void setThumbnailImageURL(String thumbnailImageURL) {
        mThumbnailImageURL = thumbnailImageURL;
    }

    /*public Attributes[] getAttributes() {
        return mAttributes;
    }

    public void setAttributes(Attributes[] attributes) {
        mAttributes = attributes;
    }*/

}
