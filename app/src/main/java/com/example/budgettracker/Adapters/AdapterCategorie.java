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

    public AdapterCategorie(Context context, List<CategorieInregistrare> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
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

        CategorieInregistrare categorieInregistrare = (CategorieInregistrare) getItem(i);
        imageView.setImageResource(categorieInregistrare.getIdImagine());
        String text = categorieInregistrare.getCategorie().toString().toLowerCase();
        text = text.substring(0,1).toUpperCase() + text.substring(1);
        textView.setText(text);
        return view;
    }
}
