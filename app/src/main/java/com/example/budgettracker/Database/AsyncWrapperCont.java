package com.example.budgettracker.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.budgettracker.CustomObjects.Cont;

public class AsyncWrapperCont {

    private ContDAO contDAO;

    public AsyncWrapperCont(Context context) {
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        contDAO = localDatabase.getContDAO();
    }

    private static class InsertContAsyncTask extends AsyncTask<Cont, Void, Void> {
        private ContDAO contDAO;

        private InsertContAsyncTask(ContDAO contDAO) {
            this.contDAO = contDAO;
        }

        @Override
        protected Void doInBackground(Cont... conturi) {
            contDAO.insert(conturi[0]);
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

    public void insert(Cont cont) {
        new AsyncWrapperCont.InsertContAsyncTask(contDAO).execute(cont);
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

}
