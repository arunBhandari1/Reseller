package com.example.reseller.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.reseller.DAO.portfolioDao;
import com.example.reseller.DAO.soldshoesDao;
import com.example.reseller.entities.portfolio;
import com.example.reseller.entities.sold_shoes;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {portfolio.class}, version = 8, exportSchema = false)
public abstract class portfolioDatabase extends RoomDatabase
{
    private static final int NUMBER_OF_THREADS = 12;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static portfolioDatabase instance;




    public abstract portfolioDao portfolioDao();

    public static synchronized portfolioDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), portfolioDatabase.class,
                    "portfolio_database").fallbackToDestructiveMigration().
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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private portfolioDao portfolioDao;

        private PopulateDbAsyncTask(portfolioDatabase db)
        {
            portfolioDao = db.portfolioDao();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            portfolioDao.insert(new portfolio("123", "jOrdn", 9, 100, 1));
            portfolioDao.insert(new portfolio("123", "jOrdn", 9, 100, 1));
            portfolioDao.insert(new portfolio("123", "jOrdn", 9, 100, 1));
            return null;
        }
    }

}
