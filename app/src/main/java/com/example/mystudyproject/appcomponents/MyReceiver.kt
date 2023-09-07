package com.example.mystudyproject.appcomponents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.mystudyproject.Const

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "RE ${intent.getStringExtra(Const.KEY)}", Toast.LENGTH_SHORT).show()
    }
}