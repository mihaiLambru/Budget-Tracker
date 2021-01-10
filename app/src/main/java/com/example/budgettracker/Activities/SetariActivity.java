package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.budgettracker.Database.AsyncOperations.AsyncWrapperUser;
import com.example.budgettracker.R;

public class SetariActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setari);
    }

    public void onClickDeleteAccount(View view) {
        //new AsyncWrapperUser(this).delete(user);
    }

    public void onClickModifyAccount(View view) {

    }
}