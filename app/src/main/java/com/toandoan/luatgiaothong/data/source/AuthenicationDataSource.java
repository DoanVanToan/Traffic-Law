package com.toandoan.luatgiaothong.data.source;

import com.toandoan.luatgiaothong.data.source.remote.api.response.GitHub;
import rx.Observable;

/**
 *
 */

public interface AuthenicationDataSource {
    Observable<GitHub> login(String login);
}
