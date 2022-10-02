package ca.cmpt362.projects.myRun2Tabs

import android.icu.text.Edits
import android.os.Bundle
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

        //display list view with list adapter:
        val listViewTop: ListView = view.findViewById(R.id.listView_settingsTop)

        //create list for top list:
        val topSettings: HashMap<String, String> = HashMap()
        topSettings.put("Name, Email, Class, etc", "User Profile")
        topSettings.put("Privacy Setting", "Posting your records anonymously")
        val listItemsTop:ArrayList<HashMap<String,String>> = ArrayList()


        val it = topSettings.iterator()
        while (it.hasNext()){
            val resultsMap: HashMap<String, String> = HashMap()
            val pair: Map.Entry<String,String> = it.next()
            resultsMap.put("head", pair.key)
            println("-------- ${pair.key}")
            resultsMap.put("sub", pair.value)
            listItemsTop.add(resultsMap);
        }

        listViewTop.adapter = SimpleAdapter(
            requireContext(), listItemsTop, android.R.layout.two_line_list_item,
            arrayOf("head", "sub"), intArrayOf(R.id.profile_head, R.id.profile_sub)
        )




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
}