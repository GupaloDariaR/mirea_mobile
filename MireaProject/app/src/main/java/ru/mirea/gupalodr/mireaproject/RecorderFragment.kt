package ru.mirea.gupalodr.mireaproject

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecorderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecorderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var TAG = MainActivity::class.java.simpleName
    private var isWork = false
    private lateinit var fileName : String
    private lateinit var recordButton : Button
    private lateinit var playButton : Button
    private lateinit var recorder: MediaRecorder
    private lateinit var player: MediaPlayer
    var isStartRecording = true
    var isStartPlaying = true
    private lateinit var recordFilePath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recorder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // инициализация кнопок
        recordButton = view.findViewById(R.id.recordButton)
        playButton = view.findViewById(R.id.playButton)
        playButton.isEnabled = false
        recordFilePath = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC),
            "/audiorecordtest.3gp").absolutePath

        // Проверка разрешений
        val audioRecordPermissionStatus = ContextCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.RECORD_AUDIO)
        val storagePermissionStatus = ContextCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(audioRecordPermissionStatus == PackageManager.PERMISSION_GRANTED
            &&  storagePermissionStatus == PackageManager.PERMISSION_GRANTED)
        {
            isWork = true
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION
            )
        }

        // функционал кнопок
        recordButton.setOnClickListener{
            if(isStartRecording){
                recordButton.text = "Завершить запись"
                playButton.isEnabled = false
                startRecording();
            } else {
                recordButton.text = "Начать запись"
                playButton.isEnabled = true
                stopRecording()
            }
            isStartRecording = !isStartRecording
        }

        playButton.setOnClickListener{
            if(isStartPlaying){
                playButton.text = "Остановить"
                recordButton.isEnabled = false
                startPlaying()
            } else{
                playButton.text = "Воспроизвести"
                recordButton.isEnabled = false
                stopPlaying()
            }
            isStartPlaying = !isStartPlaying
        }
    }

    private fun stopPlaying() {
        player.release()
    }

    private fun startPlaying() {
        player = MediaPlayer()
        try {
            player.setDataSource(recordFilePath)
            player.prepare()
            player.start()
        } catch (e: IOException) {
            Log.e(TAG, "prepare() failed")
        }
    }

    private fun stopRecording() {
        recorder.stop()
        recorder.release()
    }

    private fun startRecording() {
        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setOutputFile(recordFilePath)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        try {
            recorder.prepare()
        } catch (e: IOException) {
            Log.e(TAG, "prepare() failed")
        }
        recorder.start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode)
        {
            REQUEST_CODE_PERMISSION ->
                isWork = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (!isWork)
            requireActivity().finish()
    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 200

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecorderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecorderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}