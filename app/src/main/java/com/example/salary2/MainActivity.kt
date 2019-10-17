package com.example.salary2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity() {




    lateinit var mDatabase: DatabaseReference
    var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    lateinit var employeeList:MutableList<Employees>
    lateinit var listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDatabase = FirebaseDatabase.getInstance().getReference("Names")


        fab_main.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }
        report.setOnClickListener {
            startActivity(Intent(this@MainActivity, FullReportActivity::class.java))
        }
        if (user == null){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            val uid = user!!.uid
        val nameTxt = findViewById<View>(R.id.showusername) as TextView

        mDatabase.child(uid).child("Name").addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                nameTxt.text = " " + snapshot.value.toString()
            }
        })
        }
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


