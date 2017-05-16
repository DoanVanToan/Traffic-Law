package com.toandoan.luatgiaothong.screen.createPost;

/**
 * Listens to user actions from the UI ({@link CreatePostActivity}), retrieves the data and updates
 * the UI as required.
 */
final class CreatePostPresenter implements CreatePostContract.Presenter {
    private static final String TAG = CreatePostPresenter.class.getName();

    private final CreatePostContract.ViewModel mViewModel;

    public CreatePostPresenter(CreatePostContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
