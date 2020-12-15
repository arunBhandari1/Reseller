package com.example.reseller;

import android.app.Application;
import android.os.AsyncTask;

import com.example.reseller.DAO.portfolioDao;
import com.example.reseller.Repository.portfolioDatabase;
import com.example.reseller.entities.portfolio;

import java.util.List;

import androidx.lifecycle.LiveData;


public class portfolioRepository {
    private portfolioDao portfolioDao;
    private LiveData<List<portfolio>> allPortfolio;
    private LiveData<Integer> totalquantity;
    private LiveData<Integer> totalAsset;

    public portfolioRepository(Application application)
    {
        portfolioDatabase database = portfolioDatabase.getInstance(application);
        portfolioDao = database.portfolioDao();
        allPortfolio = portfolioDao.getAllPortfolios();
       totalquantity= portfolioDao.getQuantity();
       totalAsset= portfolioDao.getAsset();
//


    }
    public void insert(portfolio portfolio)

    {
//        portfolioDatabase.databaseWriteExecutor.execute(() -> {
//            portfolioDao.insert(portfolio);
        new InsertPortfolioAsyncTask(portfolioDao).execute(portfolio);

//    });
    }

    public void update(portfolio portfolio)
    {
        new UpdatePortfolioAsyncTask(portfolioDao).execute(portfolio);
    }

    public void delete(portfolio portfolio)
    {
        new DeletePortfolioAsyncTask(portfolioDao).execute(portfolio);
    }

    public void deleteAllNotes()
    {
        new DeleteAllPortfolioAsyncTask(portfolioDao).execute();
    }

    public LiveData<List<portfolio>> getAllPortfolio()
    {
        return allPortfolio;
    }

    public LiveData<Integer> getTotalquantity()
    {
        return totalquantity;
    }
    public LiveData<Integer> getAsset()
    {
        return totalAsset;
    }



    private static class InsertPortfolioAsyncTask extends AsyncTask<portfolio,Void,Void>
    {
    private portfolioDao portfolioDao;
    private InsertPortfolioAsyncTask (portfolioDao portfolioDao)
    {
        this.portfolioDao =portfolioDao;
    }

        @Override
        protected Void doInBackground(portfolio... portfolios) {
        portfolioDao.insert(portfolios[0]);
        return null;
        }
    }
    private static class UpdatePortfolioAsyncTask extends AsyncTask<portfolio,Void,Void>
    {
        private portfolioDao portfolioDao;
        private UpdatePortfolioAsyncTask (portfolioDao portfolioDao)
        {
            this.portfolioDao =portfolioDao;
        }

        @Override
        protected Void doInBackground(portfolio... portfolios) {
            portfolioDao.update(portfolios[0]);
            return null;
        }
    }
    private static class DeletePortfolioAsyncTask extends AsyncTask<portfolio,Void,Void>
    {
        private portfolioDao portfolioDao;
        private DeletePortfolioAsyncTask (portfolioDao portfolioDao)
        {
            this.portfolioDao =portfolioDao;
        }

        @Override
        protected Void doInBackground(portfolio... portfolios) {
            portfolioDao.delete(portfolios[0]);
            return null;
        }
    }
    private static class DeleteAllPortfolioAsyncTask extends AsyncTask<portfolio,Void,Void>
    {
        private portfolioDao portfolioDao;
        private DeleteAllPortfolioAsyncTask (portfolioDao portfolioDao)
        {
            this.portfolioDao =portfolioDao;
        }



        @Override
        protected Void doInBackground(portfolio... portfolios) {
            portfolioDao.deleteAllportfolio();
            return null;
        }
    }


}
