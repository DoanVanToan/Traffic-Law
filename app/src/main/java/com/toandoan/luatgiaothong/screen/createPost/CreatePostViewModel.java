package com.toandoan.luatgiaothong.screen.createPost;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.toandoan.luatgiaothong.BR;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.model.LocationModel;
import com.toandoan.luatgiaothong.data.model.MediaModel;
import com.toandoan.luatgiaothong.data.model.PostType;
import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.model.UserModel;
import com.toandoan.luatgiaothong.record.AndroidAudioRecorder;
import com.toandoan.luatgiaothong.record.Util;
import com.toandoan.luatgiaothong.record.model.AudioChannel;
import com.toandoan.luatgiaothong.record.model.AudioSampleRate;
import com.toandoan.luatgiaothong.record.model.AudioSource;
import com.toandoan.luatgiaothong.service.FirebaseUploadService;
import com.toandoan.luatgiaothong.utils.Utils;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static com.toandoan.luatgiaothong.data.model.PostType.IMAGE;
import static com.toandoan.luatgiaothong.data.model.PostType.LOCATION;
import static com.toandoan.luatgiaothong.data.model.PostType.VIDEO;
import static com.toandoan.luatgiaothong.service.BaseStorageService.POST_FOLDER;
import static com.toandoan.luatgiaothong.service.FirebaseUploadService.ACTION_UPLOAD_MULTI_FILE;
import static com.toandoan.luatgiaothong.service.FirebaseUploadService.EXTRA_FILES;
import static com.toandoan.luatgiaothong.service.FirebaseUploadService.EXTRA_FOLDER;
import static com.toandoan.luatgiaothong.service.FirebaseUploadService.EXTRA_MEDIA_MODEL;
import static com.toandoan.luatgiaothong.service.FirebaseUploadService.EXTRA_URI;

/**
 * Exposes the data to be used in the CreatePost screen.
 */

public class CreatePostViewModel extends BaseObservable implements CreatePostContract.ViewModel {
    public static final int PLACE_PICKER_REQUEST = 1;
    public static final int SELECT_IMAGE_REQUEST = 2;
    private static final int REQUEST_RECORD_AUDIO = 3;

    public static final int LIMIT_IMAGES = 10;

