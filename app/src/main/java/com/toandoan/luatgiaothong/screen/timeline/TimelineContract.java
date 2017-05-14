package com.toandoan.luatgiaothong.screen.timeline;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TimelineContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetUserSuccess(FirebaseUser data);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
