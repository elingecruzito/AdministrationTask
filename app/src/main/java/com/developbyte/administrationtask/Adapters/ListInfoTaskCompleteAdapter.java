package com.developbyte.administrationtask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ListInfoTaskCompleteAdapter extends RecyclerView.Adapter<ListInfoTaskCompleteAdapter.ViewHolder>{

    private List<TasksModel> tasksModels;
    private Context context;

    public ListInfoTaskCompleteAdapter(List<TasksModel> tasksModels, Context context) {
        this.tasksModels = tasksModels;
        this.context = context;
    }

    public void setTasksModels(List<TasksModel> tasksModels) {
        this.tasksModels = tasksModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListInfoTaskCompleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_task_info_list, parent, false);
        return new ListInfoTaskCompleteAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListInfoTaskCompleteAdapter.ViewHolder holder, int position) {
        holder.imgInfoTask.setBackground(context.getResources().getDrawable(R.mipmap.check));
        holder.txtNameInfoTask.setText(tasksModels.get(position).getTask());
        holder.txtDateInfoTask.setText(tasksModels.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return tasksModels == null ? 0 : tasksModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView imgInfoTask;
        private AppCompatTextView txtNameInfoTask;
        private AppCompatTextView txtDateInfoTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgInfoTask = itemView.findViewById(R.id.img_info_task);
            txtNameInfoTask = itemView.findViewById(R.id.txt_name_info_task);
            txtDateInfoTask = itemView.findViewById(R.id.txt_date_info_task);
        }
    }
}
