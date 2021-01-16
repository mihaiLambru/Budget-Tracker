package com.example.budgettracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.budgettracker.CustomObjects.Verifier;
import com.example.budgettracker.Database.AsyncOperations.AsyncWrapperUser;
import com.example.budgettracker.Database.Entities.User;
import com.example.budgettracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {
    private final String FIRST_START = "firstStart";
    private final String PREFS = "prefs";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;

    public static FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences pref = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean itIsFirstStart = pref.getBoolean(FIRST_START, true);
        if (itIsFirstStart) {
            showWelcomeActivity();
        }

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextUsername);
    }

    @Override
    protected void onStart() {
        super.onStart();
        launchMainActivity();
    }

    public void onClickToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClickRegister(View view) {
        createAccount();
    }

    private void launchMainActivity() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void createAccount() {
        boolean conditionsAreFulfilled = Verifier.verify(editTextEmail, editTextPassword, this);

        if (conditionsAreFulfilled) {
            final String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            final String name = editTextName.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final User user = new User(name, email);

                                new AsyncWrapperUser(getApplicationContext()).insert(user);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                final DatabaseReference myRef = database.getReference("lastIdUser");

                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Long value = dataSnapshot.getValue(Long.class);
                                        ++value;
                                        user.setIdUser((int) (long)value);

                                        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putInt("idUser", user.getIdUser());
                                        editor.apply();

                                        myRef.setValue(value);

                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference ref = database.getReference("Users");
                                        DatabaseReference userRef = ref.child("User " + user.getIdUser());
                                        userRef.setValue(user);

                                        Toasty.success(getApplicationContext(), "Inregistrarea a avut succes!",
                                                Toasty.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), EditareContActivity.class);
                                        intent.putExtra("flag", true);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError error) {
                                        // Failed to read value
                                        Log.w("TAG", "Failed to read value.", error.toException());
                                    }
                                });




                            } else {
                                Toasty.error(getApplicationContext(), "Exista deja un user identic!",
                                        Toasty.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void showWelcomeActivity() {
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FIRST_START, false);
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

}