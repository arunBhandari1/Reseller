package com.example.reseller.DAO;

import com.example.reseller.entities.portfolio;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface portfolioDao {
    @Insert
   void insert (portfolio portfolio);
    @Update
    void update(portfolio portfolio);
    @Delete
    void delete(portfolio portfolio);

    @Query("DELETE FROM portfolio")
    void deleteAllportfolio();

   @Query("SELECT SUM(quantity) FROM portfolio")
   LiveData<Integer> getQuantity();

    @Query("SELECT SUM(quantity*totalCost) FROM portfolio")
    LiveData<Integer> getAsset();


    @Query("SELECT * FROM portfolio")
    LiveData <List<portfolio>> getAllPortfolios();


}
