package com.example.budgettracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Cont> listaTest = new ArrayList<>();
        Cont cont1 = new Cont("Cont1", 10000,"EUR");
        Cont cont2 = new Cont("Cont2", 10040,"RON");
        Cont cont3 = new Cont("Cont3", 13000, "USD");
        listaTest.add(cont1);
        listaTest.add(cont2);
        listaTest.add(cont3);

        Spinner spinner = findViewById(R.id.spinner);
        CustomAdapter customAdapter = new CustomAdapter(this, listaTest);

        spinner.setAdapter(customAdapter);
    }
}