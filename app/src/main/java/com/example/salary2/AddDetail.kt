package com.example.salary2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_detail.*

class AddDetail : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_detail)

        val c= Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day =c.get(Calendar.DAY_OF_MONTH)

        pickDate.setOnClickListener {
            val dpd =DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,mYear,mMonth,mDay->
                dateTv.setText(""+mDay+"/"+mMonth+"/"+mYear)
            },year,month,day)
            dpd.show()
        }

        val user = mAuth.currentUser
        val uid = user!!.uid
        val nameTxt = findViewById<View>(R.id.cost_type_tv) as TextView
        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        mDatabase.child(uid).child("CostType").addValueEventListener( object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                nameTxt.text =  " " + snapshot.value.toString()
            }
        })
        mDatabase.child(uid).child("ImageCostType").addValueEventListener( object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
               img_cost_type
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_add) {
            val priceTxt = findViewById<View>(R.id.price_ed) as EditText
            val writeNotedTxt = findViewById<View>(R.id.write_note_ed) as EditText
            val price = priceTxt.text.toString()
            val writeNote = writeNotedTxt.text.toString()
            val user = mAuth.currentUser
            val uid = user!!.uid

            val employeeId = mDatabase.push().key
            val employee = Employees(
                employeeId.toString(), price,
               writeNote
            )
            mDatabase.child(uid).child(employeeId!!).setValue(employee)
            startActivity(Intent(this, MainActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

}


