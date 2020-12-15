package com.example.reseller;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reseller.entities.portfolio;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;


public class portfolioAdapter extends RecyclerView.Adapter<portfolioAdapter.portfolioHolder>
{

    class portfolioHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewSize;

        public portfolioHolder(View itemView)
        {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name_textView);
            textViewPrice = itemView.findViewById(R.id.price_textView);
            textViewSize = itemView.findViewById(R.id.size_textView);
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
                    listener.onItemClick(portfolios.get(position));
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


    private List<portfolio> portfolios = new ArrayList<>(); //array list of shoes
    private OnItemClickListener listener;

    @NonNull
    @Override
    public portfolioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.portfolio_item, parent, false);
        return new portfolioHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull portfolioHolder holder, int position)
    {
        portfolio currentPortfolio = portfolios.get(position);
        holder.textViewPrice.setText("$" + currentPortfolio.getTotalCost());
        holder.textViewName.setText(currentPortfolio.getShoeName());
        holder.textViewSize.setText(" Size: " + currentPortfolio.getShoeSize());


    }

    @Override
    public int getItemCount()
    {
        return portfolios.size();
    }

    public void setPortfolios(List<portfolio> portfolios)
    {
        this.portfolios = portfolios;
        notifyDataSetChanged();
    }

    public portfolio getPortfolioAt(int position)
    {
        return portfolios.get(position);
    }


    public interface OnItemClickListener
    {
        void onItemClick(portfolio portfolio);
    }

    public void setOnItemClickListemer(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}

