package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.budgettracker.Adapters.CustomAdapter;
import com.example.budgettracker.CustomObjects.Cont;
import com.example.budgettracker.R;

import java.util.List;

public class ListaConturiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_conturi);

        Intent intent = getIntent();
        List<Cont> list = (List<Cont>) intent.getSerializableExtra("lista");

        CustomAdapter customAdapter = new CustomAdapter(this, list);

        ListView listView = findViewById(R.id.listViewConturi);

        listView.setAdapter(customAdapter);
    }
}