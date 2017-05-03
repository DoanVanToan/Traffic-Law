package com.toandoan.luatgiaothong;

import android.app.Application;
import com.toandoan.luatgiaothong.data.source.local.realm.DataLocalMigration;
import com.toandoan.luatgiaothong.data.source.remote.api.service.AppServiceClient;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 */

public class AppApplication extends Application {
    private static final String REALM_SCHEMA_NAME = "data.realm";
    private static final int REALM_SCHEMA_VERSION = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        AppServiceClient.initialize(this);
        initAndMigrateRealmIfNeeded();
    }

    private void initAndMigrateRealmIfNeeded() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name(REALM_SCHEMA_NAME)
                .schemaVersion(REALM_SCHEMA_VERSION)
                .migration(new DataLocalMigration())
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance(); // Automatically run migration if needed
        realm.close();
    }
}
