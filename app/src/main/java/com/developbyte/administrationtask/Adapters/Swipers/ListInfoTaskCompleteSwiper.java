package com.developbyte.administrationtask.Adapters.Swipers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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

public class ListInfoTaskCompleteSwiper extends ItemTouchHelper.Callback {

    private Context context;
    private List<TasksModel> tasksModelList;
    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    private Bitmap icon;
    private View itemView;
    private static final Paint p = new Paint();
    public static final float ALPHA_FULL = 1.0f;

    public ListInfoTaskCompleteSwiper(Context context, List<TasksModel> tasksModelList, IInfoProject.IInfoProjectRepresentationDelegate representationDelegate, Utilerias utilerias) {
        this.context = context;
        this.tasksModelList = tasksModelList;
        this.representationDelegate = representationDelegate;
        this.utilerias = utilerias;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if( direction == ItemTouchHelper.LEFT ){
            representationDelegate.deleteTask(tasksModelList.get(viewHolder.getPosition()).getId_task());
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            itemView = viewHolder.itemView;
            if (dX < 0)  {
                icon = utilerias.drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_white));
                p.setColor(context.getResources().getColor(R.color.btn_color_red));
                c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                        (float) itemView.getRight(), (float) itemView.getBottom(), p);
                c.drawBitmap(icon,
                        (float) itemView.getRight() - utilerias.convertDpToPx(16, context) - icon.getWidth(),
                        (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight())/2,
                        p);
            }
            viewHolder.itemView.setAlpha(ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth());
            viewHolder.itemView.setTranslationX(dX);

        }else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
}
