package com.toandoan.luatgiaothong.screen.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BR;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.screen.editProfile.EditProfileActivity;
import com.toandoan.luatgiaothong.screen.forgotPassword.ForgotPasswordActivity;
import com.toandoan.luatgiaothong.screen.main.MainActivity;
import com.toandoan.luatgiaothong.screen.register.RegisterActivity;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Login screen.
 */

public class LoginViewModel extends BaseObservable implements LoginContract.ViewModel {

    private LoginContract.Presenter mPresenter;
    private Context mContext;
    private Navigator mNavigator;
    private ProgressDialog mDialog;

    private String mEmail;
    private String mPassword;

    public LoginViewModel(Context context, Navigator navigator) {
        mContext = context;
        mNavigator = navigator;
        mDialog = new ProgressDialog(context);
        mDialog.setMessage(context.getString(R.string.msg_loading));
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
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRegisterClick() {
        mNavigator.startActivity(RegisterActivity.getInstance(mContext));
    }

    @Override
    public void showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onGetCurrentUserError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onGetUserSuccessful(FirebaseUser firebaseUser) {
        mNavigator.finishActivity();
        if (!TextUtils.isEmpty(firebaseUser.getDisplayName())) {
            mNavigator.startActivity(MainActivity.getInstance(mContext));
        } else {
            mNavigator.startActivity(EditProfileActivity.getInstance(mContext));
        }
    }

    @Override
    public void onLoginError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onLoginClick() {
        mPresenter.login(mEmail, mPassword);
    }

    @Override
    public void onForgotPasswordClick() {
        mNavigator.startActivity(ForgotPasswordActivity.getInstance(mContext));
    }

    @Override
    public void onEmailEmpty() {
        mNavigator.showToast(R.string.empty_email);
    }

    @Override
    public void onPasswordEmpty() {
        mNavigator.showToast(R.string.empty_password);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(BR.password);
    }
}
