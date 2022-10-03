package ca.cmpt362.projects.myRun2Tabs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ManualActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private val LISTITEMS = arrayOf(
        "Date",
        "Time",
        "Duration",
        "Distance",
        "Calories",
        "Heart Rate",
        "Comment"
    )

    private lateinit var listView: ListView
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual)

        manualListAdapterView() //display || date,time,duration,distance,calories,heartrate,comment ||

    }

    private fun manualListAdapterView(){
        //display list view with list adapter:
        listView = findViewById(R.id.listView_startActivity)

        listView.adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, LISTITEMS)

        //click on each item on the list:
        listView.setOnItemClickListener(){ parent:AdapterView<*>, view:View, position: Int, id: Long ->
            if (position == 0){ // "Date" --> calendar
//                Toast.makeText(this, "Calendar clicked! position $position", Toast.LENGTH_SHORT).show();
                dateClicked()

            }else if(position == 1){ //"Time" --> clock
                timeClicked()
            }else { //"Duration", "Distance", "Heart Rate", "Calories", "Comment" --> number,text input dialog boxes
                otherDialogClicked(position)
            }
        }
    }

//  ========================  Calendar ========================
    private fun dateClicked(){

        val datePickerDialog = DatePickerDialog(
            this, this,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
//    default function for calendar "OK" respond
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        Toast.makeText(this, "$year, ${month + 1}, $day", Toast.LENGTH_SHORT).show()
    }


//  ======================== Clock ========================
    private fun timeClicked(){
        val timePickerDialog = TimePickerDialog(
            this, this, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false)
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        Toast.makeText(this, "$hour, $minute", Toast.LENGTH_SHORT).show()
    }


//  ======================== Other Edit Text Dialog Boxes ========================

    private fun otherDialogClicked(position:Int) {
        val textDialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater

        if (position == 2) {
            textDialogBuilder.setTitle("Duration")
            val durationDialogLayout = inflater.inflate(R.layout.duration_dialog_box, null)
            val editText: EditText? = durationDialogLayout.findViewById(R.id.durationEditDecimal)
            textDialogBuilder.setView(durationDialogLayout)
            textDialogBuilder.setPositiveButton("OK") { dialogInterface, i -> //Toast.makeText(this, "Duration ${editText.getText().toString()}!", Toast.LENGTH_SHORT).show()
            }
        } else if (position == 3) {
            textDialogBuilder.setTitle("Distance")
            val distanceDialogLayout = inflater.inflate(R.layout.duration_dialog_box, null)
            val editText: EditText? = distanceDialogLayout.findViewById(R.id.distanceEditDecimal)
            textDialogBuilder.setView(distanceDialogLayout)
            textDialogBuilder.setPositiveButton("OK") { dialogInterface, i -> //save editText value
            }
        }else if (position == 4){
            textDialogBuilder.setTitle("Calories")
            val caloriesDialogLayout = inflater.inflate(R.layout.calories_dialog_box, null)
            val editText: EditText? = caloriesDialogLayout.findViewById(R.id.caloriesEditNum)
            textDialogBuilder.setView(caloriesDialogLayout)
            textDialogBuilder.setPositiveButton("OK") { dialogInterface, i -> //save editText value
            }
        }else if (position == 5){
            textDialogBuilder.setTitle("Heart Rate")
            val heartRateDialogLayout = inflater.inflate(R.layout.heartrate_dialog_box, null)
            val editText: EditText? = heartRateDialogLayout.findViewById(R.id.heartrateEditNum)
            textDialogBuilder.setView(heartRateDialogLayout)
            textDialogBuilder.setPositiveButton("OK") { dialogInterface, i -> //save editText value
            }
        }else if (position == 6){
            textDialogBuilder.setTitle("Comment")
            val commentDialogLayout = inflater.inflate(R.layout.comment_dialog_box, null)
            val editText: EditText? = commentDialogLayout.findViewById(R.id.commentEditText)
            textDialogBuilder.setView(commentDialogLayout)
            textDialogBuilder.setPositiveButton("OK") { dialogInterface, i -> //save editText value
            }
        }else{
            println("Wrong position!")
        }

        textDialogBuilder.setNegativeButton("Cancel") { dialogInterface, i -> }
        textDialogBuilder.show()

    }


    fun saveButtonClickedManual(view: View){
        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //TODO: save data---
    }

    fun cancelButtonClickedManual(view: View){
        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }




}