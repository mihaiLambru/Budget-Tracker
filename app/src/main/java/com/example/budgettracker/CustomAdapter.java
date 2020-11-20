package com.example.budgettracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

        Cont cont = getItem(position);

        textViewNumeCont.setText(cont.getNume());
        textViewSumaCont.setText(String.format("%.2f", cont.getSuma()));
        String moneda = cont.getMoneda().toLowerCase();

        if (moneda.equals("eur")) {
            textViewMonedaCont.setTextColor(getContext().getResources().getColor(R.color.color_eur));
        }
        else if(moneda.equals("usd")) {
            textViewMonedaCont.setTextColor(getContext().getResources().getColor(R.color.color_usd));
        }
        else if(moneda.equals("ron")) {
            textViewMonedaCont.setTextColor(getContext().getResources().getColor(R.color.color_ron));
        }

        textViewMonedaCont.setText(moneda.toUpperCase());

        return convertView;
    }
}

