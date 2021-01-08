package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.budgettracker.Adapters.AdapterConturi;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.R;

import java.util.List;

public class ListaConturiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_conturi);

        Intent intent = getIntent();
        List<Cont> list = (List<Cont>) intent.getSerializableExtra("lista");

        AdapterConturi adapterConturi = new AdapterConturi(this, list);

        ListView listView = findViewById(R.id.listViewConturi);

        listView.setAdapter(adapterConturi);
    }
}