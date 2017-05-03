package toandoan.framgia.com.rxjavaretrofit;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import toandoan.framgia.com.rxjavaretrofit.data.source.local.realm.DataLocalMigration;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppServiceClient;

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
