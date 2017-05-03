package toandoan.framgia.com.rxjavaretrofit.data.source;

import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.response.GitHub;

/**
 *
 */

public interface AuthenicationDataSource {
    Observable<GitHub> login(String login);
}
