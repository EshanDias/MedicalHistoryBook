package com.uee.sliit.medicalhistorybook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_signUp.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser(){
        val name = tb_name.text.toString().trim()
        val birthday = "" + dtp_birthday.dayOfMonth  +"/"+ dtp_birthday.month +"/"+dtp_birthday.year
        val email = tb_email.text.toString().trim()
        val password = tb_password.text.toString().trim()
        val confirmPassword = tb_cinfirmPassword.text.toString().trim()

        if (!password.equals(confirmPassword, false)){
            tb_cinfirmPassword.error = "password does not match"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userId = ref.push().key

        val user = User(userId, name, birthday, email, password)

        ref.child(userId).setValue(user).addOnCompleteListener{
            Toast.makeText(applicationContext, "User Registered Successfully!", Toast.LENGTH_LONG).show()
        }
    }
}
