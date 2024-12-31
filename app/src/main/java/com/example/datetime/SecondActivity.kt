package com.example.datetime

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.time.LocalDate
import java.time.Period

class SecondActivity : AppCompatActivity() {

    private lateinit var toolbarTB:Toolbar

    private lateinit var getImageIV:ImageView
    private lateinit var getNameTV:TextView
    private lateinit var getSurnameTV:TextView
    private lateinit var getPhoneTV:TextView
    private lateinit var getAgeTV:TextView
    private lateinit var getBirthdayTV:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        identification()

        getInfo()

        getAgeAndBirthday()
    }

    private fun getInfo() {
        val image = Uri.parse(intent.extras?.getString("image"))
        getImageIV.setImageURI(image)
        getNameTV.setText(intent.extras?.getString("name"))
        getSurnameTV.setText(intent.extras?.getString("surname"))
        getPhoneTV.setText(intent.extras?.getString("phone"))
    }

    private fun identification() {
        getImageIV = findViewById(R.id.getImageIV)
        getNameTV = findViewById(R.id.getNameTV)
        getSurnameTV = findViewById(R.id.getSurnameTV)
        getPhoneTV = findViewById(R.id.getPhoneTV)
        getAgeTV = findViewById(R.id.getAgeTV)
        getBirthdayTV = findViewById(R.id.getBirthdayTV)
    }

    @SuppressLint("NewApi", "SetTextI18n")
    private fun getAgeAndBirthday(){
        val time = LocalDate.now()
        val age = LocalDate.parse(intent.extras?.getString("date"))
        val period = Period.between(age,time)
        getAgeTV.setText("${period.years} года")
        val birthday = Period.between(time,age.plusYears(period.years.toLong()+1))
        getBirthdayTV.setText("До следующего дня рождения: ${birthday.months} месяц(-ев), ${birthday.days} дней!")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.exitItem -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}