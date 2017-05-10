package com.toandoan.luatgiaothong.screen.login;

import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.AuthenicationRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
final class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.ViewModel mViewModel;
    private AuthenicationRepository mRepository;
    private CompositeSubscription mSubscription;

    public LoginPresenter(LoginContract.ViewModel viewModel, AuthenicationRepository repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        Subscription subscription = mRepository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FirebaseUser>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onGetCurrentUserError(e.getMessage());
                    }

                    @Override
                    public void onNext(FirebaseUser firebaseUser) {
                        if (firebaseUser != null) {
                            mViewModel.onGetUserSuccessful(firebaseUser);
                        }
                    }
                });

        mSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        mSubscription.clear();
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

        Subscription subscription = mRepository.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showDialog();
                    }
                })
                .subscribe(new Subscriber<FirebaseUser>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onLoginError(e.getMessage());
                        mViewModel.dismissDialog();
                    }

                    @Override
                    public void onNext(FirebaseUser firebaseUser) {
                        mViewModel.onGetUserSuccessful(firebaseUser);
                    }
                });

        mSubscription.add(subscription);
    }
}
