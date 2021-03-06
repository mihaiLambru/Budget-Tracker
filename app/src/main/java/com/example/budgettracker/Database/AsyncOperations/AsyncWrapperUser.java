package com.example.budgettracker.Database.AsyncOperations;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.budgettracker.Activities.RegisterActivity;
import com.example.budgettracker.Database.Entities.User;
import com.example.budgettracker.Database.DAO.UserDAO;
import com.example.budgettracker.Database.LocalDatabase;

public class AsyncWrapperUser {
    private UserDAO userDAO;

    public AsyncWrapperUser(Context context) {
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        userDAO = localDatabase.getUserDAO();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDao)
        {
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
        protected User doInBackground(Integer ...id) {
            User user = userDAO.select(id[0]);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
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

    public void select(Integer idUser) {
        new SelectUserAsyncTask(userDAO).execute(idUser);
    }

}
