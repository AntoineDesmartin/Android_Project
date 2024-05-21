package edu.ihm.vue.signalements_view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import edu.ihm.vue.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgentAddToAgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgentAddToAgendaFragment extends Fragment {

    EditText date;
    EditText start_time;
    EditText end_time;
    Button btn;
    Logger logger = Logger.getLogger("MyLog");

    String titreSignalement;

    public static AgentAddToAgendaFragment newInstance() {
        AgentAddToAgendaFragment fragment = new AgentAddToAgendaFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_agent_add_to_agenda, container, false);

        titreSignalement = AgentSignalementInfoDisplayActivity.signalementTitre;
        logger.info("titre du signalement in fragment : "+ titreSignalement);



        // Find the EditText and Button views
        date = rootView.findViewById(R.id.date);
        start_time = (EditText) rootView.findViewById(R.id.start_time);
        end_time = (EditText) rootView.findViewById(R.id.end_time);
        btn = rootView.findViewById(R.id.btn_add_to_agenda);

        // Set the input type of the EditText views
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(start_time);
            }
        });
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(end_time);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(date);
            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataAreFilled();
                if(!checkEmpty(date) && !checkEmpty(start_time) && !checkEmpty(end_time)){

                    logger.info("Selected date: "+ date.getText().toString() + "\n" +
                            "Selected start time : " + start_time.getText().toString() +"\n" +
                            "Selected end time : "+ end_time.getText().toString());

                    updateCalendar(date,start_time,end_time);
                }


            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


    public void checkDataAreFilled(){
        if(checkEmpty(date)){
            date.setError("Date doit être renseignée");
        }
        if(checkEmpty(start_time)){
            start_time.setError("Heure de début doit être renseignée");
        }
        if(checkEmpty(end_time)){
            end_time.setError("Heure de fin doit être renseignée");
        }

    }

    // Check if the data field is empty
    boolean checkEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    private void showTimePickerDialog(EditText time) {
        final java.util.Calendar cldr = java.util.Calendar.getInstance();
        int hour = cldr.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(java.util.Calendar.MINUTE);

        // time picker dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        String selectedTime = sHour + ":" + sMinute;
                        time.setText(selectedTime);
                    }
                }, hour, minutes, true);
        timePickerDialog.show();
    }

    public void showDatePickerDialog(EditText date) {
        final Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String selectedDate = dayOfMonth +"-"+ monthOfYear +"-"+ year;

                        //String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        date.setText(selectedDate);
                        /*EditText dateEditText = getView().findViewById(R.id.date);
                        dateEditText.setText(selectedDate);*/
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }



    public void updateSignalement(EditText date, EditText start_time, EditText end_time){



    }



    public void updateCalendar(EditText date, EditText start_time, EditText end_time){
        // Analyse de la DateFragment et de l'heure
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");

        // Début et fin de l'événement avec la DateFragment

        String dateInString = date.getText().toString();
        String start_timeInString = start_time.getText().toString();
        String end_timeInString = end_time.getText().toString();


        String startTime = dateInString+"T"+start_timeInString+":00";
        String endTime   = dateInString+"T"+end_timeInString+":00";



        // Lors du clic sur le bouton, l'Intent est démarré -> pour créer un événement dans l'agenda avec l'heure donnée
        Date mStartTime = null;
        Date mEndTime = null;
        try {
            mStartTime = simpleDateFormat.parse(startTime);
            mEndTime = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            logger.info("error parsing date");

            throw new RuntimeException(e);
        }

        Date finalMStartTime = mStartTime;
        Date finalMEndTime = mEndTime;

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", finalMStartTime.getTime());
        intent.putExtra("time", true);
        intent.putExtra("rule", "FREQ=YEARLY");
        intent.putExtra("endTime", finalMEndTime.getTime());
        intent.putExtra("title", titreSignalement);
        startActivity(intent);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

    }
}