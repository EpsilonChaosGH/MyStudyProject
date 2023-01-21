package com.example.mystudyproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mystudyproject.customViwe.CustomViewFragment
import com.example.mystudyproject.databinding.FragmentMenuBinding
import com.example.mystudyproject.dialog.DialogsFragment
import com.example.mystudyproject.mvvm.DroidsListFragment
import com.example.mystudyproject.navigationTabs.ContainerFragment
import com.example.mystudyproject.navigationTabs.TabsFragment
import com.example.mystudyproject.recyclerView.RecyclerViewFragment
import com.example.mystudyproject.savedStateModule.ColorsGeneratorFragment
import com.example.mystudyproject.ticTacToe.TicTacToeFragment
import com.example.mystudyproject.viewModel.ViewModelFragment

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.btShowViewModel.setOnClickListener {
            launchFragment(
                DialogsFragment.newInstance(
                    DialogsFragment.Options("title", Color.RED),
                    ViewModelFragment()
                )
            )
        }

        binding.btShowDialog.setOnClickListener { launchFragment(DialogsFragment()) }

        binding.btCustomView.setOnClickListener { launchFragment(CustomViewFragment()) }

        binding.btTicTacToe.setOnClickListener { launchFragment(TicTacToeFragment()) }

        binding.btRecyclerView.setOnClickListener { launchFragment(RecyclerViewFragment()) }

        binding.btMVVM.setOnClickListener { launchFragment(DroidsListFragment()) }

        binding.btSavedStateModule.setOnClickListener { launchFragment(ColorsGeneratorFragment()) }

        binding.btNavigationTabs.setOnClickListener { launchFragment(ContainerFragment()) }

        return binding.root
    }


    private fun launchFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

}