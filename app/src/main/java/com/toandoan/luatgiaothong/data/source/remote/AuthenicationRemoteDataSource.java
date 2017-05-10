package com.toandoan.luatgiaothong.data.source.remote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.AuthenicationDataSource;
import com.toandoan.luatgiaothong.data.source.remote.api.error.BaseException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by framgia on 10/05/2017.
 */

public class AuthenicationRemoteDataSource extends BaseRemoteDataSource
        implements AuthenicationDataSource.RemoteDataSource {
    public AuthenicationRemoteDataSource(FirebaseAuth firebaseAuth) {
        super(firebaseAuth);
    }

    @Override
    public Observable<FirebaseUser> register(String email, String password) {
        return Observable.just(mFirebaseAuth.createUserWithEmailAndPassword(email, password))
                .flatMap(new Func1<Task<AuthResult>, Observable<FirebaseUser>>() {
                    @Override
                    public Observable<FirebaseUser> call(Task<AuthResult> authResultTask) {
                        return getResponse(authResultTask);
                    }
                });
    }

    @Override
    public Observable<FirebaseUser> login(String email, String password) {
        return Observable.just(mFirebaseAuth.signInWithEmailAndPassword(email, password))
                .flatMap(new Func1<Task<AuthResult>, Observable<FirebaseUser>>() {
                    @Override
                    public Observable<FirebaseUser> call(Task<AuthResult> authResultTask) {
                        return getResponse(authResultTask);
                    }
                });
    }

    @Override
    public Observable<FirebaseUser> getCurrentUser() {
        return Observable.just(mFirebaseAuth.getCurrentUser());
    }

    public Observable<FirebaseUser> getResponse(Task<AuthResult> authResultTask) {
        if (authResultTask == null) return Observable.error(new NullPointerException());
        if (!authResultTask.isSuccessful()) {
            return Observable.error(new Error(authResultTask.getException().getMessage()));
        }
        return Observable.just(authResultTask.getResult().getUser());
    }
}
