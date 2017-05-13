package com.toandoan.luatgiaothong.data.source;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 *
 */

public interface AuthenicationDataSource {
    interface RemoteDataSource {
        void register(String email, String password, DataCallback<FirebaseUser> callBack);

        void signIn(String email, String password, DataCallback<FirebaseUser> callBack);

        void getCurrentUser(DataCallback<FirebaseUser> callBack);

        void signOut(DataCallback<FirebaseUser> callback);

        void resetPassword(String email, DataCallback callback);

        void updateProfile(String userName, Uri photo, DataCallback callback);
    }
}
