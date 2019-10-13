package com.example.salary2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AddDetail : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_detail)

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_add) {

            startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
