package com.toandoan.luatgiaothong.screen.login;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface LoginContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onRegisterClick();

        void showDialog();

        void dismissDialog();

        void onGetCurrentUserError(String message);

        void onGetUserSuccessful(FirebaseUser firebaseUser);

        void onLoginError(String message);

        void onLoginClick();

        void onLoginGoogleClick();

        void onForgotPasswordClick();

        void onEmailEmpty();

        void onPasswordEmpty();

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void login(String email, String password);

        void login(GoogleSignInAccount account);
    }
}
