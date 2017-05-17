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
    private UserModel mCreatedUser;
    @SerializedName("created_at")
    private long mCreatedAt;
    @SerializedName("modified_at")
    private long mModifiedAt;
    @SerializedName("location")
    private LocationModel mLocation;
    @SerializedName("media")
    private List<MediaModel> mMediaModels;
    @SerializedName("comments")
    private List<Comment> mComments;
    @SerializedName("likes")
    private List<UserModel> mLikeUser;
    @SerializedName("dish_like")
    private List<UserModel> mDishLikeUser;
    @SerializedName("report")
    private List<UserModel> mReportUser;

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

    public UserModel getCreatedUser() {
        return mCreatedUser;
    }

    public void setCreatedUser(UserModel createdUser) {
        mCreatedUser = createdUser;
    }

    public long getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(long createdAt) {
        mCreatedAt = createdAt;
    }

    public long getModifiedAt() {
        return mModifiedAt;
    }

    public void setModifiedAt(long modifiedAt) {
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

    public List<UserModel> getLikeUser() {
        return mLikeUser;
    }

    public void setLikeUser(List<UserModel> likeUser) {
        mLikeUser = likeUser;
    }

    public List<UserModel> getDishLikeUser() {
        return mDishLikeUser;
    }

    public void setDishLikeUser(List<UserModel> dishLikeUser) {
        mDishLikeUser = dishLikeUser;
    }

    public List<UserModel> getReportUser() {
        return mReportUser;
    }

    public void setReportUser(List<UserModel> reportUser) {
        mReportUser = reportUser;
    }
}
