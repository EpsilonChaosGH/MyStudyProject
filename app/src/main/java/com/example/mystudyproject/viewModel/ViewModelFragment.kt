package com.example.mystudyproject.viewModel

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mystudyproject.dialog.DialogsFragment.Companion.OPTIONS_KEY
import com.example.mystudyproject.dialog.DialogsFragment.Companion.SAVE_OPTIONS_KEY
import com.example.mystudyproject.databinding.FragmentViewmodelBinding
import com.example.mystudyproject.dialog.DialogsFragment


class ViewModelFragment : Fragment() {
    private lateinit var binding: FragmentViewmodelBinding
    private val viewModel by viewModels<StateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initState(
            savedInstanceState?.getParcelable(SAVE_OPTIONS_KEY)
                ?: arguments?.getParcelable(OPTIONS_KEY)
                ?: DialogsFragment.Options("default", Color.GREEN)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewmodelBinding.inflate(inflater, container, false)
        viewModel.state.observe(viewLifecycleOwner, Observer {

            binding.textView.setTextColor(viewModel.state.value?.color ?: Color.GREEN)
            binding.textView.text = viewModel.state.value?.title
        })

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(SAVE_OPTIONS_KEY, viewModel.state.value)
    }

    class StateViewModel: ViewModel() {

        fun initState(options: DialogsFragment.Options) {
            stateLiveData.value = options
        }

        private val stateLiveData = MutableLiveData<DialogsFragment.Options>()
        val state: LiveData<DialogsFragment.Options> = stateLiveData
    }

}


