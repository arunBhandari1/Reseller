package com.example.reseller;


import android.content.DialogInterface;
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
import android.view.View;
import android.view.ViewGroup;

import com.example.reseller.entities.portfolio;
import com.example.reseller.entities.sold_shoes;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragement_soldshoes extends Fragment
{
    soldshoesViewModel msoldshoesViewModel;
    final soldshoesAdapter adapter = new soldshoesAdapter();

    public fragement_soldshoes()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragement_soldshoes, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        RecyclerView recyclerView = getView().findViewById(R.id.reycler_view2);
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
                //update Recycler view
                adapter.setSoldshoess(sold_shoes);
            }


        });

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

    }

    public void openDialog(RecyclerView.ViewHolder viewHolder)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmation").setMessage("Do you really want to delete this sold shoe?");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //this will get the adapter at
                msoldshoesViewModel.delete(adapter.getSoldShoesAt(viewHolder.getAdapterPosition()));

            }
        });
        builder.setNegativeButton(R.string.no, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
