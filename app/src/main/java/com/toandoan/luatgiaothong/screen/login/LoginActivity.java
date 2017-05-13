package com.toandoan.luatgiaothong.screen.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.AuthenicationRepository;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.databinding.ActivityLoginBinding;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Login Screen.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LoginContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthenicationRepository repository = new AuthenicationRepository(
                new AuthenicationRemoteDataSource());

        mViewModel = new LoginViewModel(this, new Navigator(this));
        LoginContract.Presenter presenter = new LoginPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel((LoginViewModel) mViewModel);
        binding.signInButton.setOnClickListener(this);

        getSupportActionBar().hide();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                mViewModel.onLoginGoogleClick();
                break;
        }
    }
}
