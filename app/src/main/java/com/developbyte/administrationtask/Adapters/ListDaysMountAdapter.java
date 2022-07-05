package com.developbyte.administrationtask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Home.IHome;
import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.R;

import java.util.ArrayList;
import java.util.List;

public class ListDaysMountAdapter extends RecyclerView.Adapter<ListDaysMountAdapter.ViewHolder> {

    private List<DaysMountModel> daysMountModelList;
    private Context context;
    private int indexToday = -1;
    private int month;
    private IHome.IHomeRepresentationDelegate representationDelegate;

    public ListDaysMountAdapter(Context context, IHome.IHomeRepresentationDelegate representationDelegate) {
        this.context = context;
        this.representationDelegate = representationDelegate;
    }

    public void setDaysMountModelList(List<DaysMountModel> daysMountModelList, int month) {
        this.daysMountModelList = daysMountModelList;
        this.month = month;
        if(indexToday < 0) {
            for (int i = 0; i < daysMountModelList.size(); i++) {
                if (daysMountModelList.get(i).isToday()) {
                    indexToday = i;
                }
            }
        }
        notifyDataSetChanged();
    }

    public int getIndexToday() {
        return indexToday;
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

        holder.cardDayMonth.setCardBackgroundColor(context.getResources().getColor(R.color.white));
        holder.txtDayMount.setTextColor(context.getResources().getColor(R.color.color_text));
        holder.txtDayTextMount.setTextColor(context.getResources().getColor(R.color.color_text));

        if (daysMountModelList.get(position).isSelected()){
            holder.cardDayMonth.setCardBackgroundColor(context.getResources().getColor(R.color.background_form_buttons));
            holder.txtDayMount.setTextColor(context.getResources().getColor(R.color.white));
            holder.txtDayTextMount.setTextColor(context.getResources().getColor(R.color.white));
        }
        if(daysMountModelList.get(position).isToday()) {
            holder.cardDayMonth.setCardBackgroundColor(context.getResources().getColor(R.color.blue_background));
            holder.txtDayMount.setTextColor(context.getResources().getColor(R.color.white));
            holder.txtDayTextMount.setTextColor(context.getResources().getColor(R.color.white));
        }

        holder.txtDayMount.setText(daysMountModelList.get(position).getDay()+"");
        holder.txtDayTextMount.setText(daysMountModelList.get(position).getDay_text());

        holder.cardDayMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                indexToday = position;
                representationDelegate.getDaysOfCurrentMount(month);


                for(int i = 0; i < daysMountModelList.size(); i++){
                    daysMountModelList.get(i).setSelected(false);
                    if( i == position){
                        daysMountModelList.get(i).setSelected(true);
                    }
                }
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return daysMountModelList == null ? 0 : daysMountModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardDayMonth;
        private AppCompatTextView txtDayMount;
        private AppCompatTextView txtDayTextMount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardDayMonth = itemView.findViewById(R.id.card_day_month);
            txtDayMount = itemView.findViewById(R.id.txt_day_mount);
            txtDayTextMount = itemView.findViewById(R.id.txt_day_text_mount);
        }
    }
}
