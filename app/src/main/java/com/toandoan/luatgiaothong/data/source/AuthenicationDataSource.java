package com.toandoan.luatgiaothong.data.source;

import com.google.firebase.auth.FirebaseUser;
import rx.Observable;

/**
 *
 */

public interface AuthenicationDataSource {
    interface RemoteDataSource {
        Observable<FirebaseUser> register(String email, String password);

        Observable<FirebaseUser> login(String email, String password);

        Observable<FirebaseUser> getCurrentUser();
    }
}
