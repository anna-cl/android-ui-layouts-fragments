package ca.cmpt362.projects.myRun2Tabs

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
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
        listView = findViewById(R.id.listView_startActivity)
        listView.adapter = ArrayAdapter(
            this, android.R.layout.simple_expandable_list_item_1, LISTITEMS)
    }

}