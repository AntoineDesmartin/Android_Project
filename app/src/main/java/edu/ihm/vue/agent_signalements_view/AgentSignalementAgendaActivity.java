package edu.ihm.vue.agent_signalements_view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import edu.ihm.vue.R;
import edu.ihm.vue.main_activities.AgentActivity;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.models.Signalement;

public class AgentSignalementAgendaActivity extends AppCompatActivity {

    EditText date;
    EditText start_time;
    Button btn;
    Signalement signalement;

    Logger logger = Logger.getLogger("MyLog");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_signalement_agenda);
        Intent intent = getIntent();
        signalement = getIntent().getParcelableExtra("signalement");

        logger.info("titre du signalement in agendaActivity : " + signalement.toString());

        // Find the EditText and Button views
        date = findViewById(R.id.date);
        start_time = (EditText) findViewById(R.id.start_time);
        btn = findViewById(R.id.btn_add_to_agenda);

        // Set the input type of the EditText views
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(start_time);
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
                if (!checkEmpty(date) && !checkEmpty(start_time)) {

                    logger.info("Selected date: " + date.getText().toString() + "\n" +
                            "Selected start time : " + start_time.getText().toString() + "\n");

                    assignToMe(date, start_time);
                }
            }
        });
    }


    public void checkDataAreFilled() {
        if (checkEmpty(date)) {
            date.setError("Date doit être renseignée");
        }
        if (checkEmpty(start_time)) {
            start_time.setError("Heure de début doit être renseignée");
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String selectedDate = dayOfMonth + "-" + monthOfYear + "-" + year;

                        //String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        date.setText(selectedDate);
                        /*EditText dateEditText = getView().findViewById(R.id.date);
                        dateEditText.setText(selectedDate);*/
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void assignToMe(EditText date, EditText start_time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        String dateInString = date.getText().toString();
        String start_timeInString = start_time.getText().toString();

        String startTime = dateInString + "T" + start_timeInString + ":00";

        Date mStartTime = null;

        try {
            mStartTime = simpleDateFormat.parse(startTime);

        } catch (ParseException e) {
            logger.info("error parsing date");
            throw new RuntimeException(e);
        }

        Date finalMStartTime = mStartTime;
        this.signalement.setIntervention(finalMStartTime);
        this.signalement.setIntervenant(AgentActivity.user.getId());

        String agentId = AgentActivity.user.getId();

        int indice = -1;
        for (int i = 0; i < Signalements.signalementsMock.size(); i++) {
            Signalement currentSignalement = Signalements.signalementsMock.get(i);
            if (currentSignalement.getAddress().equals(signalement.getAddress()) &&
                    currentSignalement.getAuteur().equals(signalement.getAuteur()) &&
                    currentSignalement.getNiveau() == signalement.getNiveau() &&
                    currentSignalement.getCity().equals(signalement.getCity()) &&
                    currentSignalement.getZipCode().equals(signalement.getZipCode()) &&
                    currentSignalement.getDescription().equals(signalement.getDescription())) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            Signalements.signalementsMock.get(indice).setIntervention(finalMStartTime);
            Signalements.signalementsMock.get(indice).setIntervenant(agentId);

            signalement.setIntervenant(agentId);
            logger.info("Intervention date to send: " + signalement.getInterventioninFull());

            finish();
        } else {
            logger.warning("No matching signalement found.");
        }
        logger.info("Intervention date to send : " + this.signalement.getInterventioninFull());
        Intent intent=new Intent(getApplicationContext(), AgentSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement",(Parcelable) signalement);
        startActivity(intent);
    }
}