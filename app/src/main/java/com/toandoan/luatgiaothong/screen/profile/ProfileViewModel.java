package com.toandoan.luatgiaothong.screen.profile;

import com.google.firebase.auth.FirebaseUser;

/**
 * Exposes the data to be used in the Profile screen.
 */

public class ProfileViewModel implements ProfileContract.ViewModel {

    private ProfileContract.Presenter mPresenter;

    public ProfileViewModel() {
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
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetUserSuccesss(FirebaseUser data) {
        // TODO: 5/14/2017 later 
    }
}
