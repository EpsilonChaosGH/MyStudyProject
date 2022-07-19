package com.example.mystudyproject

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mystudyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = DialogInterface.OnClickListener { _, v ->
            when (v) {
                DialogInterface.BUTTON_POSITIVE -> toast("positive")
                DialogInterface.BUTTON_NEGATIVE -> toast("negative")
                DialogInterface.BUTTON_NEUTRAL -> toast("neutral")
                else -> toast("oops")
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("title")
            .setMessage("message")
            .setPositiveButton("positive", listener)
            .setNegativeButton("negative", listener)
            .setNeutralButton("neutral", listener)
            .setOnCancelListener{
                toast("cancel")
            }
            .create()
        dialog.show()

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}