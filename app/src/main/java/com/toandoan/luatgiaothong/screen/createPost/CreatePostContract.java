package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Intent;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;
import com.toandoan.luatgiaothong.data.model.MediaModel;
import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.model.UserModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CreatePostContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onGetCurrentUserSuccess(UserModel data);

        void onGetCurrentUserFailed(String msg);

        void onImagePickerClick();

        void onPlacePickerClick();

        void onCreatePost();

        void uploadFiles(List<MediaModel> mediaModels);

        void onCreatePostSuccess();

        void onCreatePostFailed(String msg);

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getUser();

        void createPost(TimelineModel timelineModel);
    }
}
