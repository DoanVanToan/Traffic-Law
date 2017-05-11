package com.toandoan.luatgiaothong.screen.forgotPassword;

import android.text.TextUtils;

/**
 * Listens to user actions from the UI ({@link ForgotPasswordActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private static final String TAG = ForgotPasswordPresenter.class.getName();

    private final ForgotPasswordContract.ViewModel mViewModel;

    public ForgotPasswordPresenter(ForgotPasswordContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void resetPassword(String email) {
        if (TextUtils.isEmpty(email)){
            mViewModel.onEmailEmpty();
            return;
        }
    }
}
