package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.budgettracker.R;

public class RegisterActivity extends AppCompatActivity {

    private final int reqCodeRegisterActivity = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClickToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void inregistrateBtn(View view) {
        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        EditText etUser=findViewById(R.id.textViewUsername);
        EditText etPass=findViewById(R.id.textViewPassword);
        intent.putExtra("user",etUser.getText().toString());
        intent.putExtra("parola",etPass.getText().toString());
        startActivityForResult(intent, reqCodeRegisterActivity);
    }
}