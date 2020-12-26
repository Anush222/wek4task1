package com.anush.wek4task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BillActivity : AppCompatActivity() {
   private lateinit var tvNoOFDays: TextView
    private lateinit var tvNoOFDaysVal: TextView
    private lateinit var tvAdults: TextView
    private lateinit var tvAdultsPrice: TextView
    private lateinit var tvChildren: TextView
    private lateinit var tvChildrenPrice: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var tvTax: TextView
    private lateinit var tvTaxPrice: TextView
    private lateinit var tvGrandTotal: TextView
    private lateinit var tvGrandTotalPrice: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        tvNoOFDays=findViewById(R.id.tvNoOfDays)
        tvNoOFDaysVal=findViewById(R.id.tvNoOfDaysVal)
        tvAdults=findViewById(R.id.tvAdults)
        tvAdultsPrice=findViewById(R.id.tvAdultsPrice)
        tvChildren=findViewById(R.id.tvChildren)
        tvChildrenPrice=findViewById(R.id.tvChildrenPrice)
        tvTotal=findViewById(R.id.tvTotal)
        tvTotalPrice=findViewById(R.id.tvTotalPrice)
        tvTax=findViewById(R.id.tvTax)
        tvTaxPrice=findViewById(R.id.tvTaxPrice)
        tvGrandTotal=findViewById(R.id.tvGrandTotal)
        tvGrandTotalPrice=findViewById(R.id.tvGrandTotalPrice)

        val intent=intent
        if(intent.extras!=null){
            val totalStayDays=intent.getIntExtra("totalStayDays",0)
            val selectedItem= intent.getStringExtra("selectedItem")
            val noOfAdults= intent.getIntExtra("noOfAdults",0)
            val noOfChildren=intent.getIntExtra("noOfChildren",0)
            if (selectedItem == "0") booker(noOfAdults,noOfChildren,totalStayDays,50000)
            else if (selectedItem == "1") booker(noOfAdults,noOfChildren,totalStayDays,3000)
            else if (selectedItem == "2") booker(noOfAdults,noOfChildren,totalStayDays,6000)
        }
    }

    private fun booker(noOfAdults:Int, noOfChildren:Int,totalStayDays:Int,price:Int){
        val adultPrice = noOfAdults * price * totalStayDays
        val childrenPrice = (noOfChildren * ( price/2)) * totalStayDays
        val total= adultPrice + childrenPrice
        val tax= 0.13 * total
        val grandTotal= total + tax
        tvNoOFDaysVal.text=("$totalStayDays")
        tvAdultsPrice.text = ("$adultPrice")
        tvChildrenPrice.text = ("$childrenPrice")
        tvTotalPrice.text=("$total")
        tvTaxPrice.text=("$tax")
        tvGrandTotalPrice.text=("$grandTotal")
    }
}