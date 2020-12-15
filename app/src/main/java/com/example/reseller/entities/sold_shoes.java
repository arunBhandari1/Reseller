package com.example.reseller.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class sold_shoes
{
    @PrimaryKey @NonNull
    public String styleCode;
    public String shoeName;
   public int quantity;
   // public Integer quantity;
    public double shoeSize;
    public double totalCost;
    public double soldPrice;

    public sold_shoes(String styleCode, String shoeName, int quantity, double shoeSize,
                      double totalCost, double soldPrice)
    {
        this.styleCode = styleCode;
        this.shoeName = shoeName;
    //    this.quantity = Integer.valueOf(quantity);
        this.quantity=quantity;
        this.shoeSize = shoeSize;
        this.totalCost = totalCost;
        this.soldPrice = soldPrice;
    }

    public String getStyleCode()
    {
        return styleCode;
    }

    public void setStyleCode(String styleCode)
    {
        this.styleCode = styleCode;
    }

    public String getShoeName()
    {
        return shoeName;
    }

    public void setShoeName(String shoeName)
    {
        this.shoeName = shoeName;
    }

//    public int getQuantity()
//    {
//        return quantity;
//    }
    public Integer getQuantity()
    {
        return quantity;
    }

//    public void setQuantity(int quantity)
//    {
//        this.quantity = quantity;
//    }    public void setQuantity(int quantity)
//    {
//        this.quantity = Integer.valueOf(quantity);
//    }


    public double getShoeSize()
    {
        return shoeSize;
    }

    public void setShoeSize(double shoeSize)
    {
        this.shoeSize = shoeSize;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }

    public double getSoldPrice()
    {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice)
    {
        this.soldPrice = soldPrice;
    }
}
