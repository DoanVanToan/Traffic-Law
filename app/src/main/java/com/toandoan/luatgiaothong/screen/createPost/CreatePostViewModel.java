package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

import static android.app.Activity.RESULT_OK;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType.IMAGE;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType.LOCATION;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType
        .TEXT_CONTENT;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType.VIDEO;

/**
 * Exposes the data to be used in the CreatePost screen.
 */

public class CreatePostViewModel implements CreatePostContract.ViewModel {
    public static final int PLACE_PICKER_REQUEST = 1;

    private CreatePostContract.Presenter mPresenter;

    @CreatePostActivity.CreateType
    private int mCreateType;
    private CreatePostActivity mActivity;
    private Navigator mNavigator;

    public CreatePostViewModel(CreatePostActivity activity, Navigator navigator,
            @CreatePostActivity.CreateType int createType) {
        mActivity = activity;
        mNavigator = navigator;
        mCreateType = createType;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        switch (mCreateType) {
            case LOCATION:
                onpenPlacePicker();
                break;
            case IMAGE:
                break;
            case TEXT_CONTENT:
                break;
            case VIDEO:
                break;
            default:
                break;
        }
    }

    private void onpenPlacePicker() {
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            mNavigator.startActivityForResult(builder.build(mActivity), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, mActivity);
                String toastMsg = String.format("Place: %s", place.getName());
                mNavigator.showToast(toastMsg);
            }
        }
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(CreatePostContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
