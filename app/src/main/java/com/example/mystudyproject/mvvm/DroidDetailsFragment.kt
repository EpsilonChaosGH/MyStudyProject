package com.example.mystudyproject.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentDroidDetailsBinding
import com.example.mystudyproject.navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DroidDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDroidDetailsBinding
    private val viewModel: DroidDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDroid(requireArguments().getLong(ARG_DROID_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDroidDetailsBinding.inflate(inflater, container, false)

        viewModel.actionShowToast.observe(viewLifecycleOwner) {
            it.getValue()?.let { massageRes -> navigator().toast(massageRes) }
        }

        viewModel.actionGoBack.observe(viewLifecycleOwner) {
            it.getValue()?.let { navigator().goBack() }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            binding.contentContainer.visibility = if (it.showContent) {
                val droidDetails = (it.droidDetailsResult as SuccessResult).data
                binding.droidNameTextView.text = droidDetails.droid.name
                if (droidDetails.droid.photo.isNotBlank()) {
                    Glide.with(this)
                        .load(droidDetails.droid.photo)
                        .circleCrop()
                        .into(binding.photoImageView)
                } else {
                    Glide.with(this)
                        .load(R.drawable.ic_user)
                        .into(binding.photoImageView)
                }
                binding.droidDerailsTextView.text = droidDetails.details
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.progressBar.visibility = if (it.showProgress) View.VISIBLE else View.GONE
            binding.deleteButton.isEnabled = it.enableDeleteBottom
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteDroid()
        }
        return binding.root
    }

    companion object {
        private const val ARG_DROID_ID = "ARG_DROID_ID"

        fun newInstance(droidId: Long): DroidDetailsFragment {
            val fragment = DroidDetailsFragment()
            fragment.arguments = bundleOf(ARG_DROID_ID to droidId)
            return fragment
        }
    }
}
