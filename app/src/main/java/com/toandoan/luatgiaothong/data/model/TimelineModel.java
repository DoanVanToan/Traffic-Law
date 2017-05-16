package com.toandoan.luatgiaothong.data.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by framgia on 16/05/2017.
 */

public class TimelineModel {
    @SerializedName("id")
    private String mId;
    @SerializedName("content")
    private String mContent;
    @SerializedName("created_user")
    private FirebaseUser mCreatedUser;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("modified_at")
    private String mModifiedAt;
    @SerializedName("location")
    private LocationModel mLocation;
    @SerializedName("medias")
    private List<MediaModel> mMediaModels;
    @SerializedName("comments")
    private List<Comment> mComments;
    @SerializedName("likes")
    private List<FirebaseUser> mLikeUser;
    @SerializedName("dishlikes")
    private List<FirebaseUser> mDishLikeUser;
    @SerializedName("reports")
    private List<FirebaseUser> mReportUser;

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

    public FirebaseUser getCreatedUser() {
        return mCreatedUser;
    }

    public void setCreatedUser(FirebaseUser createdUser) {
        mCreatedUser = createdUser;
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

    public LocationModel getLocation() {
        return mLocation;
    }

    public void setLocation(LocationModel location) {
        mLocation = location;
    }

    public List<MediaModel> getMediaModels() {
        return mMediaModels;
    }

    public void setMediaModels(List<MediaModel> mediaModels) {
        mMediaModels = mediaModels;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public List<FirebaseUser> getLikeUser() {
        return mLikeUser;
    }

    public void setLikeUser(List<FirebaseUser> likeUser) {
        mLikeUser = likeUser;
    }

    public List<FirebaseUser> getDishLikeUser() {
        return mDishLikeUser;
    }

    public void setDishLikeUser(List<FirebaseUser> dishLikeUser) {
        mDishLikeUser = dishLikeUser;
    }

    public List<FirebaseUser> getReportUser() {
        return mReportUser;
    }

    public void setReportUser(List<FirebaseUser> reportUser) {
        mReportUser = reportUser;
    }
}
