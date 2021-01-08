package com.example.budgettracker.Database.AsyncOperations;

import android.content.Context;
import android.os.AsyncTask;

import com.example.budgettracker.Database.DAO.InregistrareDAO;
import com.example.budgettracker.Database.Entities.Inregistrare;
import com.example.budgettracker.Database.LocalDatabase;

public class AsyncWrapperInregistrare {

    private InregistrareDAO inregistrareDAO;

    public AsyncWrapperInregistrare(Context context) {
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        inregistrareDAO = localDatabase.getInregistrareDAO();
    }

    private static class InsertInregistrareAsyncTask extends AsyncTask<Inregistrare, Void, Void> {
        private InregistrareDAO inregistrareDAO;

        private InsertInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }

        @Override
        protected Void doInBackground(Inregistrare... inregistrari) {
            inregistrareDAO.insert(inregistrari[0]);
            return null;
        }
    }

    private static class UpdateInregistrareAsyncTask extends AsyncTask<Inregistrare, Void, Void> {
        private InregistrareDAO inregistrareDAO;

        private UpdateInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }
        @Override
        protected Void doInBackground(Inregistrare... inregistrari) {
            inregistrareDAO.update(inregistrari[0]);
            return null;
        }
    }

    private static class DeleteInregistrareAsyncTask extends AsyncTask<Inregistrare, Void, Void> {
        private InregistrareDAO inregistrareDAO;

        private DeleteInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }
        @Override
        protected Void doInBackground(Inregistrare... inregistrari) {
            inregistrareDAO.delete(inregistrari[0]);
            return null;
        }
    }

    private static class SelectInregistrareAsyncTask extends AsyncTask<Integer, Void, Inregistrare> {
        private InregistrareDAO inregistrareDAO;

        private SelectInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }

        @Override
        protected Inregistrare doInBackground(Integer... id) {
            Inregistrare inregistrare = inregistrareDAO.select(id[0]);
            return inregistrare;
        }

        @Override
        protected void onPostExecute(Inregistrare inregistrare) {
            //de revenit
            super.onPostExecute(inregistrare);
        }
    }

    public void insert(Inregistrare inregistrare) {
        new AsyncWrapperInregistrare.InsertInregistrareAsyncTask(inregistrareDAO).execute(inregistrare);
    }

    public void update(Inregistrare inregistrare) {
        new AsyncWrapperInregistrare.UpdateInregistrareAsyncTask(inregistrareDAO).execute(inregistrare);
    }

    public void delete(Inregistrare inregistrare) {
        new AsyncWrapperInregistrare.DeleteInregistrareAsyncTask(inregistrareDAO).execute(inregistrare);
    }

    public void select(Integer id) {
        new AsyncWrapperInregistrare.SelectInregistrareAsyncTask(inregistrareDAO).execute(id);
    }
}
