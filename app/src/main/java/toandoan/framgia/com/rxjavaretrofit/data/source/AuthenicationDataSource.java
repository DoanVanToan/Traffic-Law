package toandoan.framgia.com.rxjavaretrofit.data.source;

import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.response.GitHub;

/**
 * Created by framgia on 18/04/2017.
 */

public interface AuthenicationDataSource {
    Observable<GitHub> login(String login);
}
