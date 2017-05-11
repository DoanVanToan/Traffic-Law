package com.toandoan.luatgiaothong.screen.main;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.AuthenicationRepository;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();

    private final MainContract.ViewModel mViewModel;
    private AuthenicationRepository mRepository;

    public MainPresenter(MainContract.ViewModel viewModel, AuthenicationRepository repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
        mRepository.getCurrentUser(new DataCallback<FirebaseUser>() {
            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.onGetCurrentUserSuccess(data);
            }

            @Override
            public void onGetDataFailed(String msg) {

            }
        });
    }

    @Override
    public void onStop() {
    }

    @Override
    public void signOut() {
        mRepository.signOut(new DataCallback() {
            @Override
            public void onGetDataSuccess(Object data) {
                mViewModel.onSignOutSuccess();
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.onSignOutFailed(msg);
            }
        });
    }
}
