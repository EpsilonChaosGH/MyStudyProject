package com.example.mystudyproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystudyproject.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.btShowDialog.setOnClickListener {
            val fragment = DialogsFragment()
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
        }
        binding.btShowViewModel.setOnClickListener {
            val fragment = ViewModelFragment()
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView,
                    DialogsFragment.newInstance(DialogsFragment.Options("title", Color.RED),
                        fragment)
                )
                .commit()
        }
        binding.btCustomView.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView, CustomViewFragment())
                .commit()
        }

        binding.btTicTacToe.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView, TicTacToeFragment())
                .commit()
        }

        return binding.root
    }

}