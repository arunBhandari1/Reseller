package com.example.reseller;

import android.app.Application;


import com.example.reseller.entities.sold_shoes;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class soldshoesViewModel extends AndroidViewModel
{
    private sold_shoesRepository repository;
    private LiveData<List<sold_shoes>> allsoldShoes;
    private LiveData<Integer> totalquantity;
    private LiveData<Integer> totalProfit;



    public soldshoesViewModel(@NonNull Application application) {
        super(application);
        repository= new sold_shoesRepository(application);
        allsoldShoes= repository.getAllSoldShoes();
        totalquantity=repository.getTotalquantity();
        totalProfit= repository.getTotalProfit();
    }

    void insert(sold_shoes sold_shoes)
    {
        repository.insert(sold_shoes);
    }

    public  void update(sold_shoes sold_shoes)
    {
        repository.update(sold_shoes);
    }

    public void delete(sold_shoes sold_shoes)
    {
        repository.delete(sold_shoes);
    }

   // public void deleteAllsoldS()
//    {
//        repository.deleteAllNotes();
//    }

    public LiveData<List<sold_shoes>> getAllsoldShoes(){
        return allsoldShoes;

    }
    public LiveData<Integer>  getTotalQuantity()
    {
        return totalquantity;
    }

    public LiveData<Integer> getTotalProfit()
    {
        return totalProfit;
    }



}
