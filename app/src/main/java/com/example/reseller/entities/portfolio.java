package com.example.reseller.entities;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class portfolio {
    @PrimaryKey @NonNull
    public String styleCode;
    public String shoeName;
    public double  shoeSize;
    public double  totalCost;
    public int    quantity;


    public portfolio(@NonNull String styleCode, String shoeName, double shoeSize, double totalCost, int quantity) {
        this.styleCode = styleCode;
        this.shoeName = shoeName;
        this.shoeSize = shoeSize;
        this.totalCost = totalCost;
        this.quantity = quantity;
    }

    @NonNull
    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(@NonNull String styleCode) {
        this.styleCode = styleCode;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(double shoeSize) {
        this.shoeSize = shoeSize;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
