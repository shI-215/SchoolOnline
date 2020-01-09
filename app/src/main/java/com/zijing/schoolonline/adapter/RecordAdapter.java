package com.zijing.schoolonline.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zijing.schoolonline.R;
import com.zijing.schoolonline.bean.Record;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<Record> recordList;

    public RecordAdapter(List<Record> recordList) {
        this.recordList = recordList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_account;
        TextView tv_amount;
        TextView tv_time;

        public ViewHolder(View view) {
            super(view);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_account = (TextView) view.findViewById(R.id.tv_account);
            tv_amount = (TextView) view.findViewById(R.id.tv_amount);
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
        Record record = recordList.get(position);
        Log.v("RecordAdapter", record.toString());
        holder.tv_id.setText(record.getRid() + "");
        holder.tv_account.setText(record.getAccount());
        holder.tv_amount.setText(record.getAmount() + "");
        holder.tv_time.setText(record.getTime());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
