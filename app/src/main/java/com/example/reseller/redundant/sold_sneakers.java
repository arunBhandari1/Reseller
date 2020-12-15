package com.example.reseller.redundant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.reseller.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class sold_sneakers extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_sneakers);
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigator);
        bottomNavigationView.getMenu().findItem(R.id.nav_sold).setChecked(true);
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
                        startActivity(new Intent(getApplicationContext(),sold_sneakers.class));
                        return true;
                    case R.id.nav_summary:
                        startActivity(new Intent(getApplicationContext(), summary.class));
                        return true;



                }
                return true;
            }
        });
    }
}