    private final static String[] PERMISSION = new String[] {
        Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final String TAG = "RecordingViewModel";

    private CreatePostContract.Presenter mPresenter;
    private UserModel mUser;
    private String mUserUrl;
    private String mUserName;
    private String mAddress;

    @PostType
    private int mCreateType;
    private CreatePostActivity mActivity;
    private Navigator mNavigator;

    private TimelineModel mTimelineModel;
    private BroadcastReceiver mReceiver;
    private boolean mIsUploading;

    public CreatePostViewModel(CreatePostActivity activity, Navigator navigator,
        @PostType int createType) {
        mActivity = activity;
        mNavigator = navigator;
        mCreateType = createType;
        mTimelineModel = new TimelineModel();
        getData();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case FirebaseUploadService.UPLOAD_PROGRESS:
                        int percent =
                            intent.getExtras().getInt(FirebaseUploadService.EXTRA_UPLOADED_PERCENT);
                        MediaModel mediaModel = intent.getExtras().getParcelable(EXTRA_MEDIA_MODEL);
                        mediaModel.setUploadPercent(percent);
                        handleProgress(mediaModel);
                        break;

                    case FirebaseUploadService.UPLOAD_COMPLETE:
                        mediaModel = intent.getExtras().getParcelable(EXTRA_MEDIA_MODEL);
                        Uri downloadUri = (Uri) intent.getExtras().get(EXTRA_URI);
                        mediaModel.setUrl(downloadUri.toString());
                        handleFinnish(mediaModel);
                        break;

                    case FirebaseUploadService.UPLOAD_FINNISH_ALL:
                        mIsUploading = false;
                        mActivity.hideBottomSheet();
                        mPresenter.createPost(mTimelineModel);
                        break;

                    case FirebaseUploadService.UPLOAD_ERROR:
                        mediaModel = intent.getExtras().getParcelable(EXTRA_MEDIA_MODEL);
                        mNavigator.showToast(
                            String.format(mActivity.getString(R.string.msg_upload_error),
                                mediaModel.getName()));
                        break;
                }
            }
        };

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(mActivity);
        manager.registerReceiver(mReceiver, FirebaseUploadService.getIntentFilter());
    }

    private void updateTimelineModel() {
        mTimelineModel.setCreatedUser(mUser);
        mTimelineModel.setCreatedAt(System.currentTimeMillis());
    }

    private void handleProgress(MediaModel mediaModel) {
        for (MediaModel model : mTimelineModel.getImages()) {
            if (model.getId().equals(mediaModel.getId())) {
                model.setUploadPercent(mediaModel.getUploadPercent());
                return;
            }
        }
    }

    private void handleFinnish(MediaModel mediaModel) {
        for (MediaModel model : mTimelineModel.getImages()) {
            if (model.getId().equals(mediaModel.getId())) {
                model.setUrl(mediaModel.getUrl());
                return;
            }
        }
    }

    private void openPlacePicker() {
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
                openPlacePicker();
                break;
            case IMAGE:
                selectImage();
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
                LocationModel locationModel = new LocationModel();
                locationModel.setAddress(place.getAddress().toString());
                locationModel.setLat(place.getLatLng().latitude);
                locationModel.setLng(place.getLatLng().longitude);
                setAddress(place.getAddress().toString());
                mTimelineModel.setLocation(locationModel);
                break;
            case SELECT_IMAGE_REQUEST:
                List<Image> images =
                    data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
                if (images == null || images.size() == 0) break;
                addPostImage(images);
                break;
            case REQUEST_RECORD_AUDIO:
                String filePath = Util.getResultFilePath(data);
                String fileName = Util.getResultFileName(data);
                MediaModel record = new MediaModel();
                record.setId(UUID.randomUUID().toString());
                record.setUrl(filePath);
                record.setName(fileName);

                addPostRecords(record);
                break;
            default:
                break;
        }
    }

    private void addPostRecords(MediaModel record) {
        if (record == null) {
            return;
        }
        if (mTimelineModel.getRecords() == null) {
            mTimelineModel.setRecords(new ArrayList<MediaModel>());
        }
        mTimelineModel.getRecords().add(record);
        mActivity.addPostRecord(mTimelineModel.getRecords());
    }

    private void addPostImage(List<Image> images) {
        if (images != null) {
            for (Image image : images) {
                MediaModel mediaModel = new MediaModel();
                mediaModel.setId(String.valueOf(image.id));
                mediaModel.setType(IMAGE);
                mediaModel.setUrl(image.path);
                mediaModel.setName(image.name);

                if (mTimelineModel.getImages() == null) {
                    mTimelineModel.setImages(new ArrayList<MediaModel>());
                }
                mTimelineModel.getImages().add(mediaModel);
            }
        }
        mActivity.addImagePost(mTimelineModel.getImages());
    }

    private void addPostRecord(List<MediaModel> records) {
        mActivity.addPostRecord(records);
    }

    @Override
    public void onGetCurrentUserSuccess(UserModel data) {
        mUser = data;
        setUserName(data.getUserName());
        setUserUrl(data.getPhotoUrl().toString());
    }

    @Override
    public void onGetCurrentUserFailed(String msg) {

    }

    @Override
    public void onImagePickerClick() {
        selectImage();
    }

    @Override
    public void onPlacePickerClick() {
        openPlacePicker();
    }

    @Override
    public void onCreatePost() {
        updateTimelineModel();
        if (mTimelineModel.getImages() != null && mTimelineModel.getImages().size() != 0) {
            uploadFiles(mTimelineModel.getImages());
        } else {
            mPresenter.createPost(mTimelineModel);
        }
    }

    @Override
    public void uploadFiles(List<MediaModel> mediaModels) {
        if (mIsUploading) return;
        mIsUploading = true;
        mActivity.startService(
            new Intent(mActivity, FirebaseUploadService.class).putParcelableArrayListExtra(
                EXTRA_FILES, (ArrayList<? extends Parcelable>) mediaModels)
                .putExtra(EXTRA_FOLDER, POST_FOLDER)
                .setAction(ACTION_UPLOAD_MULTI_FILE));

        mActivity.showUploadProgressView(mediaModels);
    }

    public void onStartRecordClicked() {
        if (Utils.isAllowPermision(mActivity, PERMISSION)) {
            recordAudio();
        }
    }

    public String getFileName() {
        String fileName = String.valueOf(System.currentTimeMillis()) + ".wav";
        return fileName;
    }

    public void recordAudio() {
        String fileName = getFileName();
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
        AndroidAudioRecorder.with(mActivity)
            // Required
            .setFileName(fileName)
            .setFilePath(filePath)
            .setColor(ContextCompat.getColor(mActivity, R.color.color_orange))
            .setRequestCode(REQUEST_RECORD_AUDIO)

            // Optional
            .setSource(AudioSource.MIC)
            .setChannel(AudioChannel.STEREO)
            .setSampleRate(AudioSampleRate.HZ_48000)
            .setAutoStart(false)
            .setKeepDisplayOn(true)

            // Start recording
            .record();
    }

    @Override
    public void onCreatePostSuccess() {
        mNavigator.finishActivity();
    }

    @Override
    public void onCreatePostFailed(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
        int[] grantResults) {
        if (requestCode == REQUEST_RECORD_AUDIO && isEnablePermision(permissions, grantResults)) {
            recordAudio();
        }
    }

    private boolean isEnablePermision(String[] permissions, int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(mReceiver);
    }

    @Override
    public void setPresenter(CreatePostContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getUserUrl() {
        return mUserUrl;
    }

    public void setUserUrl(String userUrl) {
        mUserUrl = userUrl;
        notifyPropertyChanged(BR.userUrl);
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public UserModel getUser() {
        return mUser;
    }

    public void setUser(UserModel user) {
        mUser = user;
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
        notifyPropertyChanged(BR.address);
    }

    public TimelineModel getTimelineModel() {
        return mTimelineModel;
    }

    public void setTimelineModel(TimelineModel timelineModel) {
        mTimelineModel = timelineModel;
    }
}
