package com.toandoan.luatgiaothong.screen.forgotPassword;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.toandoan.luatgiaothong.BR;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ForgotPassword screen.
 */

public class ForgotPasswordViewModel extends BaseObservable
        implements ForgotPasswordContract.ViewModel {

    private ForgotPasswordContract.Presenter mPresenter;
    private String mEmail;
    private Navigator mNavigator;

    public ForgotPasswordViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResetPasswodClick() {
        mPresenter.resetPassword(mEmail);
    }

    @Override
    public void onBackClick() {
        mNavigator.finishActivity();
    }

    @Override
    public void onEmailEmpty() {
        mNavigator.showToast(R.string.empty_email);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
        notifyPropertyChanged(BR.email);
    }
}
