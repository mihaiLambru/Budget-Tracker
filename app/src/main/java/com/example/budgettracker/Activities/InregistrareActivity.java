package com.example.budgettracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgettracker.Adapters.AdapterCategorie;
import com.example.budgettracker.CustomObjects.CategorieInregistrare;
import com.example.budgettracker.Database.AsyncOperations.AsyncWrapperCont;
import com.example.budgettracker.Database.AsyncOperations.AsyncWrapperInregistrare;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.Database.Entities.Inregistrare;
import com.example.budgettracker.Database.Entities.User;
import com.example.budgettracker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class InregistrareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        Intent intent = getIntent();
        final Cont cont = (Cont)intent.getExtras().getSerializable("cont");
        final int idCont = cont.getIdCont();

        final Spinner spinnerTipInregistrare = findViewById(R.id.spinnerTipInregistrare);
        AdapterCategorie adapterCategorie = new AdapterCategorie(this);
        spinnerTipInregistrare.setAdapter(adapterCategorie);

        final Spinner spinnerCategorie = findViewById(R.id.spinnerCategorie);

        final EditText editTextSuma = findViewById(R.id.editTextSuma);

        Button button = findViewById(R.id.buttonInregistrare);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = editTextSuma.getText().toString().trim();
                if (string.isEmpty()) {
                    Toasty.info(getApplicationContext(),"Vă rugăm introduceți o sumă", Toasty.LENGTH_SHORT).show();
                    return;
                }
                else if (Integer.parseInt(string) <= 0) {
                    Toasty.info(getApplicationContext(),"Vă rugăm introduceți o sumă pozitivă", Toasty.LENGTH_SHORT).show();
                    return;
                }
                final int suma = Integer.parseInt(string);

                TextView textView = spinnerTipInregistrare.getSelectedView().findViewById(R.id.textViewCategorie);
                String tipInregistrare = textView.getText().toString().toUpperCase();
                textView = spinnerCategorie.getSelectedView().findViewById(R.id.textViewCategorie);
                String categorie = textView.getText().toString().toUpperCase();

                SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
                final int idUser = pref.getInt("idUser", 0);

                if (tipInregistrare.equals("VENIT")) {
                    final Inregistrare inregistrare = new Inregistrare(suma, idCont,
                            Inregistrare.CategorieVenit.valueOf(categorie),
                            Inregistrare.TipInregistrare.valueOf(tipInregistrare));

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("lastIdInregistrare");

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Long value = dataSnapshot.getValue(Long.class);
                            ++value;
                            inregistrare.setIdInregistrare((int) (long)value);

                            myRef.setValue(value);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("Users");
                            DatabaseReference userRef = ref.child("User " + idUser);
                            DatabaseReference conturiRef = userRef.child("Conturi");
                            DatabaseReference contRef =conturiRef.child("Cont " + cont.getIdCont());
                            DatabaseReference inregistrariRef = contRef.child("Inregistrari");
                            DatabaseReference inregistrareRef = inregistrariRef.child("Inregistrare " + inregistrare.getIdInregistrare());
                            inregistrareRef.setValue(inregistrare);

                            double sumaCont  = cont.getSuma();
                            sumaCont += suma;
                            cont.setSuma(sumaCont);

                            DatabaseReference sumaRef = contRef.child("suma");
                            sumaRef.setValue(sumaCont);

                            new AsyncWrapperCont(getApplicationContext()).update(cont);
                            new AsyncWrapperInregistrare(getApplicationContext()).insert(inregistrare);

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("TAG", "Failed to read value.", error.toException());
                        }
                    });



                }

                else if (tipInregistrare.equals("CHELTUIALA")) {
                    final Inregistrare inregistrare = new Inregistrare(suma, idCont,
                            Inregistrare.CategorieCheltuiala.valueOf(categorie),
                            Inregistrare.TipInregistrare.valueOf(tipInregistrare));

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("lastIdInregistrare");

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Long value = dataSnapshot.getValue(Long.class);
                            ++value;
                            inregistrare.setIdInregistrare((int) (long)value);

                            myRef.setValue(value);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("Users");
                            DatabaseReference userRef = ref.child("User " + idUser);
                            DatabaseReference conturiRef = userRef.child("Conturi");
                            DatabaseReference contRef =conturiRef.child("Cont " + cont.getIdCont());
                            DatabaseReference inregistrariRef = contRef.child("Inregistrari");
                            DatabaseReference inregistrareRef = inregistrariRef.child("Inregistrare " + inregistrare.getIdInregistrare());
                            inregistrareRef.setValue(inregistrare);

                            Toasty.success(getApplicationContext(), "Inregistrarea a avut succes!",
                                    Toasty.LENGTH_SHORT).show();

                            double sumaCont  = cont.getSuma();
                            sumaCont -= suma;
                            cont.setSuma(sumaCont);

                            DatabaseReference sumaRef = contRef.child("suma");
                            sumaRef.setValue(sumaCont);


                            new AsyncWrapperCont(getApplicationContext()).update(cont);
                            new AsyncWrapperInregistrare(getApplicationContext()).insert(inregistrare);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }



                    });
                }


            }
        });

        spinnerTipInregistrare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                List<CategorieInregistrare> categorii = new ArrayList<>();

                if (i == 0) {
                    CategorieInregistrare salariu = new CategorieInregistrare(CategorieInregistrare.Categorie.SALARIU,
                            R.drawable.ic_salariu);
                    CategorieInregistrare chirii = new CategorieInregistrare(CategorieInregistrare.Categorie.CHIRII,
                            R.drawable.ic_chirie);
                    CategorieInregistrare proiecte = new CategorieInregistrare(CategorieInregistrare.Categorie.PROIECTE,
                            R.drawable.ic_proiect);
                    CategorieInregistrare dividende = new CategorieInregistrare(CategorieInregistrare.Categorie.DIVIDENDE,
                            R.drawable.ic_dividend);

                    categorii.add(salariu);
                    categorii.add(chirii);
                    categorii.add(proiecte);
                    categorii.add(dividende);

                    AdapterCategorie adapterCategorie = new AdapterCategorie(getApplicationContext(), categorii);
                    spinnerCategorie.setAdapter(adapterCategorie);

                }
                else if(i == 1) {
                    CategorieInregistrare alimente = new CategorieInregistrare(CategorieInregistrare.Categorie.ALIMENTE,
                            R.drawable.ic_alimente);
                    CategorieInregistrare cumparaturi = new CategorieInregistrare(CategorieInregistrare.Categorie.CUMPARATURI,
                            R.drawable.ic_cumparaturi);
                    CategorieInregistrare locuinta = new CategorieInregistrare(CategorieInregistrare.Categorie.LOCUINTA,
                            R.drawable.ic_locuinta);
                    CategorieInregistrare transport = new CategorieInregistrare(CategorieInregistrare.Categorie.TRANSPORT,
                            R.drawable.ic_transport);
                    CategorieInregistrare vehicul = new CategorieInregistrare(CategorieInregistrare.Categorie.VEHICUL,
                            R.drawable.ic_vehicul);
                    CategorieInregistrare divertisment = new CategorieInregistrare(CategorieInregistrare.Categorie.DIVERTISMENT,
                            R.drawable.ic_divertisment);

                    categorii.add(alimente);
                    categorii.add(cumparaturi);
                    categorii.add(locuinta);
                    categorii.add(transport);
                    categorii.add(vehicul);
                    categorii.add(divertisment);

                    AdapterCategorie adapterCategorie = new AdapterCategorie(getApplicationContext(), categorii);
                    spinnerCategorie.setAdapter(adapterCategorie);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}