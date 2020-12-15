package com.example.reseller;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_summary extends Fragment
{
    portfolioViewModel mportfolioViewModel;
    soldshoesViewModel msoldshoesViewModel;

    public fragment_summary()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        mportfolioViewModel = new ViewModelProvider(this).get(portfolioViewModel.class);
        TextView totalQuantity = (getView()).findViewById(R.id.editText_quantity);
        TextView totalAsset = getView().findViewById(R.id.editText_totalAsset);

        mportfolioViewModel.getTotalQuantity().observe(this, new Observer<Integer>()

        {
            @Override
            public void onChanged(Integer quantity)
            {
                if (quantity==null)
                {
                    totalQuantity.setText(0+ " ");
                }
                else
                {
                    totalQuantity.setText(quantity + " ");
                }
            }
        });
        mportfolioViewModel.getTotalAsset().observe(this, new Observer<Integer>()

        {
            @Override
            public void onChanged(Integer asset)
            {
                if (asset==null)
                {
                    totalAsset.setText(0+ " ");
                }
                else
                {
                    totalAsset.setText("$"+asset);
                }
            }
        });

       msoldshoesViewModel = new ViewModelProvider(this).get(soldshoesViewModel.class);
        TextView totalSoldQuantity = (getView()).findViewById(R.id.editsold_quantity);
        TextView totalprofit = getView().findViewById(R.id.editText_totalprofit);

        msoldshoesViewModel.getTotalQuantity().observe(this, new Observer<Integer>()

        {
            @Override
            public void onChanged(Integer quantity)
            {
                if (quantity==null)
                {
                    totalSoldQuantity.setText(0+ " ");
                }
                else
                {
                    totalSoldQuantity.setText(quantity + " ");
                }
            }
        });

        msoldshoesViewModel.getTotalProfit().observe(this, new Observer<Integer>()

    {
        @Override
        public void onChanged(Integer profit)
        {
            if (profit==null)
            {
                totalprofit.setText(0+ " ");
            }
            else
            {
                totalprofit.setText("$"+profit);
            }
        }
    });

    }
}
