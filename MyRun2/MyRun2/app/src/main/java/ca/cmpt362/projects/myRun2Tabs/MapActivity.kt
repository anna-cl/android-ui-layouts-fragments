package ca.cmpt362.projects.myRun2Tabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        this.setTitle(R.string.map_title)

    }

    fun saveButtonClicked(view: View){
        var intent:Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //TODO: save data---
    }

    fun cancelButtonClicked(view: View){
        var intent:Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}