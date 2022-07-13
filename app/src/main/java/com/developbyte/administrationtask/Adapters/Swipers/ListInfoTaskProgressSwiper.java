package com.developbyte.administrationtask.Adapters.Swipers;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

import java.util.List;

public class ListInfoTaskProgressSwiper extends ItemTouchHelper.Callback {

    private Context context;
    private List<TasksModel> tasksModelList;
    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    public ListInfoTaskProgressSwiper(Context context, List<TasksModel> tasksModelList, IInfoProject.IInfoProjectRepresentationDelegate representationDelegate, Utilerias utilerias) {
        this.context = context;
        this.tasksModelList = tasksModelList;
        this.representationDelegate = representationDelegate;
        this.utilerias = utilerias;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if( direction == ItemTouchHelper.RIGHT ){
            representationDelegate.updateStatusTask(tasksModelList.get(viewHolder.getPosition()).getId_task());
        }else if( direction == ItemTouchHelper.LEFT ){
            representationDelegate.deleteTask(tasksModelList.get(viewHolder.getPosition()).getId_task());
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            new SwiperChildDraw(c, viewHolder, dX, utilerias, context);

        }else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }



}
