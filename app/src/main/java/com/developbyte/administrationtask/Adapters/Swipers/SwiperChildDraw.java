package com.developbyte.administrationtask.Adapters.Swipers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

public final class SwiperChildDraw {

    private Bitmap icon;
    private View itemView;
    private static final Paint p = new Paint();
    public static final float ALPHA_FULL = 1.0f;

    public SwiperChildDraw(Canvas c, RecyclerView.ViewHolder viewHolder, float dX, Utilerias utilerias, Context context) {
        itemView = viewHolder.itemView;
        if (dX > 0) {
            icon = utilerias.drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.ic_baseline_post_add));
            p.setColor(context.getResources().getColor(R.color.blue_background));
            c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                    (float) itemView.getBottom(), p);
            c.drawBitmap(icon,
                    (float) itemView.getLeft() + utilerias.convertDpToPx(16, context),
                    (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight())/2,
                    p);
        }else {
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
    }
}
