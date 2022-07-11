package com.developbyte.administrationtask.Widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.developbyte.administrationtask.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.security.auth.callback.Callback;

public class Utilerias {


    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    private Activity activity;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private View viewModal;
    private AppCompatImageView imgIconTypeModal;
    private AppCompatTextView lblMessageModal;
    private AppCompatButton btnCancelModal;
    private AppCompatButton btnConfirmationModal;


    public Utilerias() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        generateViewModal();
    }

    public DatePickerDialog getDatePickerDialog(Context context, AppCompatEditText editText) {
        return new DatePickerDialog(context, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                editText.setText(dateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
    }

    public TimePickerDialog getTimePickerDialog(Context context, AppCompatEditText editText) {
        return new TimePickerDialog(context, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                editText.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
    }

    private void generateViewModal(){
        dialogBuilder = new AlertDialog.Builder(activity);
        viewModal = activity.getLayoutInflater().inflate(R.layout.widget_modal_messages, null);

        imgIconTypeModal = viewModal.findViewById(R.id.img_icon_type_modal);
        lblMessageModal = viewModal.findViewById(R.id.lbl_message_modal);
        btnConfirmationModal = viewModal.findViewById(R.id.btn_confirmation_modal);
        btnCancelModal = viewModal.findViewById(R.id.btn_cancel_modal);
        btnCancelModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog = dialogBuilder.setView(viewModal).create();
    }

    public void showMessageConfirmation(int message, Runnable runnable){
        imgIconTypeModal.setBackground(activity.getDrawable(R.mipmap.icon_info_96));
        lblMessageModal.setText(activity.getString(message));
        btnCancelModal.setVisibility(View.VISIBLE);
        btnConfirmationModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                runnable.run();
            }
        });
        alertDialog.show();
    }

    public void showMessageError(int message){
        imgIconTypeModal.setBackground(activity.getDrawable(R.mipmap.icon_cancelar_96));
        lblMessageModal.setText(activity.getString(message));
        btnCancelModal.setVisibility(View.GONE);
        btnConfirmationModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public String randomString(){
        String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random RANDOM = new Random();
        StringBuilder sb = new StringBuilder(RANDOM.nextInt(999));

        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }
        return sb.toString();
    }


}
