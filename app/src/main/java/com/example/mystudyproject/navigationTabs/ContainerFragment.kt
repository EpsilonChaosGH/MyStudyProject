package com.example.mystudyproject.navigationTabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mystudyproject.R

class ContainerFragment : Fragment(R.layout.fragment_navigate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost =
            childFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHost.navController

        val graph = navController.navInflater.inflate(R.navigation.main_graph)
        graph.setStartDestination(R.id.tabsFragment)
        navController.graph = graph
    }
}