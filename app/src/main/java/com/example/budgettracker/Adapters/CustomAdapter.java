package com.example.budgettracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgettracker.CustomObjects.Cont;
import com.example.budgettracker.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Cont> {
    public CustomAdapter(Context ctx, List<Cont> list) {
        super(ctx, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public View initView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView =  layoutInflater.inflate(R.layout.item_spinner, parent, false);

        TextView textViewNumeCont = convertView.findViewById(R.id.textViewNumeCont);
        TextView textViewSumaCont = convertView.findViewById(R.id.textViewSumaCont);
        TextView textViewMonedaCont = convertView.findViewById(R.id.textViewMonedaCont);
        ImageView imageViewMoneda = convertView.findViewById(R.id.imageView);

        Cont cont = getItem(position);

        textViewNumeCont.setText(cont.getNume());
        textViewSumaCont.setText(String.format("%.2f", cont.getSuma()));

        String moneda = cont.getMoneda().toLowerCase();

        switch (moneda) {
            case "eur":
                textViewMonedaCont.setTextColor(getContext().getResources().getColor(R.color.color_eur));
                imageViewMoneda.setImageResource(R.drawable.eur);
                break;
            case "usd":
                textViewMonedaCont.setTextColor(getContext().getResources().getColor(R.color.color_usd));
                imageViewMoneda.setImageResource(R.drawable.usd);
                break;
            case "ron":
                textViewMonedaCont.setTextColor(getContext().getResources().getColor(R.color.color_ron));
                imageViewMoneda.setImageResource(R.drawable.ron);
                break;
        }

        textViewMonedaCont.setText(moneda.toUpperCase());

        return convertView;
    }
}

