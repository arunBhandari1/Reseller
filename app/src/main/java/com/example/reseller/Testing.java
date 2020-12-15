package com.example.reseller;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;

public class Testing extends AppCompatActivity
{

    private  fragement_inStock fragementA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

       fragementA = new fragement_inStock();
       getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,
                new fragement_inStock() ).commit();

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigator);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
               Fragment fragment =null;
                switch (item.getItemId())
                {
                    case R.id.nav_portfolio:
                        fragment =new fragement_inStock();
                        break;

                    case R.id.nav_sold:
                      fragment =new fragement_soldshoes();
                        break;
                    case R.id.nav_summary:
                     fragment= new fragment_summary();
                       break;
                }
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,fragment).
                       commit();
                return true;
            }
        });
    }

}
