package ru.mirea.gupalodr.camera

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import ru.mirea.gupalodr.camera.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_CODE_PERMISSION = 100
        const val CAMERA_REQUEST = 0
    }
    private var isWork = false
    private var imageUri: Uri? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ДОБАВИТЬ ПРОВЕРКУ НА НАЛИЧИЕ РАЗРЕШЕНИЙ
        // НА ИСПОЛЬЗОВАНИЕ КАМЕРЫ И ЗАПИСИ В ПАМЯТЬ
        val cameraPermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        val storagePermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus  == PackageManager.PERMISSION_GRANTED) {
            isWork = true
        } else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
        }

        // Создание функции обработки результата от системного приложения «камера»
        val callback = ActivityResultCallback<ActivityResult>{ result ->
            if(result.resultCode == Activity.RESULT_OK){
                val data = result.data
                Log.d("KKK", "$imageUri")
//                var uri = data?.getStringExtra(MediaStore.EXTRA_OUTPUT)
//                Log.d("KKK", "imageUri = $uri")
//                binding.imageView.setImageURI(uri?.toUri())
                binding.imageView.setImageURI(imageUri)
            }
        }

        val cameraActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            callback
        )

        // Обработчик нажатия на компонент «imageView»
        binding.imageView.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // проверка на наличие разрешений для камеры
            if (isWork) {
                try {
                    val photoFile = createImageFile()
                    // генерирование пути к файлу на основе authorities
                    val authorities = applicationContext.packageName + ".fileprovider"
                    imageUri = FileProvider.getUriForFile(this@MainActivity, authorities, photoFile)
                    Log.d("KKK", imageUri.toString())
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri.toString())
                    cameraActivityResultLauncher.launch(cameraIntent)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val imageFileName = "IMAGE_" + timeStamp + "_"
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDirectory)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        // производится проверка полученного результата от пользователя на запрос разрешения Camera
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        }
    }
}