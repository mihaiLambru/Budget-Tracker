package com.example.budgettracker.Database.AsyncOperations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.budgettracker.Activities.EditareContActivity;
import com.example.budgettracker.Activities.InregistrareActivity;
import com.example.budgettracker.Activities.ListaConturiActivity;
import com.example.budgettracker.Activities.MainActivity;
import com.example.budgettracker.Adapters.AdapterConturi;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.Database.DAO.ContDAO;
import com.example.budgettracker.Database.Entities.Inregistrare;
import com.example.budgettracker.Database.LocalDatabase;
import com.example.budgettracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;

public class AsyncWrapperCont {
    private ContDAO contDAO;
    private Context context;

    public AsyncWrapperCont(Context context) {
        this.context = context;
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        contDAO = localDatabase.getContDAO();
    }

    private static class InsertContAsyncTask extends AsyncTask<Cont, Void, Void> {
        private ContDAO contDAO;
        private Context context;

        private InsertContAsyncTask(ContDAO contDAO, Context context) {
            this.context = context;
            this.contDAO = contDAO;
        }

        @Override
        protected Void doInBackground(final Cont... conturi) {
            contDAO.insert(conturi[0]);
            SharedPreferences pref = context.getSharedPreferences("prefs", MODE_PRIVATE);
            final int idUser = pref.getInt("idUser", 0);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("lastIdCont");

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Long value = dataSnapshot.getValue(Long.class);
                    ++value;
                    conturi[0].setIdCont((int) (long)value);

                    myRef.setValue(value);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Users");
                    DatabaseReference userRef = ref.child("User " + idUser);
                    DatabaseReference conturiRef = userRef.child("Conturi");
                    DatabaseReference contRef = conturiRef.child("Cont " + conturi[0].getIdCont());
                    contRef.setValue(conturi[0]);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });


            return null;
        }
    }

    private static class UpdateContAsyncTask extends AsyncTask<Cont, Void, Void> {
        private ContDAO contDAO;

        private UpdateContAsyncTask(ContDAO contDAO) {
            this.contDAO = contDAO;
        }

        @Override
        protected Void doInBackground(Cont... conturi) {
            contDAO.update(conturi[0]);
            return null;
        }
    }

    private static class DeleteContAsyncTask extends AsyncTask<Cont, Void, Void> {
        private ContDAO contDAO;

        private DeleteContAsyncTask(ContDAO contDAO) {
            this.contDAO = contDAO;
        }

        @Override
        protected Void doInBackground(Cont... conturi) {
            contDAO.delete(conturi[0]);
            return null;
        }
    }

    private static class SelectContAsyncTask extends AsyncTask<Integer, Void, Cont> {
        private ContDAO contDAO;

        private SelectContAsyncTask(ContDAO contDAO) {
            this.contDAO = contDAO;
        }

        @Override
        protected Cont doInBackground(Integer... id) {
            Cont cont = contDAO.select(id[0]);
            return cont;
        }

        @Override
        protected void onPostExecute(Cont cont) {
            //de revenit
            super.onPostExecute(cont);
        }
    }

    private static class SelectConturiAsyncTask extends AsyncTask<Integer, Void, List<Cont>> {
        private Context context;
        private ContDAO contDAO;

        private SelectConturiAsyncTask(ContDAO contDAO, Context context) {

             this.contDAO = contDAO;
             this.context = context;
        }

        @Override
        protected List<Cont> doInBackground(Integer... id) {
            List<Cont> conturi = contDAO.selectList(id[0]);
            return conturi;
        }

        @Override
        protected void onPostExecute(final List<Cont> conturi) {

            super.onPostExecute(conturi);
            SharedPreferences prefs =   context.getSharedPreferences("prefs", MODE_PRIVATE);
            int idUser = prefs.getInt("idUser", 0);

            Spinner spinner = ((Activity)context).findViewById(R.id.spinner);
            final FloatingActionButton fab = ((Activity)context).findViewById(R.id.floatingActionButton);

            final AdapterConturi adapterConturi = new AdapterConturi(context, conturi);

            spinner.setAdapter(adapterConturi);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    final Cont cont = (Cont)adapterConturi.getItem(i);


                    new AsyncWrapperInregistrare(context).selectLista(cont, context);

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, InregistrareActivity.class);
                            intent.putExtra("cont", cont);
                            context.startActivity(intent);
                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Button button = ((Activity)context).findViewById(R.id.buttonSettings);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListaConturiActivity.class);
                    intent.putExtra("lista", (Serializable) conturi);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void insert(Cont cont, Context context) {
        new AsyncWrapperCont.InsertContAsyncTask(contDAO,context).execute(cont);
    }

    public void update(Cont cont) {
        new AsyncWrapperCont.UpdateContAsyncTask(contDAO).execute(cont);
    }

    public void delete(Cont cont) {
        new AsyncWrapperCont.DeleteContAsyncTask(contDAO).execute(cont);
    }

    public void select(Integer id) {
        new AsyncWrapperCont.SelectContAsyncTask(contDAO).execute(id);
    }

    public void selectConturi(Integer id) {
        new AsyncWrapperCont.SelectConturiAsyncTask(contDAO, context).execute(id);
    }

}
