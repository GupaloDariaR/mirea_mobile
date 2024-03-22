package ru.mirea.gupalodr.controle_lesson2

import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyProgressDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Progress Dialog")
        progressDialog.setMessage("Идет загрузка ..")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", DialogInterface.OnClickListener() { dialog, which ->
            (activity as MainActivity).showProgress()
            dialog.cancel()
        })

        return progressDialog
    }
}