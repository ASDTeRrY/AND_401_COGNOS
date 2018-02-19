package com.miramicodigo.pickersdatetime;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private TimePickerDialog timePickerDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return null;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {

    }

}
