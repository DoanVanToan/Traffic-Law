package com.toandoan.luatgiaothong.screen.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BR;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.model.UserModel;
import com.toandoan.luatgiaothong.screen.login.LoginActivity;
import com.toandoan.luatgiaothong.screen.main.MainActivity;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Profile screen.
 */

public class ProfileViewModel extends BaseObservable implements ProfileContract.ViewModel {

    private ProfileContract.Presenter mPresenter;
    private ObservableField<UserModel> mUser = new ObservableField<>();
    private boolean mIsEditing = true;
    private boolean mIsAllowChangePassword = true;
    private MainActivity mActivity;
    private Navigator mNavigator;

    public ProfileViewModel(MainActivity mainActivity, Navigator navigator) {
        mActivity = mainActivity;
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
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetUserSuccesss(UserModel data) {
        // TODO: 5/14/2017 later
        mUser.set(data);
    }

    @Override
    public void onChangeAvtClick() {
        // TODO: 15/05/2017  onChangeAvtClick
    }

    @Override
    public void onEditUserClick() {
        // TODO: 15/05/2017  onEditUserClick
    }

    @Override
    public void showChangePasswordDialog() {
        // TODO: 15/05/2017  showChangePasswordDialog
    }

    @Override
    public void onSignOutClick() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(mActivity).setTitle(R.string.action_logout)
                        .setMessage(R.string.msg_logout)
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mPresenter.signOut();
                                    }
                                });
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public GoogleApiClient getGoogleApiCliennt() {
        return mActivity.getGoogleApiClient();
    }

    @Override
    public void onSignOutSuccess() {
        mNavigator.startActivity(LoginActivity.getInstance(mActivity));
    }

    @Override
    public void onSignOutFailed(String msg) {
        mNavigator.showToast(msg);
    }

    @Bindable
    public String getUserName() {
        return mUser.get() != null ? mUser.get().getUserName() : "";
    }

    @Bindable
    public String getUserUrl() {
        return mUser.get() != null ? mUser.get().getPhotoUrl().toString() : "";
    }

    @Bindable
    public boolean isEditing() {
        return mIsEditing;
    }

    public void setEditing(boolean editing) {
        mIsEditing = editing;
        notifyPropertyChanged(BR.editing);
    }

    @Bindable
    public boolean isAllowChangePassword() {
        return mIsAllowChangePassword;
    }

    public void setAllowChangePassword(boolean allowChangePassword) {
        mIsAllowChangePassword = allowChangePassword;
        notifyPropertyChanged(BR.allowChangePassword);
    }

    public ObservableField<UserModel> getUser() {
        return mUser;
    }

    public void setUser(ObservableField<UserModel> user) {
        mUser = user;
    }
}
