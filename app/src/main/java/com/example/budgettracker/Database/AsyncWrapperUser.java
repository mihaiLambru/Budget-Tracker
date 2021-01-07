package com.example.budgettracker.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.budgettracker.CustomObjects.User;

public class AsyncWrapperUser {
    private UserDAO userDAO;

    public AsyncWrapperUser(Context context) {
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        userDAO = localDatabase.getUserDAO();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDao) {
            this.userDAO = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private UpdateUserAsyncTask(UserDAO userDao) {
            this.userDAO = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private DeleteUserAsyncTask(UserDAO userDao) {
            this.userDAO = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.delete(users[0]);
            return null;
        }
    }

    private static class SelectUserAsyncTask extends AsyncTask<Integer, Void, User> {
        private UserDAO userDAO;

        private SelectUserAsyncTask(UserDAO userDao) {
            this.userDAO = userDao;
        }

        @Override
        protected User doInBackground(Integer... id) {
            User user = userDAO.select(id[0]);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            //de revenit
            super.onPostExecute(user);
        }
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDAO).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDAO).execute(user);
    }

    public void select(Integer id) {
        new SelectUserAsyncTask(userDAO).execute(id);
    }

}
