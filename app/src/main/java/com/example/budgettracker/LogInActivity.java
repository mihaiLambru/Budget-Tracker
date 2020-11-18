package com.example.budgettracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private final int reqCodeRegisterActivity=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void inregistrateBtn(View view) {
        Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
        EditText etUser=findViewById(R.id.textViewUsername);
        EditText etPass=findViewById(R.id.textViewPassword);
        intent.putExtra("user",etUser.getText().toString());
        intent.putExtra("parola",etPass.getText().toString());
        startActivityForResult(intent, reqCodeRegisterActivity);
    }
}