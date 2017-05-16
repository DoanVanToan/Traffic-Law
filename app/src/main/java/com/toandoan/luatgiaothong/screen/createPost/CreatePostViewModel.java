package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Intent;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;
import java.util.List;

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
    public static final int SELECT_IMAGE_REQUEST = 2;
    public static final int LIMIT_IMAGES = 10;

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
        getData();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
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

    private void selectImage() {
        Intent intent = new Intent(mActivity, AlbumSelectActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, LIMIT_IMAGES);
        mNavigator.startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }

    public void getData() {
        switch (mCreateType) {
            case LOCATION:
                onpenPlacePicker();
                break;
            case IMAGE:
                selectImage();
                break;
            case TEXT_CONTENT:
                break;
            case VIDEO:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case PLACE_PICKER_REQUEST:
                Place place = PlacePicker.getPlace(data, mActivity);
                String toastMsg = String.format("Place: %s", place.getName());
                mNavigator.showToast(toastMsg);
                break;
            case SELECT_IMAGE_REQUEST:
                List<Image> images =
                        data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);

                if (images != null) mNavigator.showToast(images.size() + "");
                break;
            default:
                break;
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
