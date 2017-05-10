package com.toandoan.luatgiaothong.screen.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.toandoan.luatgiaothong.R;

public class MainActivity extends AppCompatActivity {

    public static Intent getInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
