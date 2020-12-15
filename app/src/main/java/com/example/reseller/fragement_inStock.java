package com.example.reseller;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.reseller.entities.portfolio;
import com.example.reseller.entities.sold_shoes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragement_inStock extends Fragment
{

    portfolioViewModel mportfolioViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDITNOTE_ACTIVITY_REQUEST_CODE = 2;
    final portfolioAdapter adapter = new portfolioAdapter();
    String sprice = "";
    soldshoesViewModel msoldshoesViewModel;
    final soldshoesAdapter soldshoesadapter = new soldshoesAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragement_in_stock, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        RecyclerView recyclerView = getView().findViewById(R.id.reycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        // final portfolioAdapter adapter = new portfolioAdapter();
        recyclerView.setAdapter(adapter);

        msoldshoesViewModel = new ViewModelProvider(this).get(soldshoesViewModel.class);
        msoldshoesViewModel.getAllsoldShoes().observe(this, new Observer<List<sold_shoes>>()
        {
            @Override
            public void onChanged(List<sold_shoes> sold_shoes)
            {
                soldshoesadapter.setSoldshoess(sold_shoes);
            }
        });
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                openCustomDialog(viewHolder);

                adapter.notifyDataSetChanged();


            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListemer(new portfolioAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(portfolio portfolio)
            {
                Intent intent = new Intent(getContext(), AddEditShoeActivity.class);
                intent.putExtra(AddEditShoeActivity.EXTRA_ID, "EXTRA");
                intent.putExtra(AddEditShoeActivity.STYLE_CODE, portfolio.getStyleCode());
                intent.putExtra(AddEditShoeActivity.SHOE_NAME, portfolio.getShoeName());
                intent.putExtra(AddEditShoeActivity.SHOE_SIZE, portfolio.getShoeSize());
                intent.putExtra(AddEditShoeActivity.SHOE_PRICE, portfolio.getTotalCost());
                intent.putExtra(AddEditShoeActivity.SHOE_QUANTITY, portfolio.getQuantity());
                startActivityForResult(intent, EDITNOTE_ACTIVITY_REQUEST_CODE);

            }
        });


        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), AddEditShoeActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    private EditText editTextSellingPrice;

    public void openCustomDialog(RecyclerView.ViewHolder viewHolder)
    {
//        ExampleDialog customDialog = new ExampleDialog();
//        customDialog.show(getChildFragmentManager(),"example dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        editTextSellingPrice = view.findViewById(R.id.edittext_sellingprice);
        builder.setView(view)
                .setTitle("Selling Price")
                .setNegativeButton("cancel", null)
                .setPositiveButton("ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String sellingprice = editTextSellingPrice.getText().toString();

                        double num = Double.parseDouble(sellingprice);
                        portfolio portfolio = adapter.getPortfolioAt(viewHolder.getAdapterPosition());
                        sold_shoes soldshoes = new sold_shoes(portfolio.getStyleCode(), portfolio.getShoeName(),
                                portfolio.getQuantity(), portfolio.getShoeSize(), portfolio.getTotalCost(), num);

                        msoldshoesViewModel.insert(soldshoes);
                        mportfolioViewModel.delete(adapter.getPortfolioAt(viewHolder.getAdapterPosition()));
                        adapter.notifyDataSetChanged();


                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void openDialog(RecyclerView.ViewHolder viewHolder)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmation").setMessage("Do you really want to delete this shoe");
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


    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {

            portfolio portfolio = new portfolio(data.getStringExtra(AddEditShoeActivity.STYLE_CODE),
                    data.getStringExtra(AddEditShoeActivity.SHOE_NAME),
                    new Double(data.getStringExtra(AddEditShoeActivity.SHOE_SIZE))
                    , new Double(data.getStringExtra(AddEditShoeActivity.SHOE_PRICE)),
                    new Integer(data.getStringExtra(AddEditShoeActivity.SHOE_QUANTITY)));

            mportfolioViewModel.insert(portfolio);

        } else if (requestCode == EDITNOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            portfolio portfolio = new portfolio(data.getStringExtra(AddEditShoeActivity.STYLE_CODE),
                    data.getStringExtra(AddEditShoeActivity.SHOE_NAME),
                    new Double(data.getStringExtra(AddEditShoeActivity.SHOE_SIZE))
                    , new Double(data.getStringExtra(AddEditShoeActivity.SHOE_PRICE)),
                    new Integer(data.getStringExtra(AddEditShoeActivity.SHOE_QUANTITY)));
            mportfolioViewModel.update(portfolio);

        }
    }
}




