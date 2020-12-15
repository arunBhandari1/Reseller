package com.example.reseller.DAO;


import com.example.reseller.entities.sold_shoes;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface soldshoesDao {
    @Insert
    void insert (sold_shoes shoes);
    @Update
    void update(sold_shoes shoes);
    @Delete
    void delete(sold_shoes shoes);

    @Query("SELECT SUM(soldPrice-totalCost) FROM sold_shoes")
    LiveData<Integer> getProfit();

    @Query("SELECT SUM(quantity) FROM sold_shoes")
    LiveData<Integer> getQuantity();

    @Query("SELECT * FROM sold_shoes")
    LiveData <List<sold_shoes>> getAllSoldShoes();


}
