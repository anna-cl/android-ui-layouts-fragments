package ca.cmpt362.projects.myRun2Tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class FragmentStart: Fragment() {
    private lateinit var selectedItem: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        selectedItem = view.findViewById(R.id.spinner_inputType) //fragment view object to find spinner items: "Manual Entry", "GPS", "Automatic"

        val startButton: Button = view.findViewById(R.id.start_button)
        startButton.setOnClickListener() { view: View ->
            openStartActivity()
        }

        return view
    }

    // spinner box on start fragment
    private fun getSpinnerPosition(): Int {
        return selectedItem.selectedItemPosition
    }

    private fun openStartActivity(){
        lateinit var intent: Intent
        if (getSpinnerPosition()==0) { // spinner --> "Manual Entry" --> click start, open start activity
            intent = Intent(activity, ManualActivity::class.java)
            startActivity(intent)
        }else{ // spinner --> "GPS" "Automatic" --> click start, open Map activity
            intent = Intent(activity, MapActivity::class.java)
            startActivity(intent)
        }
    }

}