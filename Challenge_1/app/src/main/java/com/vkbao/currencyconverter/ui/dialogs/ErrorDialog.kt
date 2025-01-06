package com.vkbao.currencyconverter.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vkbao.currencyconverter.databinding.DialogErrorBinding

class ErrorDialog : DialogFragment() {
    private var binding: DialogErrorBinding? = null
    private var _message = ""
    var message get() = _message
        set(value) {
            _message = value
            binding?.description?.text = value
        }
    private var _onButtonClickListener: View.OnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogErrorBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = DialogErrorBinding.inflate(requireActivity().layoutInflater, null, false)

        return activity.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogErrorBinding.inflate(it!!.layoutInflater, null, false)
            builder.setView(binding!!.root)

            binding!!.description.text = _message

            binding!!.okBtn.setOnClickListener {l ->
                _onButtonClickListener?.onClick(l)
                dismiss()
            }

            builder.create()
        }
    }
}