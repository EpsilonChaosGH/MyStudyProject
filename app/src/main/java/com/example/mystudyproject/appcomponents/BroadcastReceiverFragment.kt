package com.example.mystudyproject.appcomponents

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mystudyproject.Const
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentBroadcastReceiverBinding
import com.yandex.mapkit.MapKitFactory

class BroadcastReceiverFragment : Fragment(R.layout.fragment_broadcast_receiver) {

    private lateinit var binding: FragmentBroadcastReceiverBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.initialize(requireContext())
        binding = FragmentBroadcastReceiverBinding.bind(view)

        val batteryStatus: Intent? = requireActivity().registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val myReceiver: Intent? = requireActivity().registerReceiver(MyReceiver(), IntentFilter(Const.TEST))

        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }
        binding.value.text = batteryPct?.toInt().toString()
       // sendMessage()

//        Toast.makeText(this, chargePlug, Toast.LENGTH_SHORT).show()

        binding.bt.setOnClickListener {
            binding.value.text = myReceiver?.getStringExtra(Const.KEY)
        }
    }

    fun sendMessage(){
        val intent = Intent(Const.TEST)

        intent.putExtra(Const.KEY,"TEST_VALUE")

        requireActivity().sendBroadcast(intent)
    }
}