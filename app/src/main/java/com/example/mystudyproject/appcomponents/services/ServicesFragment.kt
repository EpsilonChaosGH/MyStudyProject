package com.example.mystudyproject.appcomponents.services

import android.Manifest
import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.BatteryManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mystudyproject.Const
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentBroadcastReceiverBinding
import com.example.mystudyproject.databinding.FragmentServicesBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.yandex.mapkit.MapKitFactory

class ServicesFragment : Fragment(R.layout.fragment_services) {

    private lateinit var binding: FragmentServicesBinding

    private val requestLocationLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotLocationPermissionResult
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.initialize(requireContext())
        binding = FragmentServicesBinding.bind(view)


        binding.buttonWork.setOnClickListener { }

        binding.buttonBound.setOnClickListener { }

        binding.buttonForeground.setOnClickListener {
            requestLocationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            Intent(requireContext(), MyForegroundService::class.java).also {
                requireActivity().startForegroundService(it)
            }
        }

        binding.buttonBackground.setOnClickListener {
            Intent(requireContext(), MyBackgroundService::class.java).also {
                requireActivity().startService(it)
            }
        }
    }

    private fun onGotLocationPermissionResult(granted: Boolean) {
        if (granted) {
            showToast(R.string.permission_grated)
        } else {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                askUserForOpeningAppSettings()
            } else {
                showToast(R.string.permissions_denied)
            }
        }
    }

    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireActivity().packageName, null)
        )
        if (requireActivity().packageManager.resolveActivity(
                appSettingsIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) == null
        ) {
            showToast(R.string.permissions_denied_forever)
        } else {

            val listener = DialogInterface.OnClickListener { _, _ -> }
            val listenerSettings = DialogInterface.OnClickListener { _, _ ->
                startActivity(appSettingsIntent)
            }
            val builder = AlertDialog.Builder(requireContext())
                .setPositiveButton(R.string.button_open_settings, listenerSettings)
                .setNeutralButton(R.string.button_cancel, listener)
                .create()
            builder.setView(layoutInflater.inflate(R.layout.dialog_notification_settings, null))
            builder.show()
        }
    }

    private fun showToast(massage: Int) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}