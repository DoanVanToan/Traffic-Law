package toandoan.framgia.com.rxjavaretrofit.data.source.local.realm;

/**
 *
 */

public interface BaseLocalDataSource {
    void openTransaction();

    void closeTransaction();
}
