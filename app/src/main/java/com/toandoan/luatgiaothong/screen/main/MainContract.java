package com.toandoan.luatgiaothong.screen.main;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetCurrentUserSuccess(FirebaseUser data);

        void onSignOutClick();

        void onSignOutSuccess();

        void onSignOutFailed(String msg);

        GoogleApiClient getGoogleApiCliennt();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void signOut();
    }
}
