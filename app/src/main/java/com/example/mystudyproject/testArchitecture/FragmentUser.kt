package com.example.mystudyproject.testArchitecture

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentUserBinding
import com.example.mystudyproject.testArchitecture.extension.success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentUser : Fragment(R.layout.fragment_user) {

    private lateinit var binding: FragmentUserBinding

    private val viewModel by viewModels<ViewModelUser>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserBinding.bind(view)


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingUser.collect { value ->
                    value?.let {
                        it.success {
                            binding.textViewUser.text = it.name
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingUserPref.collect { value ->
                    value?.let {
                        Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                        binding.textViewUserPref.text = it.name
                    }
                }
            }
        }
    }
}