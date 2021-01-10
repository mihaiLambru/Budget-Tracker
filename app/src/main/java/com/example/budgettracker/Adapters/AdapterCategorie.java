package com.example.budgettracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgettracker.CustomObjects.CategorieInregistrare;
import com.example.budgettracker.R;

import java.util.List;

public class AdapterCategorie extends BaseAdapter {
    private Context context;
    private List<CategorieInregistrare> lista;
    private final boolean flag;

    //true-categorie false-tip inregistrare
    public AdapterCategorie(Context context, List<CategorieInregistrare> lista) {
        this.context = context;
        this.lista = lista;
        flag = true;
    }

    public AdapterCategorie(Context context) {
        this.context = context;
        flag = false;
    }


    @Override
    public int getCount() {
        if (flag)
            return lista.size();
        return 2;
    }

    @Override
    public Object getItem(int i) {
        if (flag)
            return lista.get(i);
        if (i == 0)
            return "Venit";
        return "Cheltuiala";
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view =  layoutInflater.inflate(R.layout.item_spinner_categorii_tranzactie, viewGroup, false);
        TextView textView = view.findViewById(R.id.textViewCategorie);
        ImageView imageView = view.findViewById(R.id.imageViewCategorie);

        if (flag) {
            CategorieInregistrare categorieInregistrare = (CategorieInregistrare) getItem(i);
            imageView.setImageResource(categorieInregistrare.getIdImagine());
            String text = categorieInregistrare.getCategorie().toString().toLowerCase();
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
            textView.setText(text);
        }
        else {
            if (i == 0) {
                textView.setText("Venit");
                imageView.setImageResource(R.drawable.ic_plus);
            }
            else if (i == 1) {
                textView.setText("Cheltuiala");
                imageView.setImageResource(R.drawable.ic_minus);
            }

        }
        return view;
    }
}
