package com.example.budgettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.budgettracker.Adapters.AdapterMoneda;
import com.example.budgettracker.Database.AsyncOperations.AsyncWrapperCont;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.Database.Entities.User;
import com.example.budgettracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditareContActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editare_cont);

        final EditText editTextNumeCont = findViewById(R.id.editTextNumeCont);
        final EditText editTextSuma = findViewById(R.id.editTextSumaCont);

        Intent intent = getIntent();
        final boolean flag = intent.getBooleanExtra("flag", true);
        final Cont cont = (Cont)intent.getExtras().getSerializable("cont");

        if (!flag) {

            editTextNumeCont.setText(cont.getNume());
            editTextSuma.setText(" " + cont.getSuma());

            TextView textView = findViewById(R.id.textView);
            textView.setText("Editați contul");
        }

        final Spinner spinner = findViewById(R.id.spinnerMoneda);

        AdapterMoneda adapterMoneda = new AdapterMoneda(this);

        spinner.setAdapter(adapterMoneda);

        Button button = findViewById(R.id.buttonOK);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag) {
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    int userdId = prefs.getInt("idUser", 0);
                    View view1 = spinner.getSelectedView();
                    TextView textView = view1.findViewById(R.id.textViewMoneda);
                    String moneda = textView.getText().toString();

                    String numeCont = editTextNumeCont.getText().toString();
                    String sumaCont = editTextSuma.getText().toString();

                    Cont cont = new Cont(numeCont, Integer.parseInt(sumaCont), moneda.toUpperCase(), userdId);
                    new AsyncWrapperCont(getApplicationContext()).insert(cont, getApplicationContext());

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else {
                    View view1 = spinner.getSelectedView();
                    TextView textView = view1.findViewById(R.id.textViewMoneda);
                    String moneda = textView.getText().toString();

                    String numeCont = editTextNumeCont.getText().toString().trim();
                    String[] sumaCont = editTextSuma.getText().toString().trim().split("\\.");

                    cont.setSuma(Integer.parseInt(sumaCont[0]));
                    cont.setMoneda(moneda.toUpperCase());
                    cont.setNume(numeCont);

                    new AsyncWrapperCont(getApplicationContext()).update(cont);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
    }
}