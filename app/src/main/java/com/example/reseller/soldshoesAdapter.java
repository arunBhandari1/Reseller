package com.example.reseller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.reseller.entities.sold_shoes;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class soldshoesAdapter extends RecyclerView.Adapter<soldshoesAdapter.soldshoesHolder>
{
    class soldshoesHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewName;
        private TextView textViewProfit;
        private TextView textViewSize;

        public soldshoesHolder(View itemView)
        {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name_textView2);
            textViewProfit = itemView.findViewById(R.id.profit_textView);
            textViewSize = itemView.findViewById(R.id.size_textView2);
            //   textViewName.setOnClickListener(this);
            //    textViewPrice.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int position = getAdapterPosition();
//                    if (listener==null && position !=RecyclerView.NO_POSITION)
//                        {
//                    listener.onItemClick(soldshoess.get(position));
                    //    }
                }
            });
        }

//        @Override
//        public void onClick(View v) {
//            Log.d("ONCLICK","click vayo hai kta yo click bata");
        //    showPopupMenu(v);
//        }

//        private void showPopupMenu(View view)
//        {
//            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
//            popupMenu.inflate(R.menu.menu_for_list);
//            popupMenu.show();
//        }
    }


    private List<sold_shoes> soldshoess = new ArrayList<>(); //array list of shoes
    private soldshoesAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public soldshoesAdapter.soldshoesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sold_shoes_item, parent, false);
        return new soldshoesAdapter.soldshoesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull soldshoesAdapter.soldshoesHolder holder, int position)
    {
        sold_shoes currentSoldshoes = soldshoess.get(position);
        if (currentSoldshoes.getSoldPrice()> currentSoldshoes.getTotalCost())
        {
           holder.itemView.setBackgroundColor(123);
            holder.textViewProfit.setText("Profit: $" +
                    (currentSoldshoes.getSoldPrice() - currentSoldshoes.getTotalCost()));
        }
        else
        {
            holder.textViewProfit.setText("Loss $" +
                    (currentSoldshoes.getSoldPrice() - currentSoldshoes.getTotalCost()));
        }
        holder.textViewName.setText(currentSoldshoes.getShoeName());
        holder.textViewSize.setText(" Size: " + currentSoldshoes.getShoeSize());


    }

    @Override
    public int getItemCount()
    {
        return soldshoess.size();
    }

    public void setSoldshoess(List<sold_shoes> soldshoess)
    {
        this.soldshoess = soldshoess;
        notifyDataSetChanged();
    }

    public sold_shoes getSoldShoesAt(int position)
    {
        return soldshoess.get(position);
    }


    public interface OnItemClickListener
    {
        void onItemClick(sold_shoes sold_shoes);
    }

    public void setOnItemClickListemer(soldshoesAdapter.OnItemClickListener listener)
    {
        this.listener = listener;
    }
}
