package com.example.mystudyproject.navigationTabs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentFirstBinding
import com.example.mystudyproject.factory
import kotlinx.coroutines.launch

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FirstViewModel by viewModels { factory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFirstBinding.bind(view)

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getRandomNumber()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                binding.textView.text = it.toString()
            }

        }
    }
}