package com.example.budgettracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.budgettracker.Adapters.CustomAdapter;
import com.example.budgettracker.CustomObjects.Cont;
import com.example.budgettracker.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int MENIU_STATISTICI = 0;
    private List<Cont> listaTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTest = new ArrayList<>();
        Cont cont1 = new Cont("Cont1", 10000,"EUR");
        Cont cont2 = new Cont("Cont2", 700,"RON");
        Cont cont3 = new Cont("Cont3", 8000, "USD");
        listaTest.add(cont1);
        listaTest.add(cont2);
        listaTest.add(cont3);

        Spinner spinner = findViewById(R.id.spinner);
        CustomAdapter customAdapter = new CustomAdapter(this, listaTest);

        spinner.setAdapter(customAdapter);
    }

    public void deschideTranzactia(View view) {
        startActivity(new Intent(this, TranzactieActivity.class));
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this,ListaConturiActivity.class);
        intent.putExtra("lista", (Serializable) listaTest);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENIU_STATISTICI, 0, "Statistici cont");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == MENIU_STATISTICI) {
            startActivity(new Intent(this, StatisticsActivity.class));
        }
        return true;
    }
}