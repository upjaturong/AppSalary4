package com.example.salary2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        food_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("อาหาร")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
        shopping_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("ช็อปปิ้ง")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
        carfare_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("ค่ารถค่าน้ำมัน")
            mDatabase.child(uid).child("ImageCostType").setValue("ค่ารถค่าน้ำมัน")
            startActivity(Intent(this, AddDetail::class.java))
        }
        family_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("ครอบครัว")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
        game_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("เกม")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
        tel_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("ค่าโทรศัพท์")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
        room_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("ค่าที่พัก")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
        revenue_add.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase.child(uid).child("CostType").setValue("รายรับ")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
    }


}
