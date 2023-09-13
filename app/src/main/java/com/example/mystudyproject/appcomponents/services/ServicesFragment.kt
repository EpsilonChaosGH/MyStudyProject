package com.example.mystudyproject.appcomponents.services

import android.Manifest
import android.app.Service
import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.net.Uri
import android.os.BatteryManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mystudyproject.Const
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentBroadcastReceiverBinding
import com.example.mystudyproject.databinding.FragmentServicesBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.yandex.mapkit.MapKitFactory
import java.util.concurrent.TimeUnit

class ServicesFragment : Fragment(R.layout.fragment_services) {

    private lateinit var binding: FragmentServicesBinding

    private val handler = Handler(Looper.getMainLooper())

    private val requestLocationLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotLocationPermissionResult
    )

    private val connection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            (service as MyBoundService.UploadBinder).subscribeToProgress { progress ->
                handler.post {
                    Toast.makeText(requireContext(), "CONNECTED", Toast.LENGTH_SHORT).show()
                    binding.textViewProcess.text = progress.toString()
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            handler.post {
                Toast.makeText(requireContext(), "DISCONNECTED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.initialize(requireContext())
        binding = FragmentServicesBinding.bind(view)


        binding.buttonWork.setOnClickListener {
            val request = OneTimeWorkRequest.from(MyWorker::class.java)

            val request1 = OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            val request2 = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES).build()

            WorkManager.getInstance(requireContext())
                .beginUniqueWork(
                    "WORK NAME",
                    ExistingWorkPolicy.KEEP,
                    request
                )
                .enqueue()

            WorkManager.getInstance(requireContext())
                .enqueue(request2)
        }

        binding.buttonBound.setOnClickListener {
            Intent(requireContext(), MyBoundService::class.java).also {
                requireActivity().startService(it)
            }
        }

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

    private fun showToast(massage: Int) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Intent(requireContext(), MyBoundService::class.java).also { intent ->
            requireActivity().bindService(intent, connection, BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unbindService(connection)
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
}