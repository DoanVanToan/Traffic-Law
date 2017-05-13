package com.toandoan.luatgiaothong.screen.editProfile;

import android.content.Intent;
import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface EditProfileContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onSelectImageClick();

        void onSaveUserClick();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onSaveUserSuccess();

        void onSaveUserFailed(String msg);

        void onEmptyUserName();

        void hideProgressDialog();

        void showProgressDialog();

        void onGetUserSuccess(FirebaseUser data);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void saveUser(String userName, Uri uri);
    }
}
