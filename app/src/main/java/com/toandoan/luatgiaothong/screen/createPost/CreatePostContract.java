package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Intent;
import com.toandoan.luatgiaothong.BasePresenter;
import com.toandoan.luatgiaothong.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CreatePostContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
