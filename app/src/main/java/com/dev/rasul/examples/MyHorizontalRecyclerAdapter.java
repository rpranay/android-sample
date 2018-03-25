package com.dev.rasul.examples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rasul on 3/24/2018.
 */

class MyHorizontalRecyclerAdapter extends RecyclerView.Adapter<MyHorizontalRecyclerAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private MyHorizontalRecyclerAdapter.HorizontalItemClickListener mClickListener;

    public MyHorizontalRecyclerAdapter(Context c) {
        this.mInflater = LayoutInflater.from(c);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.each_horizontal_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String txt = "Item #" + position;
        holder.tv.setText(txt);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iv;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvHorizontalText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null){
                mClickListener.onHorizontalItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface HorizontalItemClickListener{
        void onHorizontalItemClick(View v, int pos);
    }

    void setHorizontalClickListener(MyHorizontalRecyclerAdapter.HorizontalItemClickListener listener){
        this.mClickListener = listener;
    }

}