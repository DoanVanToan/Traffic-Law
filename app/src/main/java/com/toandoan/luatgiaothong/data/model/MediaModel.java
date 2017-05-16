package com.toandoan.luatgiaothong.data.model;

import android.support.annotation.IntDef;
import com.google.gson.annotations.SerializedName;

import static com.toandoan.luatgiaothong.data.model.MediaModel.MediaType.IMAGE;
import static com.toandoan.luatgiaothong.data.model.MediaModel.MediaType.VIDEO;

/**
 * Created by framgia on 16/05/2017.
 */

public class MediaModel {
    @SerializedName("id")
    private String mId;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("type")
    @MediaType
    private int mType;

    @IntDef({ IMAGE, VIDEO })
    @interface MediaType {
        int IMAGE = 0;
        int VIDEO = 1;
        int MP4 = 3;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
