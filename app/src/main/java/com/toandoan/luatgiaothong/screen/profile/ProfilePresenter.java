package com.toandoan.luatgiaothong.screen.profile;

/**
 * Listens to user actions from the UI ({@link ProfileFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ProfilePresenter implements ProfileContract.Presenter {
    private static final String TAG = ProfilePresenter.class.getName();

    private final ProfileContract.ViewModel mViewModel;

    public ProfilePresenter(ProfileContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
