package com.example.mystudyproject.yandexmap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

class MapFragment : Fragment(R.layout.fragment_map) {

    private lateinit var binding: FragmentMapBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.initialize(requireContext())
        binding = FragmentMapBinding.bind(view)

        MapKitFactory.initialize(requireContext())

        binding.mapView.mapWindow.map.move(
            CameraPosition(
                Point(42.222222, 42.222222),
                11.0f,
                0.0f,
                0.0f
            ),
            Animation(
                Animation.Type.SMOOTH,
                1.5f
            ),
            null
        )
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        binding.mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
}