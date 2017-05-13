package com.toandoan.luatgiaothong.screen.timeline;

/**
 * Listens to user actions from the UI ({@link TimelineFragment}), retrieves the data and updates
 * the UI as required.
 */
final class TimelinePresenter implements TimelineContract.Presenter {
    private static final String TAG = TimelinePresenter.class.getName();

    private final TimelineContract.ViewModel mViewModel;

    public TimelinePresenter(TimelineContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
