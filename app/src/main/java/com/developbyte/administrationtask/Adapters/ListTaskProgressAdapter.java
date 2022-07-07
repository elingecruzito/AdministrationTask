package com.developbyte.administrationtask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Home.IHome;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ListTaskProgressAdapter extends RecyclerView.Adapter<ListTaskProgressAdapter.ViewHolder>{

    private List<TasksModel> tasksModelList;
    private Context context;
    private IHome.IHomeRepresentationDelegate representationDelegate;

    public ListTaskProgressAdapter(List<TasksModel> tasksModelList, Context context, IHome.IHomeRepresentationDelegate representationDelegate) {
        this.tasksModelList = tasksModelList;
        this.context = context;
        this.representationDelegate = representationDelegate;
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
        holder.lyItemTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                representationDelegate.showInfoProject(tasksModelList.get(position).getId_project());
            }
        });
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

        private RelativeLayout lyItemTask;
        private ImageView iconStatusTask;
        private AppCompatTextView txtNameTask;
        private AppCompatTextView txtDateTask;
        private AppCompatTextView txtNameProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lyItemTask = itemView.findViewById(R.id.ly_item_task);
            iconStatusTask = itemView.findViewById(R.id.icon_status_task);
            txtNameTask = itemView.findViewById(R.id.txt_name_task);
            txtDateTask = itemView.findViewById(R.id.txt_date_task);
            txtNameProject = itemView.findViewById(R.id.txt_name_project);
        }
    }
}
