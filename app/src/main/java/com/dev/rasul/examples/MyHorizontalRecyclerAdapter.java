package com.dev.rasul.examples;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasul on 3/24/2018.
 */

class MyHorizontalRecyclerAdapter extends RecyclerView.Adapter<MyHorizontalRecyclerAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private MyHorizontalRecyclerAdapter.HorizontalItemClickListener mClickListener;
    private int mType = 0;
    private List<String> mList;

    public MyHorizontalRecyclerAdapter(Context c, List<String> list, int type) {
        this.mInflater = LayoutInflater.from(c);
        mType = type;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.each_horizontal_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
                mClickListener.onHorizontalItemClick(view, getAdapterPosition(), mType);
            }
        }
    }

    public interface HorizontalItemClickListener{
        void onHorizontalItemClick(View v, int pos, int type);
    }

    void setHorizontalClickListener(MyHorizontalRecyclerAdapter.HorizontalItemClickListener listener){
        this.mClickListener = listener;
    }

}