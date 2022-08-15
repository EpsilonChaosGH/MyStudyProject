package com.example.mystudyproject.savedStateModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystudyproject.databinding.FragmentColorsGeneratorBinding
import kotlin.random.Random
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.core.view.setMargins
import androidx.fragment.app.viewModels
import com.example.mystudyproject.R


class ColorsGeneratorFragment : Fragment() {


    lateinit var binding: FragmentColorsGeneratorBinding
    private val viewModel by viewModels<ViewModel> { ViewModelFactory(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorsGeneratorBinding.inflate(inflater, container, false)


        viewModel.square.observe(viewLifecycleOwner) {
            renderSquare(it)
        }

        binding.generateColorsButton.setOnClickListener {
            viewModel.generateSquare()
        }

        return binding.root
    }


    private fun renderSquare(squares: Squares) = with(binding) {
        colorsContainer.removeAllViews()
        val identifiers = squares.colors.indices.map { View.generateViewId() }
        for (i in squares.colors.indices) {
            val row = i / squares.size
            val column = i % squares.size

            val view = View(requireContext())
            view.setBackgroundColor(squares.colors[i])
            view.id = identifiers[i]

            val params = LayoutParams(0, 0)
            params.setMargins(resources.getDimensionPixelSize(R.dimen.space))
            view.layoutParams = params

            // startToX constraint
            if (column == 0) params.startToStart = LayoutParams.PARENT_ID
            else params.startToEnd = identifiers[i - 1]

            // endToX constraint
            if (column == squares.size - 1) params.endToEnd = LayoutParams.PARENT_ID
            else params.endToStart = identifiers[i + 1]

            // topToX constraint
            if (row == 0) params.topToTop = LayoutParams.PARENT_ID
            else params.topToBottom = identifiers[i - squares.size]

            // bottomToX constraint
            if (row == squares.size - 1) params.bottomToBottom = LayoutParams.PARENT_ID
            else params.bottomToTop = identifiers[i + squares.size]

            colorsContainer.addView(view)
        }
    }
}