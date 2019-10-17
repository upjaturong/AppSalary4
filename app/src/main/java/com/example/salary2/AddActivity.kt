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
        val user = mAuth.currentUser
        val uid = user!!.uid

        food_add.setOnClickListener {

            mDatabase.child(uid).child("CostType").setValue("อาหาร")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/food.png?alt=media&token=ea567452-277b-4c31-9d86-957224e46aaf")
            startActivity(Intent(this, AddDetail::class.java))
        }
        shopping_add.setOnClickListener {
            mDatabase.child(uid).child("CostType").setValue("ช็อปปิ้ง")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/shop.png?alt=media&token=1c311769-089b-4817-b665-d6fa56dc4089")
            startActivity(Intent(this, AddDetail::class.java))
        }
        carfare_add.setOnClickListener {

            mDatabase.child(uid).child("CostType").setValue("ค่ารถค่าน้ำมัน")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/car.png?alt=media&token=d422ae23-7c0e-45a6-b439-a3650f97a8f0")
            startActivity(Intent(this, AddDetail::class.java))
        }
        family_add.setOnClickListener {

            mDatabase.child(uid).child("CostType").setValue("ครอบครัว")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/ss.png?alt=media&token=ee70e2af-5551-40d2-a317-4b3b6ab72de5")
            startActivity(Intent(this, AddDetail::class.java))
        }
        game_add.setOnClickListener {

            mDatabase.child(uid).child("CostType").setValue("ค่าเกม")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/game.png?alt=media&token=83ee2e9a-10fc-4eb5-8962-27bf8602c004")
            startActivity(Intent(this, AddDetail::class.java))
        }
        tel_add.setOnClickListener {

            mDatabase.child(uid).child("CostType").setValue("ค่าโทรศัพท์")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/telephot.jpg?alt=media&token=bf0ad980-f2e3-47b1-9483-273226bb0fd6")
            startActivity(Intent(this, AddDetail::class.java))
        }
        room_add.setOnClickListener {

            mDatabase.child(uid).child("CostType").setValue("ค่าที่พัก")
            mDatabase.child(uid).child("ImageCostType").setValue("https://firebasestorage.googleapis.com/v0/b/salary-6da60.appspot.com/o/room.png?alt=media&token=a09adb43-504b-48cd-bfba-36d937581a78")
            startActivity(Intent(this, AddDetail::class.java))
        }
        revenue_add.setOnClickListener {
            mDatabase.child(uid).child("CostType").setValue("รายรับ")
            mDatabase.child(uid).child("ImageCostType").setValue("อาหาร")
            startActivity(Intent(this, AddDetail::class.java))
        }
    }


}
