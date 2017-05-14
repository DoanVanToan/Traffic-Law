package com.toandoan.luatgiaothong.screen.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;
import com.toandoan.luatgiaothong.databinding.ActivityMainBinding;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

import static com.toandoan.luatgiaothong.screen.main.MainPagerAdapter.PROFILE;
import static com.toandoan.luatgiaothong.screen.main.MainPagerAdapter.TIME_LINE;

/**
 * Main Screen.
 */
public class MainActivity extends BaseActivity {
    private MainContract.ViewModel mViewModel;
    private TabLayout mTabLayout;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new MainViewModel(this, new Navigator(this));
        AuthenicationRepository repository =
                new AuthenicationRepository(new AuthenicationRemoteDataSource());
        MainContract.Presenter presenter = new MainPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
        mTabLayout = binding.tabLayout;
        initTabLayout();
        getSupportActionBar().hide();
    }

    private void initTabLayout() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mTabLayout.getTabAt(1).getIcon() == null) {
                    mTabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);
                }
                
                switch (tab.getPosition()) {
                    case TIME_LINE:
                        tab.setIcon(R.drawable.ic_timeline_selected);
                        break;
                    case PROFILE:
                        tab.setIcon(R.drawable.ic_profile_selected);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case TIME_LINE:
                        tab.setIcon(R.drawable.ic_timeline);
                        break;
                    case PROFILE:
                        tab.setIcon(R.drawable.ic_profile);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
