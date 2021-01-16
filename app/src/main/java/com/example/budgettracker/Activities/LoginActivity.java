package com.example.budgettracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.budgettracker.CustomObjects.Verifier;
import com.example.budgettracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextPassword= findViewById(R.id.editTextPasswordLogin);

        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        String name = pref.getString("name", "");
        String pass = pref.getString("pass", "");
        editTextEmail.setText(name);
        editTextPassword.setText(pass);
    }

    public void onClickLogin(View view) {
        boolean conditionsAreFulfilled = Verifier.verify(editTextEmail, editTextPassword, this);

        if (conditionsAreFulfilled) {
            CheckBox checkBox = findViewById(R.id.checkBox3);

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            if (checkBox.isChecked()) {
                editor.putString("name", editTextEmail.getText().toString());
                editor.putString("pass", editTextPassword.getText().toString());
                editor.apply();
            }
            else {
                editor.putString("name", "");
                editor.putString("pass", "");
                editor.apply();
            }

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            RegisterActivity.firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(getApplicationContext(), "Autentificare realizata cu succes",
                                        Toasty.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toasty.error(getApplicationContext(), "Authentication esuata.",
                                        Toasty.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    public void onClickToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}