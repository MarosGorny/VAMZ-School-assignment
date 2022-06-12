package com.example.traininglog.gorny.traininglog.addActivity

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.traininglog.gorny.traininglog.data.EnumAktivity
import com.example.traininglog.gorny.traininglog.R
import java.text.SimpleDateFormat
import java.util.*

const val TRAINING_LOG_DISTANCE = "distance"
const val LOG_ACTIVITY = "log activity"
class AddActivityLog: AppCompatActivity() {


    lateinit var radioButton: RadioButton
    lateinit var addDistance: EditText
    lateinit var logType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_training_log_layout)

        findViewById<Button>(R.id.dateButton).setOnClickListener {
            //TODO spravit aby som mohol vyklikat datum
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker,year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                findViewById<Button>(R.id.dateButton).text = SimpleDateFormat("dd.MM.yy").format(cal.time)
            }
            DatePickerDialog(this,dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        findViewById<Button>(R.id.timeButton).setOnClickListener {
            //TODO spravit aby som si mohol vyklikat cas
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                findViewById<Button>(R.id.timeButton).text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        findViewById<Button>(R.id.button_done).setOnClickListener {

            val selectedId = findViewById<RadioGroup>(R.id.activity_options).checkedRadioButtonId
            logType = when (selectedId) {
                R.id.option_run -> "Run"
                R.id.option_bike -> "Bike"
                else -> "Swim"
            }

            addTrainingLog()
        }

        addDistance = findViewById(R.id.distance_input_number)

    }


    /* The onClick action for the done button. Closes the activity and returns the new training name
    and description as part of the intent. If the distance is missing, the result is set
    to cancelled. */
    private fun addTrainingLog() {


        val resultIntent = Intent()
        if(addDistance.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED,resultIntent)
        } else {
            val distance = addDistance.text.toString()
            resultIntent.putExtra(TRAINING_LOG_DISTANCE,distance)
            resultIntent.putExtra(LOG_ACTIVITY,logType)
            setResult(Activity.RESULT_OK,resultIntent)
        }
        finish()
    }

}