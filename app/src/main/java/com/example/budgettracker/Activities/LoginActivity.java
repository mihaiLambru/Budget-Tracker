package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.budgettracker.R;

public class LoginActivity extends AppCompatActivity {
    private TextView textViewUserName;
    private TextView textViewPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewUserName = findViewById(R.id.editTextEmailLogin);
        textViewPassWord= findViewById(R.id.editTextPasswordLogin);
        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        String name = pref.getString("name", "");
        String pass = pref.getString("pass", "");
        textViewUserName.setText(name);
        textViewPassWord.setText(pass);
    }

    public void onClickLogin(View view) {
        CheckBox checkBox = findViewById(R.id.checkBox3);
        if(checkBox.isChecked()) {
            //de revenit
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onClickToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}