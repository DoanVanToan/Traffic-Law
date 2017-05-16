package com.toandoan.luatgiaothong.screen.createPost;

/**
 * Exposes the data to be used in the CreatePost screen.
 */

public class CreatePostViewModel implements CreatePostContract.ViewModel {

    private CreatePostContract.Presenter mPresenter;

    public CreatePostViewModel() {
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
    public void setPresenter(CreatePostContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
