package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.budgettracker.Adapters.CustomAdapter;
import com.example.budgettracker.Adapters.CustomAdapterCategorie;
import com.example.budgettracker.CustomObjects.CategorieTranzactie;
import com.example.budgettracker.R;

import java.util.ArrayList;
import java.util.List;

public class TranzactieActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranzactie);

        List<CategorieTranzactie> categorii = new ArrayList<>();
        CategorieTranzactie alimente = new CategorieTranzactie(CategorieTranzactie.Categorie.ALIMENTE,
                R.drawable.ic_alimente);
        CategorieTranzactie cumparaturi = new CategorieTranzactie(CategorieTranzactie.Categorie.CUMPARATURI,
                R.drawable.ic_cumparaturi);
        CategorieTranzactie locuinta = new CategorieTranzactie(CategorieTranzactie.Categorie.LOCUINTA,
                R.drawable.ic_locuinta);
        CategorieTranzactie transport = new CategorieTranzactie(CategorieTranzactie.Categorie.TRANSPORT,
                R.drawable.ic_transport);
        CategorieTranzactie vehicul = new CategorieTranzactie(CategorieTranzactie.Categorie.VEHICUL,
                R.drawable.ic_vehicul);
        CategorieTranzactie divertisment = new CategorieTranzactie(CategorieTranzactie.Categorie.DIVERTISMENT,
                R.drawable.ic_divertisment);


        categorii.add(alimente);
        categorii.add(cumparaturi);
        categorii.add(locuinta);
        categorii.add(transport);
        categorii.add(vehicul);
        categorii.add(divertisment);

        CustomAdapterCategorie customAdapterCategorie = new CustomAdapterCategorie(this, categorii);

        Spinner spinner = findViewById(R.id.spinnerCategorie);
        spinner.setAdapter(customAdapterCategorie);

    }
}