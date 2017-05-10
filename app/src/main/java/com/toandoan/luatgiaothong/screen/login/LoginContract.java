package com.toandoan.luatgiaothong.screen.login;

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

        void onGetUserError(String message);

        void onGetCurrentUserError(String message);

        void onGetUserSuccessful(FirebaseUser firebaseUser);

        void onLoginError(String message);

        void onLoginClick();

        void onEmailEmpty();

        void onPasswordEmpty();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void login(String email, String password);
    }
}
