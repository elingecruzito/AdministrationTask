package com.developbyte.administrationtask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.ArrayList;
import java.util.List;

public class ListNewTaskAdapter extends RecyclerView.Adapter<ListNewTaskAdapter.ViewHolder> {

    private List<TasksModel> listTasksModels;

    public void setTasksModel(TasksModel tasksModel) {
        if(listTasksModels == null){
            listTasksModels = new ArrayList<>();
        }
        listTasksModels.add(tasksModel);
        notifyDataSetChanged();

    }

    public List<TasksModel> getListTasksModels() {
        return listTasksModels;
    }

    public void clearListTaskModels(){
        if(listTasksModels != null){
            listTasksModels.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_new_task_project, parent, false);
        return new ListNewTaskAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNameNewTaskProject.setText(listTasksModels.get(position).getTask());
        holder.txtDateNewTaskProject.setText(listTasksModels.get(position).getDate());
        holder.txtHourNewTaskProject.setText(listTasksModels.get(position).getHour());
        holder.btnDeleteItemTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listTasksModels.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTasksModels == null ? 0 : listTasksModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView txtDateNewTaskProject;
        private AppCompatTextView txtHourNewTaskProject;
        private AppCompatTextView txtNameNewTaskProject;
        private AppCompatImageView btnDeleteItemTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDateNewTaskProject = itemView.findViewById(R.id.txt_date_new_task_project);
            txtHourNewTaskProject = itemView.findViewById(R.id.txt_hour_new_task_project);
            txtNameNewTaskProject = itemView.findViewById(R.id.txt_name_new_task_project);
            btnDeleteItemTask = itemView.findViewById(R.id.btn_delete_item_task);
        }
    }
}
