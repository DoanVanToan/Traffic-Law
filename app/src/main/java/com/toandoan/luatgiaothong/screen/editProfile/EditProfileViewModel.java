package com.toandoan.luatgiaothong.screen.editProfile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BR;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.screen.main.MainActivity;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

import static android.app.Activity.RESULT_OK;

/**
 * Exposes the data to be used in the EditProfile screen.
 */

public class EditProfileViewModel extends BaseObservable implements EditProfileContract.ViewModel {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditProfileContract.Presenter mPresenter;
    private String mUserName;
    private Uri mPhotoUri;
    private String mUserEmail;
    private Navigator mNavigator;
    private Context mContext;
    private ProgressDialog mDialog;

    public EditProfileViewModel(Context context, Navigator navigator) {
        mContext = context;
        mNavigator = navigator;
        mDialog = new ProgressDialog(context);
        mDialog.setMessage(mContext.getString(R.string.msg_loading));
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
    public void setPresenter(EditProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSelectImageClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mNavigator.startActivityForResult(
                Intent.createChooser(intent, mContext.getString(R.string.title_upload)),
                PICK_IMAGE_REQUEST);
    }

    @Override
    public void onSaveUserClick() {
        mPresenter.saveUser(mUserName, mPhotoUri);
    }

    @Override
    public void onSaveUserSuccess() {
        mNavigator.startActivity(MainActivity.getInstance(mContext));
    }

    @Override
    public void onSaveUserFailed(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onEmptyUserName() {
        mNavigator.showToast(mContext.getString(R.string.empty_user_name));
    }

    @Override
    public void hideProgressDialog() {
        if (mDialog != null) mDialog.dismiss();
    }

    @Override
    public void showProgressDialog() {
        if (mDialog != null) mDialog.show();
    }

    @Override
    public void onGetUserSuccess(FirebaseUser data) {
        if (data != null) {
            setUserEmail(data.getEmail());
        }
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            setPhotoUri(data.getData());
        }
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public Uri getPhotoUri() {
        return mPhotoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        mPhotoUri = photoUri;
        notifyPropertyChanged(BR.photoUri);
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
