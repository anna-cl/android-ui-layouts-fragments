package ca.cmpt362.projects.myRun2Tabs

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
// important: Adapter View. See lecture Sept. 23
class ListViewLayoutActivity : AppCompatActivity() {
    private val FACULTY = arrayOf(
        "Chris Bailey-Kellogg",
        "Devin Balkcom", "Andrew Campbell", "Michael Casey",
        "Amit Chakrabarti", "Thomas H. Cormen ",
        "Robert L. (Scot) Drysdale, III", "Hany Farid", "Lisa Fleischer",
        "Gevorg Grigoryan", "Prasad Jayanti", "David Kotz", "Lorie Loeb",
        "Fabio Pellacini", "Daniel Rockmore", "Sean Smith",
        "Lorenzo Torresani", "Peter Winkler", "Emily Whiting", "Xia Zhou"
    )

    private lateinit var myListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_layout)


// Sept. 23 - 1.1 lecture:
// ----- List adapter to replace a for loop, ListView is already with scrollview:
        myListView = findViewById(R.id.listView)

        myListView.adapter = ArrayAdapter(
            this, android.R.layout.simple_expandable_list_item_1, FACULTY)
    }

}

