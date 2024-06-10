package ru.mirea.gupalodr.cryptoloader

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import ru.mirea.gupalodr.cryptoloader.databinding.ActivityMainBinding
import java.security.InvalidKeyException
import java.security.InvalidParameterException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    private lateinit var binding: ActivityMainBinding
    val TAG = this.javaClass.simpleName
    private val LoaderID = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(MyLoader.ARG_WORD, "mirea")
            LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this)
        }

        binding.button.setOnClickListener {
            val text = binding.editText.text.toString()
            val key = generateKey()
            val cipherText = encryptMsg(text, key!!)

            val bundle = Bundle()
            bundle.putByteArray(MyLoader.ARG_WORD, cipherText)
            bundle.putByteArray("key", key!!.encoded)
            LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this)
        }
    }

    override fun onLoaderReset(loader: Loader<String>) {
        Log.d(TAG, "onLoaderReset")
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        if (id == LoaderID) {
            Toast.makeText(this, "onCreateLoader: $id", Toast.LENGTH_SHORT).show()
            return MyLoader(this, args!!)
        }
        throw  InvalidParameterException("Invalid loader id")
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        if (loader.id == LoaderID) {
            Log.d(TAG, "onLoadFinished: $data")
            Toast.makeText(this, "onLoadFinished: $data", Toast.LENGTH_SHORT).show()
        }
    }

    fun generateKey(): SecretKey? {
        try {
            val sr = SecureRandom.getInstance("SHA1PRNG")
            sr.setSeed("any data used as random seed".toByteArray())
            val kg = KeyGenerator.getInstance("AES")
            kg.init(256, sr)
            return SecretKeySpec((kg.generateKey()).encoded, "AES")
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun encryptMsg(message: String, secret: SecretKey): ByteArray {
        try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, secret)
            return cipher.doFinal(message.toByteArray())
        } catch (e: NoSuchAlgorithmException) {
            throw java.lang.RuntimeException(e)
        } catch (e: NoSuchPaddingException) {
            throw java.lang.RuntimeException(e)
        } catch (e: InvalidKeyException) {
            throw java.lang.RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw java.lang.RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw java.lang.RuntimeException(e)
        }
    }
}