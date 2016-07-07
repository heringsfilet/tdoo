package de.hering.tdoo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

import de.hering.tdoo.model.Todo;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    public Todo mItem;
    public TextView dateText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        if(mItem.dueDate != null){
            c.setTime(mItem.dueDate);
        }
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, monthOfYear,dayOfMonth);
    }

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(mItem.dueDate);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.YEAR,year);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        mItem.dueDate = c.getTime();
        dateText.setText(mItem.getDueDateDateString());
    }
}