package com.zijing.schoolonline.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.bean.Recharge;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<Recharge> recordList;

    public RecordAdapter(List<Recharge> recordList) {
        this.recordList = recordList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_account;
        TextView tv_amount;
        TextView tv_type;
        TextView tv_time;

        public ViewHolder(View view) {
            super(view);
            tv_account = (TextView) view.findViewById(R.id.tv_account);
            tv_amount = (TextView) view.findViewById(R.id.tv_amount);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
        }
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, null, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        Recharge recharge = recordList.get(position);
        Log.v("RecordAdapter", recharge.toString());
        holder.tv_account.setText(recharge.getRechargeAccount());
        holder.tv_amount.setText("+" + recharge.getRechargeMoney());
        if (TextUtils.equals(recharge.getRechargeType(), "电费")) {
            holder.tv_type.setBackgroundResource(R.drawable.ic_round_elect);
        } else if (TextUtils.equals(recharge.getRechargeType(), "水费")) {
            holder.tv_type.setBackgroundResource(R.drawable.ic_round_water);
        } else if (TextUtils.equals(recharge.getRechargeType(), "空调")) {
            holder.tv_type.setBackgroundResource(R.drawable.ic_round_air);
        }
        holder.tv_type.setText(recharge.getRechargeType());
        holder.tv_time.setText(recharge.getRechargeDate());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
