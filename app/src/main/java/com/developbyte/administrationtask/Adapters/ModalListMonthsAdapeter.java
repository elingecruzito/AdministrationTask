package com.developbyte.administrationtask.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Model.MonthsModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ModalListMonthsAdapeter extends RecyclerView.Adapter<ModalListMonthsAdapeter.ViewHolder>{

    private List<MonthsModel> modelList;
    private Context context;
    private int indexSelected;

    public ModalListMonthsAdapeter(List<MonthsModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
        for(int i = 0; i < modelList.size(); i++){
            if(modelList.get(i).isActive()){
                indexSelected = i;
            }
        }
    }

    public int getIndexSelected() {
        return indexSelected;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ModalListMonthsAdapeter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_items_list_modal_monts, parent, false);
        return new ModalListMonthsAdapeter.ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ModalListMonthsAdapeter.ViewHolder holder, int position) {
        holder.lyModalMonthYear.setBackgroundColor(
                context.getColor(
                        modelList.get(position).isActive() ?
                                R.color.blue_background :
                                R.color.white
                )
        );
        holder.lyModalMonthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedOption(position);
            }
        });
        holder.lblValueItemModalMonths.setText(modelList.get(position).getMonth());
        holder.lblValueItemModalMonths.setTextColor(context.getColor(
                modelList.get(position).isActive() ?
                        R.color.white :
                        R.color.black
        ));
        holder.lblValueItemModalMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedOption(position);
            }
        });
    }

    private void setSelectedOption(int position){
        for(int i = 0; i < modelList.size(); i++){
            if( i == position){
                indexSelected = position + 1;
                modelList.get(i).setActive(true);
            }else {
                modelList.get(i).setActive(false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout lyModalMonthYear;
        private AppCompatTextView lblValueItemModalMonths;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lyModalMonthYear = itemView.findViewById(R.id.ly_modal_month_year);
            lblValueItemModalMonths = itemView.findViewById(R.id.lbl_value_item_modal_months);
        }
    }
}
