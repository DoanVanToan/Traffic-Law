package com.toandoan.luatgiaothong.screen.forgotPassword;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.databinding.ActivityForgotPasswordBinding;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * ForgotPassword Screen.
 */
public class ForgotPasswordActivity extends BaseActivity {

    public static Intent getInstance(Context context) {
        return new Intent(context, ForgotPasswordActivity.class);
    }

    private ForgotPasswordContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ForgotPasswordViewModel(new Navigator(this));

        ForgotPasswordContract.Presenter presenter = new ForgotPasswordPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityForgotPasswordBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.setViewModel((ForgotPasswordViewModel) mViewModel);
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
}