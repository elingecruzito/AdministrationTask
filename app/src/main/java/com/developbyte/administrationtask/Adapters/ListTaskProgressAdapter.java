package com.developbyte.administrationtask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ListTaskProgressAdapter extends RecyclerView.Adapter<ListTaskProgressAdapter.ViewHolder>{

    private List<TasksModel> tasksModelList;
    private Context context;

    public ListTaskProgressAdapter(List<TasksModel> tasksModelList, Context context) {
        this.tasksModelList = tasksModelList;
        this.context = context;
    }

    public void setTasksModelList(List<TasksModel> tasksModelList) {
        this.tasksModelList = tasksModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListTaskProgressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_task_list, parent, false);
        return new ListTaskProgressAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTaskProgressAdapter.ViewHolder holder, int position) {
        holder.iconStatusTask.setBackground(context.getResources().getDrawable(R.mipmap.progress));
        holder.txtNameTask.setText(tasksModelList.get(position).getTask());
        holder.txtNameProject.setText(tasksModelList.get(position).getProject());
        holder.txtDateTask.setText(tasksModelList.get(position).getHour());
    }

    @Override
    public int getItemCount() {
        return tasksModelList == null ? 0 : tasksModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iconStatusTask;
        private AppCompatTextView txtNameTask;
        private AppCompatTextView txtDateTask;
        private AppCompatTextView txtNameProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iconStatusTask = itemView.findViewById(R.id.icon_status_task);
            txtNameTask = itemView.findViewById(R.id.txt_name_task);
            txtDateTask = itemView.findViewById(R.id.txt_date_task);
            txtNameProject = itemView.findViewById(R.id.txt_name_project);
        }
    }
}