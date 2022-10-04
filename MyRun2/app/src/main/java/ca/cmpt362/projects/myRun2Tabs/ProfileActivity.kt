package ca.cmpt362.projects.myRun2Tabs

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.io.File

class ProfileActivity: AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var imgUri: Uri
    private val imgFileName = "myrun2_profile_photo.jpg" //for picture saving
    private val package_name:String = "ca.cmpt362.projects.myRun2Tabs"
    private lateinit var cameraResult: ActivityResultLauncher<Intent>

    private lateinit var nameEdit:EditText
    private lateinit var nameString:String
    private lateinit var emailString:String
    private lateinit var phoneString:String
    private lateinit var classString:String
    private lateinit var majorString:String
    private lateinit var selectedRadioString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Util.checkPermissions(this)

        // Photo Uri path:
        val imgFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), imgFileName)
        imgUri = FileProvider.getUriForFile(this, package_name, imgFile)

        imageView = findViewById(R.id.imageView)

        cameraResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { //lambda function
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                val bitmap = Util.getBitmap(this, imgUri)
                imageView.setImageBitmap(bitmap)
                Log.d("camera", "--- camera OK clicked --- Result pass!")
            }
        }

        if (imgFile.exists()){
            val bitmap = Util.getBitmap(this, imgUri)
            imageView.setImageBitmap(bitmap)
        }

        loadProfile()
    }

    // change photo:
    fun changeButtonClick(view: View){
        //start camera but with no control, hope for the best
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri) // pass photo data
        cameraResult.launch(intent)
        startActivity(intent)
    }

    //This function is listening UI SAVE button click --> android:onClick="saveButtonClick" in xml file
    fun saveButtonClickedForm(view: View) {
        // Text edit:
        nameEdit = findViewById(R.id.editText_name)
        nameString = nameEdit.text.toString()
        val emailEdit: EditText = findViewById(R.id.editText_email)
        emailString = emailEdit.text.toString()
        val phoneEdit: EditText = findViewById(R.id.editText_phone)
        phoneString = phoneEdit.text.toString()
        val classEdit: EditText = findViewById(R.id.editText_class)
        classString = classEdit.text.toString()
        val majorEdit: EditText = findViewById(R.id.editText_major)
        majorString = majorEdit.text.toString()

        // Radio buttons:
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val selectedRadioID: Int = radioGroup.checkedRadioButtonId
        if (selectedRadioID != -1) { //if no radio button is selected, selectedRadioID == -1
            val selectedRadio: RadioButton = findViewById(selectedRadioID)
            selectedRadioString = selectedRadio.text.toString()
        } else {
            selectedRadioString = null.toString()
        }

        saveProfile() 

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
    }


    //get shared preferences values
    private fun loadProfile(){
        val getSharedPreferences: SharedPreferences = getSharedPreferences("profileMyRun1", Context.MODE_PRIVATE)
        val sharedNameValue : String? = getSharedPreferences.getString("name_key", null)
        val sharedEmailValue : String? = getSharedPreferences.getString("email_key", null)
        val sharedPhoneValue : String? = getSharedPreferences.getString("phone_key", null)
        val sharedClassValue : String? = getSharedPreferences.getString("class_key", null)
        val sharedMajorValue : String? = getSharedPreferences.getString("major_key", null)
        val sharedRadioButton: String? = getSharedPreferences.getString("radiobtn_key", null)

        val nameEdit:EditText = findViewById(R.id.editText_name)
        nameEdit.setText(sharedNameValue)
        val emailEdit: EditText = findViewById(R.id.editText_email)
        emailEdit.setText(sharedEmailValue)
        val phoneEdit: EditText = findViewById(R.id.editText_phone)
        phoneEdit.setText(sharedPhoneValue)
        val classEdit: EditText = findViewById(R.id.editText_class)
        classEdit.setText(sharedClassValue)
        val majorEdit: EditText = findViewById(R.id.editText_major)
        majorEdit.setText(sharedMajorValue)

        lateinit var radioButton: RadioButton
        if (sharedRadioButton.equals("Female")){
            radioButton = findViewById(R.id.radio_female)
            radioButton.setChecked(true)
        }else if(sharedRadioButton.equals("Male")){
            radioButton = findViewById(R.id.radio_male)
            radioButton.setChecked(true)
        }else if (sharedRadioButton.equals("Other")){
            radioButton = findViewById(R.id.radio_othergen)
            radioButton.setChecked(true)
        }
    }

    //Save data to file:
    private fun saveProfile(){
        //Save data to file:
        val fileName = "profileMyRun1"
        val setsharedPreferences: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = setsharedPreferences.edit()
        editor.apply{
            putString("name_key", nameString)
            putString("email_key", emailString)
            putString("phone_key", phoneString)
            putString("class_key", classString)
            putString("major_key", majorString)
            putString("radiobtn_key", selectedRadioString)
        }.apply()

        finish()

    }

    fun cancelButtonClickedForm(view: View){
        finish()
    }
}