package com.dev.rasul.examples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasul on 3/14/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<ProductItem> mItem;
    private ItemClickListener mClickListener;

    public MyRecyclerViewAdapter(Context c, ArrayList<ProductItem> pItem) {
        this.mInflater = LayoutInflater.from(c);
        mItem = pItem;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.each_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(mItem.get(position).item_name);
        holder.tvPrice.setText("Rs. "+mItem.get(position).item_price+"");
        holder.tvRating.setText(mItem.get(position).item_rating);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvPrice, tvRating;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_title);
            tvPrice = itemView.findViewById(R.id.item_price);
            tvRating = itemView.findViewById(R.id.item_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }

    void setClickListener(ItemClickListener listener) {
        this.mClickListener = listener;
    }
}
