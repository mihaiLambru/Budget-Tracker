package com.example.budgettracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.budgettracker.Adapters.AdapterConturi;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MENIU_STATISTICI = 0;
    private static final int MENIU_SETARI_CONT = 1;
    private static final int MENIU_LOG_OUT = 2;
    private List<Cont> listaTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTest = new ArrayList<>();
        Cont cont1 = new Cont(1, "Cont1", 10000,"EUR", 1);
        Cont cont2 = new Cont(2, "Cont2", 1800,"USD", 1);
        Cont cont3 = new Cont(3, "Cont3", 120000,"RON", 1);;
        listaTest.add(cont1);
        listaTest.add(cont2);
        listaTest.add(cont3);



        Spinner spinner = findViewById(R.id.spinner);
        AdapterConturi adapterConturi = new AdapterConturi(this, listaTest);

        spinner.setAdapter(adapterConturi);
    }

    public void deschideTranzactia(View view) {
        startActivity(new Intent(this, InregistrareActivity.class));
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this,ListaConturiActivity.class);
        intent.putExtra("lista", (Serializable) listaTest);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENIU_STATISTICI, 0, "Statistici cont");
        menu.add(Menu.NONE, MENIU_SETARI_CONT, 1, "Setari cont");
        menu.add(Menu.NONE, MENIU_LOG_OUT, 2, "Logout");

        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            int colorId = this.getResources().getColor(R.color.colorCustomPrimary);
            spanString.setSpan(new ForegroundColorSpan(colorId), 0,     spanString.length(), 0);
            item.setTitle(spanString);
        }
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == MENIU_STATISTICI) {
            startActivity(new Intent(this, StatisticsActivity.class));
        }
        if (item.getItemId() == MENIU_SETARI_CONT) {
            //de revenit, in activitatea aia apelez update
            //si delete
            //startActivity(new Intent(this, ));
        }
        if (item.getItemId() == MENIU_LOG_OUT) {
            //DE REVENIT
        }
        return true;
    }
}