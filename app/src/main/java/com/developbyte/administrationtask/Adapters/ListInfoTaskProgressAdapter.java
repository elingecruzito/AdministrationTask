package com.developbyte.administrationtask.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ListInfoTaskProgressAdapter extends RecyclerView.Adapter<ListInfoTaskProgressAdapter.ViewHolder>{

    private List<TasksModel> tasksModels;
    private Context context;
    private AlertDialog.Builder alertBuilder;

    public ListInfoTaskProgressAdapter(List<TasksModel> tasksModels, Context context) {
        this.tasksModels = tasksModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ListInfoTaskProgressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_task_info_list, parent, false);
        return new ListInfoTaskProgressAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListInfoTaskProgressAdapter.ViewHolder holder, int position) {
        holder.imgInfoTask.setBackground(context.getResources().getDrawable(R.mipmap.progress_task));
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
