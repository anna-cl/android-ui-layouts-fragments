//Reference:
//https://stackoverflow.com/questions/1957831/center-a-button-in-a-linear-layout

package ca.cmpt362.projects.myRun2Tabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startButtonClicked(view : View){
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "fill out the form", Toast.LENGTH_SHORT).show()
    }

}