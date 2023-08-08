package com.kacperbrozyna.registraationformapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.kacperbrozyna.registraationformapp.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding
    lateinit var user: User

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrieveUser()
        displayUser()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.textViewEmailAddress.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user.email}")
            startActivity(intent)
        }
        binding.textViewPhoneNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user.phone}")
            startActivity(intent)
        }
    }

    private fun displayUser() {
        binding.textViewFullName.text = user.getFullname()
        binding.textViewEmailAddress.text = user.email
        binding.textViewPhoneNumber.text = user.phone
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun retrieveUser() {
        user = intent.getSerializableExtra("User", User::class.java)!!
    }

}