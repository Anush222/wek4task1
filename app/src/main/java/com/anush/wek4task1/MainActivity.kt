package com.anush.wek4task1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var spinner1: Spinner
    private lateinit var etCheckInDate: EditText
    private lateinit var etCheckOutDate: EditText
    private lateinit var etAdults: EditText
    private lateinit var etChildren: EditText
    private lateinit var btnBook: Button
    var totalStayDays: Int=0
    private val destinations= arrayOf("Bali  Rs.5000","Malaysia   Rs.3000","Singapore   Rs.6000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner1 = findViewById(R.id.spinner1)
        etCheckInDate = findViewById(R.id.etCheckInDate)
        etCheckOutDate = findViewById(R.id.etCheckOutDate)
        etAdults = findViewById(R.id.etAdults)
        etChildren = findViewById(R.id.etChildren)
        btnBook  = findViewById(R.id.btnBook)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, destinations)
        spinner1.adapter= adapter
        etCheckInDate.setOnClickListener(this)
        etCheckOutDate.setOnClickListener(this)
        btnBook.setOnClickListener(this)

    }
    override fun onClick(v:View?){
        when(v?.id){
            R.id.etCheckInDate->{
               checkInDate()
            }
            R.id.etCheckOutDate->{
               checkOutDate()
            }
            R.id.btnBook->{
                if(isValid()) {
                    val selectedItem = spinner1.selectedItemPosition.toString()
                    val noOfAdults = etAdults.text.toString().toInt()
                    val noOfChildren = etChildren.text.toString().toInt()
                    val days = daysCalculator()
                    val intent = Intent(this, BillActivity::class.java)
                    intent.putExtra("selectedItem", selectedItem)
                    intent.putExtra("totalStayDays", days)
                    intent.putExtra("noOfAdults", noOfAdults)
                    intent.putExtra("noOfChildren", noOfChildren)
                    startActivity(intent)
                }
            }
        }
    }

    private fun daysCalculator():Int{
        val checkInDay=etCheckInDate.text.toString()
        val checkOutDay=etCheckOutDate.text.toString()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date1: Date = simpleDateFormat.parse(checkInDay)
        val date2: Date = simpleDateFormat.parse(checkOutDay)
        val difference = Math.abs(date1.time - date2.time)
        totalStayDays = ((difference) / (24 * 60 * 60 * 1000)).toInt()+1
        return  totalStayDays
    }


    private fun isValid():Boolean{
        if (etCheckInDate.text.isEmpty()) {
            etCheckInDate.error = "Please select your check-in date"
            return false
        }
        else if (etCheckOutDate.text.isEmpty()) {
            etCheckOutDate.error = "Please select your check-out date"
            return false
        }
        else if (etAdults.text.isEmpty()) {
            etAdults.error = "*required"
            return false
        }
        else if (etChildren.text.isEmpty()) {
            etChildren.error = "*required"
            return false
        }
        return true
    }
    private fun checkInDate(){
        val c = Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val checkInDate="$dayOfMonth/${month+1}/$year"
                etCheckInDate.setText(checkInDate)
            },year,month,day)
        datePickerDialog.show()
    }

    private fun checkOutDate(){
        val c = Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val checkInDate="$dayOfMonth/${month+1}/$year"
                etCheckOutDate.setText(checkInDate)
            },year,month,day)
        datePickerDialog.show()

        }


    }
