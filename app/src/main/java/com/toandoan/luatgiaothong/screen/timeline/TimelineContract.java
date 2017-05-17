package com.toandoan.luatgiaothong.screen.timeline;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;
import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.model.UserModel;
import com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity;

/**
 * This specifies the contract between the view and the presenter.
 */
interface TimelineContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetUserSuccess(UserModel data);

        void onCreateNewPostClick(@CreatePostActivity.CreateType int createType);

        void onChildAdded(TimelineModel timeline);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
