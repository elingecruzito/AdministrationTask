package com.developbyte.administrationtask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.R;

import java.util.ArrayList;
import java.util.List;

public class ListDaysMountAdapter extends RecyclerView.Adapter<ListDaysMountAdapter.ViewHolder> {

    private List<DaysMountModel> daysMountModelList;

    public void setDaysMountModelList(List<DaysMountModel> daysMountModelList) {
        this.daysMountModelList = daysMountModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_days_mount, parent, false);
        return new ListDaysMountAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtDayMount.setText(daysMountModelList.get(position).getDay()+"");
        holder.txtDayTextMount.setText(daysMountModelList.get(position).getDay_text());
    }

    @Override
    public int getItemCount() {
        return daysMountModelList == null ? 0 : daysMountModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView txtDayMount;
        private AppCompatTextView txtDayTextMount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDayMount = itemView.findViewById(R.id.txt_day_mount);
            txtDayTextMount = itemView.findViewById(R.id.txt_day_text_mount);
        }
    }
}
