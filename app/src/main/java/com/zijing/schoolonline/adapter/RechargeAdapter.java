package com.zijing.schoolonline.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zijing.schoolonline.R;

import java.util.List;

public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.ViewHolder> {
    private List<String> list;
    private int selectIndex = -1;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rView;
        TextView tv_item;

        public ViewHolder(View view) {
            super(view);
            rView = view;
            tv_item = (TextView) view.findViewById(R.id.tv_item);
        }
    }

    public RechargeAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RechargeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.rView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getPosition();
//                selectIndex = position;
                setSelectedIndex(position);
                Toast.makeText(v.getContext(), list.get(position) + "" + selectIndex, Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RechargeAdapter.ViewHolder holder, int position) {
        holder.tv_item.setText(list.get(position));
        if (selectIndex == position) {
            holder.tv_item.setBackgroundColor(Color.RED);           //选中状态
        } else {                                                            //非选中状态
            holder.tv_item.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setSelectedIndex(int position) {
        this.selectIndex = position;
        notifyDataSetChanged();
    }

    public int getPositionIndex() {
        return selectIndex;
    }
}
