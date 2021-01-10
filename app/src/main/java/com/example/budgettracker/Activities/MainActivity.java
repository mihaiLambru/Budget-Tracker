package com.example.budgettracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.budgettracker.Adapters.AdapterConturi;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.Database.Entities.Inregistrare;
import com.example.budgettracker.R;
import com.jjoe64.graphview.GraphView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

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

        Inregistrare inregistrare1 = new Inregistrare(1, 100, 1,
                Inregistrare.CategorieCheltuiala.ALIMENTE, Inregistrare.TipInregistrare.CHELTUIALA);
        Inregistrare inregistrare2 = new Inregistrare(2, 200, 1,
                Inregistrare.CategorieCheltuiala.CUMPARATURI, Inregistrare.TipInregistrare.CHELTUIALA);
        Inregistrare inregistrare3 = new Inregistrare(3, 300, 1,
                Inregistrare.CategorieCheltuiala.DIVERTISMENT, Inregistrare.TipInregistrare.CHELTUIALA);
        Inregistrare inregistrare4 = new Inregistrare(4, 700, 1,
                Inregistrare.CategorieCheltuiala.LOCUINTA, Inregistrare.TipInregistrare.CHELTUIALA);
        Inregistrare inregistrare5 = new Inregistrare(5, 500, 1,
                Inregistrare.CategorieCheltuiala.VEHICUL, Inregistrare.TipInregistrare.CHELTUIALA);
        Inregistrare inregistrare6 = new Inregistrare(6, 500, 1,
                Inregistrare.CategorieCheltuiala.TRANSPORT, Inregistrare.TipInregistrare.CHELTUIALA);
        //cu un for, de revenit
       /* int total = (int)(inregistrare1.getSuma() + inregistrare2.getSuma() + inregistrare3.getSuma());
        int percentage1 = (int)inregistrare1.getSuma() * 100 / total;
        int percentage2 = (int)inregistrare2.getSuma() * 100 / total;
        int percentage3 = (int)inregistrare3.getSuma() * 100 / total;*/

        float total = inregistrare1.getSuma() + inregistrare2.getSuma() + inregistrare3.getSuma()
                +inregistrare4.getSuma() + inregistrare5.getSuma() + inregistrare6.getSuma();
        float percentage1 = inregistrare1.getSuma() * 100 / total;
        float percentage2 = inregistrare2.getSuma() * 100 / total;
        float percentage3 = inregistrare3.getSuma() * 100 / total;
        float percentage4 = inregistrare4.getSuma() * 100 / total;
        float percentage5 = inregistrare5.getSuma() * 100 / total;
        float percentage6 = inregistrare6.getSuma() * 100 / total;

        PieChart pieCheltuieli  = findViewById(R.id.pieChartCheltuieli);

        pieCheltuieli .addPieSlice(
                new PieModel(
                        "Alimente",
                        percentage1,
                        getResources().getColor(R.color.color_alimente)
                        ));
        pieCheltuieli .addPieSlice(
                new PieModel(
                        "Cumparaturi",
                        percentage2,
                        getResources().getColor(R.color.color_cumparaturi)
                ));
        pieCheltuieli .addPieSlice(
                new PieModel(
                        "Divertisment",
                        percentage3,
                        getResources().getColor(R.color.color_divertisment)
                ));
        pieCheltuieli .addPieSlice(
                new PieModel(
                        "Locuinta",
                        percentage4,
                        getResources().getColor(R.color.color_locuinta)
                ));
        pieCheltuieli .addPieSlice(
                new PieModel(
                        "Vehicul",
                        percentage5,
                        getResources().getColor(R.color.color_vehicul)
                ));
        pieCheltuieli .addPieSlice(
                new PieModel(
                        "Transport",
                        percentage6,
                        getResources().getColor(R.color.color_transport)
                ));

        LinearLayout linearLayoutContents = findViewById(R.id.linearLayoutContentsCheltuieli);

        for (int i = 0; i < 10; ++i) {
            if (i == 6) {
                linearLayoutContents = findViewById(R.id.linearLayoutContentsVenituri);
            }
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(400,50));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            View view = new View(this);
            GradientDrawable drawable = (GradientDrawable)getDrawable(R.drawable.circle);
            if (i == 0)
                drawable.setColor(getResources().getColor(R.color.color_alimente));
            else if (i == 1)
                drawable.setColor(getResources().getColor(R.color.color_cumparaturi));
            else if (i == 2)
                drawable.setColor(getResources().getColor(R.color.color_divertisment));
            else if (i == 3)
                drawable.setColor(getResources().getColor(R.color.color_locuinta));
            else if (i == 4)
                drawable.setColor(getResources().getColor(R.color.color_vehicul));
            else if (i == 5)
                drawable.setColor(getResources().getColor(R.color.color_transport));
            else if (i == 6)
                drawable.setColor(getResources().getColor(R.color.color_salariu));
            else if (i == 7)
                drawable.setColor(getResources().getColor(R.color.color_chirie));
            else if (i == 8)
                drawable.setColor(getResources().getColor(R.color.color_proiecte));
            else if (i == 9)
                drawable.setColor(getResources().getColor(R.color.color_dividend));

            view.setBackground(drawable);
            view.setLayoutParams(new LinearLayout.LayoutParams(40,40));

            TextView textView = new TextView(this);
            if (i == 0)
                textView.setText("Alimente");
            else if (i == 1)
                textView.setText("Cumparaturi");
            else if (i == 2)
                textView.setText("Divertisment");
            else if (i == 3)
                textView.setText("Locuinta");
            else if (i == 4)
                textView.setText("Vehicul");
            else if (i == 5)
                textView.setText("Transport");
            else if (i == 6)
                textView.setText("Salarii");
            else if (i == 7)
                textView.setText("Chirii");
            else if (i == 8)
                textView.setText("Proiecte");
            else if (i == 9)
                textView.setText("Dividende");

            textView.setTextColor(getResources().getColor(R.color.black));

            textView.setLayoutParams(new LinearLayout.LayoutParams(300, 50));
            textView.setPadding(10, -7, 0, 0);

            linearLayout.addView(view);
            linearLayout.addView(textView);

            linearLayoutContents.addView(linearLayout);
        }

        TextView textViewCheltuieliPie = findViewById(R.id.textViewPieCheltuieli);
        textViewCheltuieliPie.setText("Total cheltuieli: " + (int) total + " RON");


        Inregistrare inregistrare7 = new Inregistrare(7, 100, 1,
                Inregistrare.CategorieVenit.SALARIU, Inregistrare.TipInregistrare.VENIT);
        Inregistrare inregistrare8 = new Inregistrare(8, 200, 1,
                Inregistrare.CategorieVenit.CHIRII, Inregistrare.TipInregistrare.VENIT);
        Inregistrare inregistrare9 = new Inregistrare(9, 300, 1,
                Inregistrare.CategorieVenit.PROIECTE, Inregistrare.TipInregistrare.VENIT);
        Inregistrare inregistrare10 = new Inregistrare(10, 325, 1,
                Inregistrare.CategorieVenit.DIVIDENDE, Inregistrare.TipInregistrare.VENIT);

        float total2 = inregistrare7.getSuma() + inregistrare8.getSuma() + inregistrare9.getSuma()
                + inregistrare10.getSuma();
        float percentage7 = inregistrare7.getSuma() * 100 / total2;
        float percentage8 = inregistrare8.getSuma() * 100 / total2;
        float percentage9 = inregistrare9.getSuma() * 100 / total2;
        float percentage10 = inregistrare10.getSuma() * 100 / total2;

        PieChart pieVenituri  = findViewById(R.id.pieChartVenituri);

        pieVenituri.addPieSlice(
                new PieModel(
                        "Salarii",
                        percentage7,
                        getResources().getColor(R.color.color_salariu)
                ));
        pieVenituri.addPieSlice(
                new PieModel(
                        "Chirii",
                        percentage8,
                        getResources().getColor(R.color.color_chirie)
                ));
        pieVenituri.addPieSlice(
                new PieModel(
                        "Proiecte",
                        percentage9,
                        getResources().getColor(R.color.color_proiecte)
                ));
        pieVenituri.addPieSlice(
                new PieModel(
                        "Dividende",
                        percentage10,
                        getResources().getColor(R.color.color_dividend)
                ));



        TextView textViewVenituriPie = findViewById(R.id.textViewPieVenituri);
        textViewVenituriPie.setText("Total venituri: " + (int) total2 + " RON");

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
            startActivity(new Intent(this, SetariActivity.class));
        }
        if (item.getItemId() == MENIU_LOG_OUT) {
            //DE REVENIT
        }
        return true;
    }
}