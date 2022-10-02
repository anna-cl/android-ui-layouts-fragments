package ca.cmpt362.projects.myRun2Tabs

import android.graphics.Color
import android.icu.text.Edits
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class FragmentSettings: Fragment() {

    private val LISTITEMS_TOP = arrayOf(
        "Name, Email, Class, etc",
        "Privacy Setting"
    )
    private val LISTITEMS_MID = arrayOf(
        "Unit Preference",
        "Comments"
    )

    private val LISTITEMS_BOTTOM = arrayOf(
        "Webpage"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        setupListViewAdapter(view)

        return view
    }

    private fun setupListViewAdapter(view: View) {

        //1.display list view with list adapter:
        val listViewTop: ListView = view.findViewById(R.id.listView_settingsTop)
        val listViewMid: ListView = view.findViewById(R.id.listView_settingsMid)
        val listViewBottom: ListView = view.findViewById(R.id.listView_settingsBottom)

        //2.create data map for top list:
        val twoRowDataTop = mutableListOf(
            mapOf("head" to "Name, Email, Class, etc", "sub" to "User Profile" ),
            mapOf("head" to "Privacy Setting", "sub" to "Posting your records anonymously")
        )
        val twoRowDataMid = mutableListOf(
            mapOf("head" to "Unit Preference", "sub" to "Select the units"),
            mapOf("head" to "Comments", "sub" to "Please enter your comments")
        )
        val twoRowDataBottom = mutableListOf(
            mapOf("head" to "Webpage", "sub" to "https://www.sfu.ca/computing.html"),
        )

        //3. simple adapter for map data:
        listViewTop.adapter = SimpleAdapter(
            requireContext(), twoRowDataTop,android.R.layout.simple_list_item_2,
            arrayOf("head", "sub"), intArrayOf(android.R.id.text1, android.R.id.text2) //text1 text2 ids are in default simple_list_item_2.xml, no need to create an list item layout
        )

        listViewMid.adapter = SimpleAdapter(
            requireContext(), twoRowDataMid,android.R.layout.simple_list_item_2,
            arrayOf("head", "sub"), intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        listViewBottom.adapter = SimpleAdapter(
            requireContext(), twoRowDataBottom,android.R.layout.simple_list_item_2,
            arrayOf("head", "sub"), intArrayOf(android.R.id.text1, android.R.id.text2)
        )
//        change list head and sub item font size and color--------

        //4.Click each list item:
        topListItemsClicked(listViewTop)
        midListItemsClicked(listViewMid)
        bottomListItemsClicked(listViewBottom)

    }

    private fun topListItemsClicked(listViewTop: ListView){//click on each list item and open dialog or activity
        //click on each item on the list:
        listViewTop.setOnItemClickListener() { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) { //user profile
                Toast.makeText(activity, " open profile form $position", Toast.LENGTH_SHORT).show();
//                todo: open profile form
            }else{
                Toast.makeText(activity, " click check box $position", Toast.LENGTH_SHORT).show();
                //                todo: click check box
            }
        }
    }

    private fun midListItemsClicked(listViewMid: ListView){
        listViewMid.setOnItemClickListener(){ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0){//unit preference --> dialog box
                Toast.makeText(activity, " dialog box opened", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(activity, " comment box", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun bottomListItemsClicked(listViewBottom: ListView){
        listViewBottom.setOnItemClickListener(){ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(activity, " open web page", Toast.LENGTH_SHORT).show();
        }
    }
}
