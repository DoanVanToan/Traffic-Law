package com.toandoan.luatgiaothong.data.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 16/05/2017.
 */

public class Comment {
    private String mId;
    private String mContent;
    private String mCreatedAt;
    private String mModifiedAt;
    private FirebaseUser mCreateUser;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getModifiedAt() {
        return mModifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        mModifiedAt = modifiedAt;
    }

    public FirebaseUser getCreateUser() {
        return mCreateUser;
    }

    public void setCreateUser(FirebaseUser createUser) {
        mCreateUser = createUser;
    }
}
