package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.budgettracker.R;

public class RegisterActivity extends AppCompatActivity {
    private final String FIRST_START = "firstStart";
    private final String NAME = "name";
    private final String PASS = "pass";
    private final String PREFS = "prefs";

    private TextView textViewUserName;
    private TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean itIsFirstStart = pref.getBoolean(FIRST_START, true);
        if (itIsFirstStart) {
            showWelcomeActivity();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //test
        //new AsyncWrapperUser(getApplicationContext()).insert(new User(2, "alex"));
        //new AsyncWrapperCont(getApplicationContext()).insert(new Cont("Cont1", 20.00, "RON", 2));

        textViewUserName = findViewById(R.id.editTextUsername);
        textViewPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClickRegister(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        CheckBox checkBoxRemember = findViewById(R.id.checkBoxRemember);
        if(checkBoxRemember.isChecked()) {
            editor.putString(NAME, textViewUserName.getText().toString());
            editor.putString(PASS, textViewPassword.getText().toString());
            editor.apply();
        }
        else {
            editor.putString(NAME, "");
            editor.putString(PASS, "");
            editor.apply();
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    public void showWelcomeActivity() {
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FIRST_START, false);
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), WelcomeActivityTest.class);
        startActivity(intent);
        finish();
    }
}