package com.toandoan.luatgiaothong.screen.forgotPassword;

import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ForgotPasswordContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onResetPasswodClick();
        
        void onBackClick();

        void onEmailEmpty();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void resetPassword(String email);
    }
}
