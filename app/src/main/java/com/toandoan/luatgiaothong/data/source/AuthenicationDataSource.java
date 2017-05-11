package com.toandoan.luatgiaothong.data.source;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 *
 */

public interface AuthenicationDataSource {
    interface RemoteDataSource {
        void register(String email, String password, DataCallback<FirebaseUser> callBack);

        void login(String email, String password, DataCallback<FirebaseUser> callBack);

        void getCurrentUser(DataCallback<FirebaseUser> callBack);
    }
}
