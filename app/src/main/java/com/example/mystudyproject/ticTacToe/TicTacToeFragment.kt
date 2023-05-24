package com.example.mystudyproject.ticTacToe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystudyproject.databinding.FragmentTictactoeBinding
import kotlin.random.Random

class TicTacToeFragment : Fragment() {

    var isFirstPlayer = true
    private lateinit var binding: FragmentTictactoeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTictactoeBinding.inflate(inflater, container, false)



        binding.ticTacToeView.ticTacToeField = TicTacToeField(10, 10)


        binding.btRandom.setOnClickListener {
            binding.ticTacToeView.ticTacToeField = TicTacToeField(
                Random.nextInt(3, 50),
                Random.nextInt(3, 50)
            )
            binding.ticTacToeView.ticTacToeField!!.setSell(3,4, Cell.PLAYER_2)
        }

        binding.ticTacToeView.actionListener = { row, column, field ->
            val cell = field.getSell(row, column)
            if (cell == Cell.EMPTY) {
                if (isFirstPlayer) {
                    field.setSell(row, column, Cell.PLAYER_1)
                } else {
                    field.setSell(row, column, Cell.PLAYER_2)
                }
                isFirstPlayer = !isFirstPlayer
            }

        }


        return binding.root
    }
}