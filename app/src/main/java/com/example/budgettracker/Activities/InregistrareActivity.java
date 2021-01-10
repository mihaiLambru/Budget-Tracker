package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.budgettracker.Adapters.AdapterCategorie;
import com.example.budgettracker.CustomObjects.CategorieInregistrare;
import com.example.budgettracker.R;

import java.util.ArrayList;
import java.util.List;

public class InregistrareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        Spinner spinnerTipInregistrare = findViewById(R.id.spinnerTipInregistrare);
        AdapterCategorie adapterCategorie = new AdapterCategorie(this);
        spinnerTipInregistrare.setAdapter(adapterCategorie);

        final Spinner spinnerCategorie = findViewById(R.id.spinnerCategorie);
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