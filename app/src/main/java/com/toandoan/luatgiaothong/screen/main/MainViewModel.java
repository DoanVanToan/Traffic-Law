package com.toandoan.luatgiaothong.screen.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BR;
import com.toandoan.luatgiaothong.screen.login.LoginActivity;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainViewModel extends BaseObservable implements MainContract.ViewModel {

    private MainContract.Presenter mPresenter;
    private Navigator mNavigator;
    private Context mContext;

    private String mUserEmail;

    public MainViewModel(Context context, Navigator navigator) {
        mContext = context;
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
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetCurrentUserSuccess(FirebaseUser data) {
        setUserEmail(data.getEmail());
    }

    @Override
    public void onSignOutClick() {
        mPresenter.signOut();
    }

    @Override
    public void onSignOutSuccess() {
        mNavigator.startActivity(LoginActivity.getInstance(mContext));
    }

    @Override
    public void onSignOutFailed(String msg) {

    }

    @Bindable
    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
    }
}
