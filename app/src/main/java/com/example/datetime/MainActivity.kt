package com.example.datetime

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private val GALLERY_REQUEST = 1
    private var selectedImage:Uri? = null

    private lateinit var addImageViewIV:ImageView
    private lateinit var nameEditTextET:EditText
    private lateinit var surnameEditTextET:EditText
    private lateinit var phoneEditTextET:EditText
    private lateinit var birthdayDayEditTextET:EditText
    private lateinit var birthdayMonthEditTextET:EditText
    private lateinit var birthdayYearEditTextET:EditText
    private lateinit var saveBTN:Button

    @SuppressLint("MissingInflatedId", "NewApi", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        identification()

        addImageViewIV.setOnClickListener {
            val image = Intent(Intent.ACTION_PICK)
            image.type = "image/*"
            startActivityForResult(image,GALLERY_REQUEST)
        }
        saveBTN.setOnClickListener {
            val date = parseDate()
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("name",nameEditTextET.text.toString())
            intent.putExtra("surname",surnameEditTextET.text.toString())
            intent.putExtra("phone",phoneEditTextET.text.toString())
            intent.putExtra("date",date.toString())
            intent.putExtra("image",selectedImage.toString())
            startActivity(intent)
            finish()
        }
    }

    private fun identification() {
        addImageViewIV = findViewById(R.id.addImageViewIV)
        nameEditTextET = findViewById(R.id.nameEditTextET)
        surnameEditTextET = findViewById(R.id.surnameEditTextET)
        phoneEditTextET = findViewById(R.id.phoneEditTextET)
        birthdayDayEditTextET = findViewById(R.id.birthdayDayEditTextET)
        birthdayMonthEditTextET = findViewById(R.id.birthdayMonthEditTextET)
        birthdayYearEditTextET = findViewById(R.id.birthdayYearEditTextET)
        saveBTN = findViewById(R.id.saveBTN)
    }

    @SuppressLint("NewApi")
    private fun parseDate():LocalDate{
        val day = Integer.parseInt(birthdayDayEditTextET.text.toString())
        val month = Integer.parseInt(birthdayMonthEditTextET.text.toString())
        val year = Integer.parseInt(birthdayYearEditTextET.text.toString())
        val date = LocalDate.of(year,month,day)
        return date
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            GALLERY_REQUEST -> {
                if (resultCode == RESULT_OK){
                    selectedImage = data?.data
                    addImageViewIV.setImageURI(selectedImage)
                }
            }
        }
    }

}