package ru.mirea.gupalodr.sharer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

class ShareActivity2 : AppCompatActivity() {
    private val TAG = "ShareTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share2)

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "*/*"

        val callback = ActivityResultCallback<ActivityResult> {
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                Log.d(TAG, "Data:" + data!!.dataString)
            }
        }
        val imageActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            callback
        )
        imageActivityResultLauncher.launch(intent)
    }
}