package com.toandoan.luatgiaothong.screen.login;

import android.text.TextUtils;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.AuthenicationRepository;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
final class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.ViewModel mViewModel;
    private AuthenicationRepository mRepository;

    public LoginPresenter(LoginContract.ViewModel viewModel, AuthenicationRepository repository) {
        mViewModel = viewModel;
        mRepository = repository;
    }

    @Override
    public void onStart() {
        mRepository.getCurrentUser(new DataCallback<FirebaseUser>() {
            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.onGetUserSuccessful(data);
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.onGetCurrentUserError(msg);
            }
        });

    }

    @Override
    public void onStop() {
    }

    @Override
    public void login(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            mViewModel.onEmailEmpty();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mViewModel.onPasswordEmpty();
            return;
        }

        mViewModel.showDialog();
        mRepository.signIn(email, password, new DataCallback<FirebaseUser>() {

            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.dismissDialog();
                mViewModel.onGetUserSuccessful(data);
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.dismissDialog();
                mViewModel.onLoginError(msg);
            }
        });
    }

    @Override
    public void login(GoogleSignInAccount account) {
        mViewModel.showDialog();
        mRepository.signIn(account, new DataCallback<FirebaseUser>() {
            @Override
            public void onGetDataSuccess(FirebaseUser data) {
                mViewModel.onGetUserSuccessful(data);
                mViewModel.dismissDialog();
            }

            @Override
            public void onGetDataFailed(String msg) {
                mViewModel.dismissDialog();
                mViewModel.onLoginError(msg);
            }
        });
    }
}
