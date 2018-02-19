package com.miramicodigo.pickersdatetime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog datePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return null;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {

    }

    public Calendar maxRangeDatePicker() {
        String aTime = "2017-12-25";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar cal = null;
        try {
            cal = Calendar.getInstance();
            cal.setTime(sdf.parse(aTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public Calendar minRangeDatePicker() {
        String aTime = "2017-12-07";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar cal = null;
        try {
            cal = Calendar.getInstance();
            cal.setTime(sdf.parse(aTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
