package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Intent;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CreatePostContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onGetCurrentUserSuccess(FirebaseUser data);

        void onGetCurrentUserFailed(String msg);

        void onImagePickerClick();

        void onPlacePickerClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getUser();
    }
}
