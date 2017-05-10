package com.toandoan.luatgiaothong.screen.register;

import android.text.TextUtils;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.AuthenicationRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link RegisterActivity}), retrieves the data and updates
 * the UI as required.
 */
final class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.ViewModel mViewModel;
    private AuthenicationRepository mRepository;
    private CompositeSubscription mSubscription;

    public RegisterPresenter(RegisterContract.ViewModel viewModel,
            AuthenicationRepository repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mSubscription.clear();
    }

    @Override
    public void registerAccount(String email, String password, String passwordConfirm) {
        if (TextUtils.isEmpty(email)) {
            mViewModel.onEmptyEmail();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mViewModel.onEmptyPassword();
            return;
        }

        if (TextUtils.isEmpty(passwordConfirm)) {
            mViewModel.onEmptyPasswordConfirm();
            return;
        }

        if (!password.equals(passwordConfirm)) {
            mViewModel.onPasswordConfirmNotCorrect();
            return;
        }

        Subscription subscription = mRepository.register(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.showProgressDialog();
                    }
                })
                .subscribe(new Subscriber<FirebaseUser>() {
                    @Override
                    public void onCompleted() {
                        mViewModel.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewModel.onRegisterFailed(e.getMessage());
                        mViewModel.dismissDialog();
                    }

                    @Override
                    public void onNext(FirebaseUser firebaseUser) {
                        if (firebaseUser != null) {
                            mViewModel.onRegisterSuccess(firebaseUser);
                        } else {
                            mViewModel.onRegisterFailed(null);
                        }
                    }
                });

        mSubscription.add(subscription);
    }
}

