package com.example.reseller.Repository;

import android.content.Context;
import android.os.AsyncTask;


import com.example.reseller.DAO.soldshoesDao;

import com.example.reseller.entities.sold_shoes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {sold_shoes.class}, version = 11, exportSchema = false)
public abstract class soldshoesDatabase extends RoomDatabase
{
        private static final int NUMBER_OF_THREADS = 12;
        public static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        private static soldshoesDatabase instance;




        public abstract soldshoesDao soldshoesDao();

        public static synchronized soldshoesDatabase getInstance(Context context)
        {
            if (instance == null)
            {
                instance = Room.databaseBuilder(context.getApplicationContext(), soldshoesDatabase.class,
                        "soldshoes_database").fallbackToDestructiveMigration().
                        addCallback(roomCallback).build();

            }
            return instance;
        }

        private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
        {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db)
            {

                super.onCreate(db);
                new soldshoesDatabase.PopulateDbAsyncTask(instance).execute();
            }
        };

        private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
        {
            private soldshoesDao soldshoesDao;

            private PopulateDbAsyncTask(soldshoesDatabase db)
            {
                soldshoesDao = db.soldshoesDao();
            }

            @Override
            protected Void doInBackground(Void... voids)
            {
                soldshoesDao.insert(new sold_shoes("123", "jOrdn", 9, 100, 100,200));
                soldshoesDao.insert(new sold_shoes("gsg", "jOrdn", 9,2,3,4 ));
                soldshoesDao.insert(new sold_shoes("sadfs", "jOrdn", 9, 100, 1,3));
                return null;
            }
        }

    }
