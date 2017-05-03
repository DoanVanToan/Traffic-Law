package com.toandoan.luatgiaothong.data.source.local.realm;

/**
 *
 */

public interface BaseLocalDataSource {
    void openTransaction();

    void closeTransaction();
}
