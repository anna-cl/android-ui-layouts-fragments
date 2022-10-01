//Sept27,2022 My Run 2
//Reference:
//https://stackoverflow.com/questions/1957831/center-a-button-in-a-linear-layout
//https://www.digitalocean.com/community/tutorials/android-alert-dialog-using-kotlin#9-alert-dialog-with-edit-text

package ca.cmpt362.projects.myRun2Tabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun getSpinnerPosition(): Int {
        val selectedItem: Spinner = findViewById(R.id.spinner_inputType)
        return selectedItem.selectedItemPosition
    }

    fun startButtonClicked(view : View){
        lateinit var intent:Intent
        if (getSpinnerPosition()==0) { // spinner --> "Manual Entry"
            intent = Intent(this, StartManualActivity::class.java)
            startActivity(intent)
        }else{
            intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

}