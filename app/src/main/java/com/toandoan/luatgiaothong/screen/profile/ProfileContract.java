package com.toandoan.luatgiaothong.screen.profile;

;import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProfileContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetUserSuccesss(FirebaseUser data);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
