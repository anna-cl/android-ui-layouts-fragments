package ca.cmpt362.projects.myRun2Tabs

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class StartActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        listAdapterView()

    }

    private fun listAdapterView(){
        //display list view with list adapter:
        listView = findViewById(R.id.listView_startActivity)

        listView.adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, LISTITEMS)

        //click on each item on the list:
        listView.setOnItemClickListener(){ parent:AdapterView<*>, view:View, position: Int, id: Long ->
            if (position == 0){
                Toast.makeText(this, "Item clicked! position $position", Toast.LENGTH_SHORT).show();
                dateClicked() //----------

            }else if(position == 1){
                Toast.makeText(this, "Item clicked! position $position", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun dateClicked(){
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this, this,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

}