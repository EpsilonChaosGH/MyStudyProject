package com.example.mystudyproject.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentDialogsBinding
import kotlinx.parcelize.Parcelize

class DialogsFragment : Fragment() {
    private lateinit var binding: FragmentDialogsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogsBinding.inflate(inflater, container, false)


        val listener = DialogInterface.OnClickListener { _, v ->
            when (v) {
                DialogInterface.BUTTON_POSITIVE -> toast("positive")
                DialogInterface.BUTTON_NEGATIVE -> toast("negative")
                DialogInterface.BUTTON_NEUTRAL -> toast("neutral")
                else -> toast("oops")
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("title")
            .setMessage("message")
            .setPositiveButton("positive", listener)
            .setNegativeButton("negative", listener)
            .setNeutralButton("neutral", listener)
            .setOnCancelListener {
                toast("cancel")
            }
            .create()

        binding.button2.setOnClickListener {
            dialog.show()
        }


        class ValuesDialog2(
            var values: List<Int>,
            var currentIndex: Int
        )

        fun createValues(currentValues: Int): ValuesDialog2 {
            val values = 0..100 step 10
            val currentIndex = values.indexOf(currentValues)
            return if (currentIndex == -1) {
                val list = values + currentValues
                ValuesDialog2(list, list.lastIndex)
            } else {
                ValuesDialog2(values.toList(), currentIndex)
            }
        }

        var vl = 5

        fun singleChoiceAlertDialog() {

            val items = createValues(vl)
            val textItem = items.values
                .map { "value = $it" }
                .toTypedArray()

            val dialog2 = AlertDialog.Builder(requireContext())
                .setTitle("title")
                .setSingleChoiceItems(textItem, items.currentIndex) { dialog, witch ->
                    vl = items.values[witch]
                    dialog.dismiss()
                    binding.currentValue.text = vl.toString()
                }
                .create()
            dialog2.show()
        }

        binding.currentValue.text = vl.toString()
        binding.btDialogSingleChoice.setOnClickListener {
            singleChoiceAlertDialog()
        }
        return binding.root
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        val OPTIONS_KEY = "OPTIONS_KEY"

        @JvmStatic
        val SAVE_OPTIONS_KEY = "SAVE_OPTIONS_KEY"

        fun newInstance(options: Options, fragment: Fragment): Fragment {
            val args = Bundle()
            args.putParcelable(OPTIONS_KEY, options)
            fragment.arguments = args
            return fragment
        }
    }

    @Parcelize
    data class Options(
        val title: String,
        val color: Int
    ) : Parcelable


}