package ca.cmpt362.projects.myRun2Tabs

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.icu.text.Edits
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class FragmentSettings: Fragment() {

    private lateinit var intent:Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false) //inflate fragment view

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

        //3. use SimpleAdapter for map data:
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

//        change list head and sub item font size and color--
//        https://stackoverflow.com/questions/8380020/android-how-do-i-set-the-textsize-for-a-layout
//        https://stackoverflow.com/questions/5563698/how-to-change-text-color-of-simple-list-item

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
//                intent = Intent(activity, ProfileFormActivity::class.java)
//                startActivity(intent)

            }else{
                Toast.makeText(activity, " click check box $position", Toast.LENGTH_SHORT).show();
                //                todo: click check box
            }
        }
    }

    private fun midListItemsClicked(listViewMid: ListView){

        listViewMid.setOnItemClickListener(){ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0){//unit preference --> choice dialog
                val dialogBuilder = AlertDialog.Builder(requireContext())
                val units = arrayOf("Metric(Kilometers)", "Imperial(Miles)")

                dialogBuilder.setTitle("Unit Preference")
                dialogBuilder.setSingleChoiceItems(units, -1) { dialogInterface, i ->
//               Todo: save selected units[i] ----
                    Toast.makeText(activity, " ${units[i]} selected", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss()
                }
                dialogBuilder.setNegativeButton("Cancel") { dialogInterface, i -> dialogInterface.cancel() }
                dialogBuilder.show() //if these 2 dialog boxes share same builder object, will crashes here. //The specified child already has a parent. You must call removeView() on the child's parent first.
            }

            else if(position == 1){ //comment --> text dialog
                val dialogBuilder = AlertDialog.Builder(requireContext())
                val inflater = layoutInflater

                dialogBuilder.setTitle("Comment")
                val commentDialogLayout = inflater.inflate(R.layout.comment_settings_dialog_box, null)
                val editText: EditText? = commentDialogLayout.findViewById(R.id.commentEditSettings)
                dialogBuilder.setView(commentDialogLayout)
                dialogBuilder.setPositiveButton("OK") { dialogInterface, i -> }//save editText value
                dialogBuilder.setNegativeButton("Cancel") { dialogInterface, i -> dialogInterface.cancel()}
                dialogBuilder.show()
            }
        }
    }

    private fun bottomListItemsClicked(listViewBottom: ListView){
        listViewBottom.setOnItemClickListener(){ parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(activity, " open web page", Toast.LENGTH_SHORT).show();
            intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sfu.ca/computing.html"))
            startActivity(intent)
        }
    }
}
