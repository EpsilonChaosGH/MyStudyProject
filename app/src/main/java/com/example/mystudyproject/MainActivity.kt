package com.example.mystudyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mystudyproject.databinding.ActivityMainBinding
import com.example.mystudyproject.mvvm.Droid
import com.example.mystudyproject.mvvm.DroidDetailsFragment
import com.example.mystudyproject.mvvm.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = Fragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit()
        }
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