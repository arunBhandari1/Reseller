package com.example.reseller.redundant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.reseller.R;
import com.example.reseller.redundant.inStock;
import com.example.reseller.redundant.sold_sneakers;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class summary extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigator);
        bottomNavigationView.getMenu().findItem(R.id.nav_summary).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.nav_portfolio:
                        startActivity(new Intent(getApplicationContext(), inStock.class));
                        return true;
                    case R.id.nav_sold:
                        startActivity(new Intent(getApplicationContext(), sold_sneakers.class));
                        return true;




                }
                return true;
            }
        });
    }
}
