package com.dev.rasul.examples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rasul on 3/14/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MyRecyclerViewAdapter(Context c){
        this.mInflater = LayoutInflater.from(c);
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.each_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        String txt = "Item #" + position;
        holder.tv.setText(txt);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvGridName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    void setClickListener(ItemClickListener listener){
        this.mClickListener = listener;
    }
}
