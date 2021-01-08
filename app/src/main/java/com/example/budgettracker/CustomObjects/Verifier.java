package com.example.budgettracker.CustomObjects;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;

import es.dmoral.toasty.Toasty;

public class Verifier {

    public static boolean verify(EditText emailEditText, EditText passwordEditText, Context context) {
        String textEmail = emailEditText.getText().toString().trim();
        String textPassword = passwordEditText.getText().toString().trim();

        if (textEmail.isEmpty() && textPassword.isEmpty()) {
            Toasty.info(context, "Please insert your email and password", Toasty.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return false;
        }
        else if (textEmail.isEmpty()) {
            Toasty.info(context, "Please insert your email", Toasty.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return false;
        }
        else if (textPassword.isEmpty()) {
            Toasty.info(context, "Please insert a password", Toasty.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return false;
        }

        if (passwordEditText.length() < 6) {
            Toasty.info(context, "The password should have more than 6 characters", Toasty.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
            Toasty.info(context, "Please enter a valid email", Toasty.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return false;
        }
        return true;
    }
}
