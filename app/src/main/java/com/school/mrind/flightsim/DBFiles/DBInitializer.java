package com.school.mrind.flightsim.DBFiles;

import android.arch.core.util.Function;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.school.mrind.flightsim.DBFiles.DB;
import com.school.mrind.flightsim.DBFiles.User;
import java.util.List;

public class DBInitializer {

    public List<User> userList;

    public static void populateAsync(@NonNull final DB db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final DB db) {
        populateWithTestData(db);
    }

    private static User addUser(final DB db, User user) {
        db.userDao().insert(user);
        return user;
    }

    private static void populateWithTestData(DB db) {
        User user = new User();
        user.setClassname("2ASA");
        user.setNome("CykaBlyat");
        user.setStudentnum(22);
        user.setInterrogato(false);
        addUser(db, user);

        List<User> userList = db.userDao().getAll();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DB mDb;

        PopulateDbAsync(DB db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //populateWithTestData(mDb);
            return null;
        }

    }

    public static class readDb extends AsyncTask<Void, Void, List<User>> {

        public final DB mDb;

        public readDb(DB db){
            mDb = db;
        }

        @Override
        protected List<User> doInBackground(Void... params){
            List<User> usrList = mDb.userDao().getAll();
            return usrList;
        }

        @Override
        protected void onPostExecute(List<User> result){
            super.onPostExecute(result);
        }
    }
}
