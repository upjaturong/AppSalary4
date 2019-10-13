package com.example.salary2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity() {


    var daily: RecyclerView? = null
    var date_1 = arrayOf(
        "20", "21", "22", "23", "24", "25"
    )
    var date_2 = arrayOf(
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
    )
    var date_3 = arrayOf(
        "September 2019",
        "September 2019",
        "September 2019",
        "September 2019",
        "September 2019",
        "September 2019"
    )

    var namefood = arrayOf(
        "น้ำดื่ม", "อาหาร", "ค่าเฟ่", "ชานมไข่มุก", "ค่าเฟ่", "ชานมไข่มุก"
    )

    var arrImg = arrayOf(
        R.drawable.food,
        R.drawable.img_greentea,
        R.drawable.img_milkgreentea,
        R.drawable.pangyen,
        R.drawable.img_milkgreentea,
        R.drawable.pangyen,
        R.drawable.img_milkgreentea

    )
    var price = arrayOf(
        "฿ 10", "฿ 20", "฿ 30", "฿ 40", "฿ 30", "฿ 40"
    )

    lateinit var mDatabase : DatabaseReference
    var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daily = findViewById(R.id.recyclerview)
        daily!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val myAdapter = Adapter(date_1, date_2, date_3, namefood, arrImg, price, price, this)
        daily!!.adapter = myAdapter

        fab_main.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }
        report.setOnClickListener {
            startActivity(Intent(this@MainActivity, FullReportActivity::class.java))
        }
        val uid = user!!.uid

//        if (){
//            startActivity(Intent(this, LoginActivity::class.java))
//        }else{
            val nameTxt = findViewById<View>(R.id.showusername) as TextView
            mDatabase = FirebaseDatabase.getInstance().getReference("Names")
            mDatabase.child(uid).child("Name").addValueEventListener( object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                }
                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    nameTxt.text =  " " + snapshot.value.toString()
                }
            })
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.signOut) {
            mAuth.signOut()
            val uid = user!!.uid
            mDatabase.child(uid).child("Status").setValue(0)
            Toast.makeText(this, "Signed Out :(", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}

