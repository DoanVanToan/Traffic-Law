package com.toandoan.luatgiaothong.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.remote.api.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.data.source.remote.api.response.GitHub;
import com.toandoan.luatgiaothong.data.source.remote.api.service.AppServiceClient;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private CompositeSubscription mSubscription = new CompositeSubscription();

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            mTextMessage.setText(R.string.title_home);
                            return true;
                        case R.id.navigation_dashboard:
                            mTextMessage.setText(R.string.title_dashboard);
                            return true;
                        case R.id.navigation_notifications:
                            mTextMessage.setText(R.string.title_notifications);
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onDestroy() {
        mSubscription.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        AuthenicationRemoteDataSource dataSource =
                new AuthenicationRemoteDataSource(AppServiceClient.getInstance());

        Subscription subscription = dataSource.login("doanvantoan")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GitHub>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTextMessage.setText(e.toString());
                    }

                    @Override
                    public void onNext(GitHub gitHub) {
                        mTextMessage.setText(gitHub.toString());
                    }
                });

        mSubscription.add(subscription);
    }
}
