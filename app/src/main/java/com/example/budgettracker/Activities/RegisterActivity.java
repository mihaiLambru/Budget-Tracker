package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.budgettracker.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClickToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}