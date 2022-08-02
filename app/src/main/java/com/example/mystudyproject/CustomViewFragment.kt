package com.example.mystudyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mystudyproject.databinding.FragmentCustomViewBinding
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.part_buttons.view.*

class CustomViewFragment: Fragment() {
    private lateinit var binding: FragmentCustomViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomViewBinding.inflate(inflater, container, false)



        binding.bottomButton.setListener {
            when(it) {
                BottomButtonAction.POSITIVE -> {
                    binding.bottomButton.setPositiveButtonText("OK OK OK")
                    Toast.makeText(requireActivity(), "OK", Toast.LENGTH_SHORT).show()
                }

                BottomButtonAction.NEGATIVE -> {
                    binding.bottomButton.setNegativeButtonText("NO NO NO")
                    Toast.makeText(requireActivity(), "cancel", Toast.LENGTH_SHORT).show()

                }
            }
        }




        return binding.root
    }
}