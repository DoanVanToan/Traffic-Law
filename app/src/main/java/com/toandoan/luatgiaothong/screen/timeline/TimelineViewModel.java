package com.toandoan.luatgiaothong.screen.timeline;

/**
 * Exposes the data to be used in the Timeline screen.
 */

public class TimelineViewModel implements TimelineContract.ViewModel {

    private TimelineContract.Presenter mPresenter;

    public TimelineViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(TimelineContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
