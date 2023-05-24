package com.example.mystudyproject.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystudyproject.databinding.FragmentDroidListBinding
import com.example.mystudyproject.navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DroidsListFragment : Fragment() {

    private lateinit var binding: FragmentDroidListBinding
    private lateinit var adapter: DroidsAdapter

    private val viewModel: DroidsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDroidListBinding.inflate(inflater, container, false)

        adapter = DroidsAdapter(viewModel)

        viewModel.droid.observe(viewLifecycleOwner, Observer {
            hideAll()
            when (it) {
                is SuccessResult -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter.droids = it.data
                }

                is ErrorResult -> {
                    binding.tryAgain.visibility = View.VISIBLE
                }

                is PendingResult -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is EmptyResult -> {
                    binding.noDroidsTextView.visibility = View.VISIBLE
                }
            }
        })

        viewModel.actionShowDetails.observe(viewLifecycleOwner, Observer {
            it.getValue()?.let { droid -> navigator().showDetails(droid) }
        })

        viewModel.actionShowToast.observe(viewLifecycleOwner, Observer {
            it.getValue()?.let { massageRes -> navigator().toast(massageRes) }
        })

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    private fun hideAll() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.tryAgain.visibility = View.GONE
        binding.noDroidsTextView.visibility = View.GONE
    }
}