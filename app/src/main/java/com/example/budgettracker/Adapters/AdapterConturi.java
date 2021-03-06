package com.example.budgettracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgettracker.Activities.EditareContActivity;
import com.example.budgettracker.Activities.ListaConturiActivity;
import com.example.budgettracker.Activities.MainActivity;
import com.example.budgettracker.Database.AsyncOperations.AsyncWrapperCont;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.R;

import java.util.List;

public class AdapterConturi extends BaseAdapter {
    private final Context context;
    private final List<Cont> list;
    private int item;

    public AdapterConturi(Context context, List<Cont> list) {
        this.list = list;
        this.context = context;

        if(context instanceof MainActivity) {
            item = R.layout.item_spinner_main_activity;
        }
        else if(context instanceof ListaConturiActivity) {
            item = R.layout.item_list_view_conturi;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0 && i < list.size())
            return list.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public View initView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView =  layoutInflater.inflate(item, parent, false);

        final TextView textViewNumeCont = convertView.findViewById(R.id.textViewNumeCont);
        final TextView textViewSumaCont = convertView.findViewById(R.id.textViewSumaCont);
        TextView textViewMonedaCont = convertView.findViewById(R.id.textViewMonedaCont);
        ImageView imageViewMoneda = convertView.findViewById(R.id.imageView);

        if (item == R.layout.item_list_view_conturi) {
            Button buttonSettingsAccount = convertView.findViewById(R.id.buttonSettingsAccount);
            Button buttonDeleteAccount = convertView.findViewById(R.id.buttonDeleteAccount);

            buttonSettingsAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditareContActivity.class);
                    intent.putExtra("flag", false);
                    intent.putExtra("cont", (Cont) getItem(position));
                    context.startActivity(intent);
                }
            });

            buttonDeleteAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    Cont cont = (Cont) getItem(position);
                    new AsyncWrapperCont(context).delete(cont);
                    notifyDataSetChanged();
                }
            });
        }


        Cont cont = (Cont) getItem(position);

        textViewNumeCont.setText(cont.getNume());
        textViewSumaCont.setText(String.format("%.2f", cont.getSuma()));

        String moneda = cont.getMoneda().toLowerCase();

        switch (moneda) {
            case "eur":
                textViewMonedaCont.setTextColor(context.getResources().getColor(R.color.color_eur));
                imageViewMoneda.setImageResource(R.drawable.eur);
                break;
            case "usd":
                textViewMonedaCont.setTextColor(context.getResources().getColor(R.color.color_usd));
                imageViewMoneda.setImageResource(R.drawable.usd);
                break;
            case "ron":
                textViewMonedaCont.setTextColor(context.getResources().getColor(R.color.color_ron));
                imageViewMoneda.setImageResource(R.drawable.ron);
                break;
        }

        textViewMonedaCont.setText(moneda.toUpperCase());

        return convertView;
    }

}
