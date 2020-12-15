package com.example.reseller;

import android.app.Application;
import android.os.AsyncTask;

import com.example.reseller.DAO.portfolioDao;
import com.example.reseller.DAO.soldshoesDao;
import com.example.reseller.Repository.portfolioDatabase;
import com.example.reseller.Repository.soldshoesDatabase;
import com.example.reseller.entities.portfolio;
import com.example.reseller.entities.sold_shoes;

import java.util.List;

import androidx.lifecycle.LiveData;

public class sold_shoesRepository
{
    private com.example.reseller.DAO.soldshoesDao soldshoesDao;
    private LiveData<List<sold_shoes>> allSoldShoes;
    private LiveData<Integer> totalquantity;
    private LiveData<Integer> totalProfit;

    public sold_shoesRepository(Application application)
    {
        soldshoesDatabase database = soldshoesDatabase.getInstance(application);
        soldshoesDao = database.soldshoesDao();
        allSoldShoes = soldshoesDao.getAllSoldShoes();
        totalquantity= soldshoesDao.getQuantity();
        totalProfit= soldshoesDao.getProfit();
    }


    public void insert(sold_shoes sold_shoes)

    {
//        portfolioDatabase.databaseWriteExecutor.execute(() -> {
//            portfolioDao.insert(portfolio);
        new sold_shoesRepository.InsertPortfolioAsyncTask(soldshoesDao).execute(sold_shoes);

//    });
    }

    public void update(sold_shoes sold_shoes)
    {
        new sold_shoesRepository.UpdatePortfolioAsyncTask(soldshoesDao).execute(sold_shoes);
    }

    public void delete(sold_shoes sold_shoes)
    {
        new sold_shoesRepository.DeletePortfolioAsyncTask(soldshoesDao).execute(sold_shoes);
    }



    public LiveData<List<sold_shoes>> getAllSoldShoes()
    {
        return allSoldShoes;
    }

    public LiveData<Integer> getTotalquantity()
    {
        return totalquantity;
    }

    public LiveData<Integer> getTotalProfit()
    {
        return totalProfit;
    }




    private static class InsertPortfolioAsyncTask extends AsyncTask<sold_shoes,Void,Void>
    {
        private com.example.reseller.DAO.soldshoesDao soldshoesDao;
        private InsertPortfolioAsyncTask (soldshoesDao soldshoesDao)
        {
            this.soldshoesDao =soldshoesDao;
        }

        @Override
        protected Void doInBackground(sold_shoes...sold_shoes) {
           soldshoesDao.insert(sold_shoes[0]);
            return null;
        }
    }
    private static class UpdatePortfolioAsyncTask extends AsyncTask<sold_shoes,Void,Void>
    {
        private com.example.reseller.DAO.soldshoesDao soldshoesDao;
        private UpdatePortfolioAsyncTask (soldshoesDao soldshoesDao)
        {
            this.soldshoesDao =soldshoesDao;
        }

        @Override
        protected Void doInBackground(sold_shoes...sold_shoes) {
            soldshoesDao.update(sold_shoes[0]);
            return null;
        }
    }
    private static class DeletePortfolioAsyncTask extends AsyncTask<sold_shoes,Void,Void>
    {
        private com.example.reseller.DAO.soldshoesDao soldshoesDao;
        private DeletePortfolioAsyncTask (soldshoesDao soldshoesDao)
        {
            this.soldshoesDao =soldshoesDao;
        }

        @Override
        protected Void doInBackground(sold_shoes...sold_shoes) {
            soldshoesDao.delete(sold_shoes[0]);
            return null;
        }
    }
//    private static class DeleteAllPortfolioAsyncTask extends AsyncTask<portfolio,Void,Void>
//    {
//        private portfolioDao portfolioDao;
//        private DeleteAllPortfolioAsyncTask (portfolioDao portfolioDao)
//        {
//            this.portfolioDao =portfolioDao;
//        }
//
//
//
//        @Override
//        protected Void doInBackground(portfolio... portfolios) {
//            portfolioDao.deleteAllportfolio();
//            return null;
//        }
//    }

}
