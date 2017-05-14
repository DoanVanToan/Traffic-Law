package com.toandoan.luatgiaothong.screen.timeline;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;

/**
 * Listens to user actions from the UI ({@link TimelineFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TimelinePresenter implements TimelineContract.Presenter {
    private static final String TAG = TimelinePresenter.class.getName();

    private final TimelineContract.ViewModel mViewModel;
    private AuthenicationRepository mAuthenicationRepository;

    public TimelinePresenter(TimelineContract.ViewModel viewModel,
            AuthenicationRepository authenicationRepository) {
        mViewModel = viewModel;
        mAuthenicationRepository = authenicationRepository;
    }

    @Override
    public void onStart() {
        mAuthenicationRepository.getCurrentUser(new DataCallback<FirebaseUser>() {
            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.onGetUserSuccess(data);
            }

            @Override
            public void onGetDataFailed(String msg) {

            }
        });
    }

    @Override
    public void onStop() {
    }
}
