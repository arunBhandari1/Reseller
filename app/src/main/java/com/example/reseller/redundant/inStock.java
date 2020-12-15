package com.example.reseller.redundant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.reseller.AddEditShoeActivity;
import com.example.reseller.R;
import com.example.reseller.entities.portfolio;
import com.example.reseller.portfolioAdapter;
import com.example.reseller.portfolioViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


public class inStock extends AppCompatActivity
{
    public portfolioViewModel mportfolioViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDITNOTE_ACTIVITY_REQUEST_CODE = 2;
    final portfolioAdapter adapter = new portfolioAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_stock);
        RecyclerView recyclerView = findViewById(R.id.reycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        // final portfolioAdapter adapter = new portfolioAdapter();
        recyclerView.setAdapter(adapter);


        mportfolioViewModel = new ViewModelProvider(this).get(portfolioViewModel.class);
        mportfolioViewModel.getAllPortfolios().observe(this, new Observer<List<portfolio>>()

        {
            @Override
            public void onChanged(List<portfolio> portfolios)
            {
                //update Recycler view
                adapter.setPortfolios(portfolios);
            }


        });

        /**
         * when swiped .deleting tool
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {

                openDialog(viewHolder);
                adapter.notifyDataSetChanged();


            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListemer(new portfolioAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(portfolio portfolio)
            {
                Intent intent = new Intent(inStock.this, AddEditShoeActivity.class);
                intent.putExtra(AddEditShoeActivity.EXTRA_ID, "EXTRA");
                intent.putExtra(AddEditShoeActivity.STYLE_CODE, portfolio.getStyleCode());
                intent.putExtra(AddEditShoeActivity.SHOE_NAME, portfolio.getShoeName());
                intent.putExtra(AddEditShoeActivity.SHOE_SIZE, portfolio.getShoeSize());
                intent.putExtra(AddEditShoeActivity.SHOE_PRICE, portfolio.getTotalCost());
                intent.putExtra(AddEditShoeActivity.SHOE_QUANTITY, portfolio.getQuantity());
                startActivityForResult(intent, EDITNOTE_ACTIVITY_REQUEST_CODE);

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(inStock.this, AddEditShoeActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigator);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
               switch (item.getItemId())
               {
                   case R.id.nav_portfolio:
                      startActivity(new Intent(getApplicationContext(),inStock.class));
                      return true;
                   case R.id.nav_sold:
                       startActivity(new Intent(getApplicationContext(), sold_sneakers.class));
                       return true;
                   case R.id.nav_summary:
                       startActivity(new Intent(getApplicationContext(), summary.class));
                       return true;



               }
               return true;
            }
        });
    }

    public void openDialog(RecyclerView.ViewHolder viewHolder)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confiamation").setMessage("Do you really want to delete this shoe");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //this will get the adapter at
                mportfolioViewModel.delete(adapter.getPortfolioAt(viewHolder.getAdapterPosition()));

            }
        });
        builder.setNegativeButton(R.string.no, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
//        {
//
//            portfolio portfolio = new portfolio(data.getStringExtra(AddEditShoeActivity.STYLE_CODE),
//                    data.getStringExtra(AddEditShoeActivity.SHOE_NAME),
//                    new Double(data.getStringExtra(AddEditShoeActivity.SHOE_SIZE))
//                    , new Double(data.getStringExtra(AddEditShoeActivity.SHOE_PRICE)),
//                    new Integer(data.getStringExtra(AddEditShoeActivity.SHOE_QUANTITY)));
//
//            mportfolioViewModel.insert(portfolio);
//
//        } else if (requestCode == EDITNOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
//        {
//            portfolio portfolio = new portfolio(data.getStringExtra(AddEditShoeActivity.STYLE_CODE),
//                    data.getStringExtra(AddEditShoeActivity.SHOE_NAME),
//                    new Double(data.getStringExtra(AddEditShoeActivity.SHOE_SIZE))
//                    , new Double(data.getStringExtra(AddEditShoeActivity.SHOE_PRICE)),
//                    new Integer(data.getStringExtra(AddEditShoeActivity.SHOE_QUANTITY)));
//            mportfolioViewModel.update(portfolio);
//
//        }
//    }


}