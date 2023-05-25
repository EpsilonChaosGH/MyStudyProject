package com.example.mystudyproject.daggerAndHilt.hilt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentHiltBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestFragment: Fragment(R.layout.fragment_hilt) {

    private lateinit var binding: FragmentHiltBinding

    private val viewModel by viewModels<HiltTestViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHiltBinding.bind(view)

        viewModel.state.observe(viewLifecycleOwner){
            binding.textView2.text = it.retrofit
            binding.textView3.text = it.settings
            binding.textView4.text =it.db
            binding.textView5.text =it.repo
        }

    }
}