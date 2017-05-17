package com.toandoan.luatgiaothong.screen.createPost;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.model.UserModel;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;
import com.toandoan.luatgiaothong.data.source.remote.timeline.TimelineRepository;

/**
 * Listens to user actions from the UI ({@link CreatePostActivity}), retrieves the data and updates
 * the UI as required.
 */
final class CreatePostPresenter implements CreatePostContract.Presenter {
    private static final String TAG = CreatePostPresenter.class.getName();

    private final CreatePostContract.ViewModel mViewModel;
    private AuthenicationRepository mAuthenicationRepository;
    private TimelineRepository mTimelineRepository;

    public CreatePostPresenter(CreatePostContract.ViewModel viewModel,
            AuthenicationRepository authenicationRepository,
            TimelineRepository timelineRepository) {
        mViewModel = viewModel;
        mAuthenicationRepository = authenicationRepository;
        mTimelineRepository = timelineRepository;
        getUser();
    }

    @Override
    public void getUser() {
        mAuthenicationRepository.getCurrentUser(new DataCallback<FirebaseUser>() {
            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.onGetCurrentUserSuccess(new UserModel(data));
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.onGetCurrentUserFailed(msg);
            }
        });
    }

    @Override
    public void createPost(TimelineModel timelineModel) {
        mTimelineRepository.createNewPost(timelineModel, new DataCallback() {
            @Override
            public void onGetDataSuccess(Object data) {
                mViewModel.onCreatePostSuccess();
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.onCreatePostFailed(msg);
            }
        });
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
