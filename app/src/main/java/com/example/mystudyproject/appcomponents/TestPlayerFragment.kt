package com.example.mystudyproject.appcomponents

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentTestPlayerBinding

class TestPlayerFragment: Fragment(R.layout.fragment_test_player) {

    private lateinit var binding: FragmentTestPlayerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestPlayerBinding.bind(view)

        val constrains = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadWorkRequest = OneTimeWorkRequestBuilder<Worker>()
            .setConstraints(constrains)
            .build()

        WorkManager.getInstance(requireContext())
            .enqueue(uploadWorkRequest)

    }
}