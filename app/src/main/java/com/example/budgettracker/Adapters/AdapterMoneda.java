package com.example.budgettracker.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgettracker.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMoneda extends BaseAdapter {
    private Context context;
    private List<String> monede;

    public AdapterMoneda(Context context) {
        this.context = context;
        monede = new ArrayList<>();
        monede.add("RON");
        monede.add("EUR");
        monede.add("USD");
    }

    @Override
    public int getCount() {
        return monede.size();
    }

    @Override
    public Object getItem(int i) {
        return monede.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view =  layoutInflater.inflate(R.layout.item_spinner_moneda, viewGroup, false);

        TextView textView = view.findViewById(R.id.textViewMoneda);
        ImageView imageView = view.findViewById(R.id.imageViewMoneda);

        if (i == 0) {
            textView.setText("RON");
            textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(context.getResources().getColor(R.color.color_ron));
            imageView.setImageResource(R.drawable.ron);
        }

        else if(i == 1) {
            textView.setText("EUR");
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextColor(context.getResources().getColor(R.color.color_eur));
            imageView.setImageResource(R.drawable.eur);
        }

        else if (i == 2) {
            textView.setText("USD");
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextColor(context.getResources().getColor(R.color.color_usd));
            imageView.setImageResource(R.drawable.usd);
        }

        return view;
    }
}
