package com.toandoan.luatgiaothong.screen.timeline;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BR;

/**
 * Exposes the data to be used in the Timeline screen.
 */

public class TimelineViewModel extends BaseObservable implements TimelineContract.ViewModel {

    private TimelineContract.Presenter mPresenter;
    private ObservableField<FirebaseUser> mUser = new ObservableField<>();
    private String mUserUrl;

    public TimelineViewModel() {
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
    public void setPresenter(TimelineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetUserSuccess(FirebaseUser data) {
        mUser.set(data);
        setUserUrl(data.getPhotoUrl().toString());
    }

    public ObservableField<FirebaseUser> getUser() {
        return mUser;
    }

    public void setUser(ObservableField<FirebaseUser> user) {
        mUser = user;
    }

    @Bindable
    public String getUserUrl() {
        return mUserUrl;
    }

    public void setUserUrl(String userUrl) {
        mUserUrl = userUrl;
        notifyPropertyChanged(BR.userUrl);
    }
}
