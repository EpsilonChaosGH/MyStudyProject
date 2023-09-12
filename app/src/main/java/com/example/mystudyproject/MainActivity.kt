package com.example.mystudyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mystudyproject.databinding.ActivityMainBinding
import com.example.mystudyproject.mvvm.Droid
import com.example.mystudyproject.mvvm.DroidDetailsFragment
import com.example.mystudyproject.mvvm.Navigator
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MapKitFactory.setApiKey("3fc96586-0ce8-41ca-8d3a-2ac3155d4fee")

        if (savedInstanceState == null) {
            val fragment = Fragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit()
        }

       // val service = MyService()

       // service.startService(Intent())
    }

    override fun showDetails(droid: Droid) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, DroidDetailsFragment.newInstance(droid.id))
            .commit()
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun toast(massageRes: Int) {
        Toast.makeText(this, massageRes, Toast.LENGTH_SHORT).show()
    }
}