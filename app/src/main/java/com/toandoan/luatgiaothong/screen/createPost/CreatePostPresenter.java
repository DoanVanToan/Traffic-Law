package com.toandoan.luatgiaothong.screen.createPost;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;

/**
 * Listens to user actions from the UI ({@link CreatePostActivity}), retrieves the data and updates
 * the UI as required.
 */
final class CreatePostPresenter implements CreatePostContract.Presenter {
    private static final String TAG = CreatePostPresenter.class.getName();

    private final CreatePostContract.ViewModel mViewModel;
    private AuthenicationRepository mAuthenicationRepository;

    public CreatePostPresenter(CreatePostContract.ViewModel viewModel,
            AuthenicationRepository authenicationRepository) {
        mViewModel = viewModel;
        mAuthenicationRepository = authenicationRepository;
        getUser();
    }

    @Override
    public void getUser() {
        mAuthenicationRepository.getCurrentUser(new DataCallback<FirebaseUser>() {
            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.onGetCurrentUserSuccess(data);
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.onGetCurrentUserFailed(msg);
            }
        });
    }

    @Override
    public void createPost(TimelineModel timelineModel) {

    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
