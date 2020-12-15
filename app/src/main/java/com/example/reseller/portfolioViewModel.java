package com.example.reseller;

import android.app.Application;

import com.example.reseller.entities.portfolio;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class portfolioViewModel extends AndroidViewModel {
    private portfolioRepository repository;
    private LiveData<List<portfolio>> allPortfolios;
    private LiveData<Integer> totalquantity;
    private LiveData<Integer> totalAsset;

    public portfolioViewModel(@NonNull Application application) {
        super(application);
        repository= new portfolioRepository(application);
        allPortfolios= repository.getAllPortfolio();
        totalquantity=repository.getTotalquantity();
        totalAsset=repository.getAsset();
  }

   void insert(portfolio portfolio)
    {
        repository.insert(portfolio);
    }

    public  void update(portfolio portfolio)
    {
        repository.update(portfolio);
    }

    public void delete(portfolio portfolio)
    {
        repository.delete(portfolio);
    }

    public void deleteAllportfolios()
    {
        repository.deleteAllNotes();
    }

    public LiveData<List<portfolio>> getAllPortfolios(){
        return allPortfolios;

    }
    public LiveData<Integer>  getTotalQuantity()
    {
        return totalquantity;
    }
    public LiveData<Integer> getTotalAsset(){
        return totalAsset;
    }





}

