package ru.mirea.gupalodr.cryptoloader

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.loader.content.AsyncTaskLoader
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class MyLoader(context: Context, private val args: Bundle): AsyncTaskLoader<String>(context) {
    companion object {
        const val ARG_WORD = "word"
    }
    private val firstName: String? = args.getString(ARG_WORD)
    private val cipherText = args.getByteArray(ARG_WORD)
    private val key = args.getByteArray("key")

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
//        SystemClock.sleep(5000)
//        return firstName

        // Восстановление ключа
        val originalKey = SecretKeySpec(key, 0, key!!.size, "AES")
        // Дешифрование
        return decryptMsg(cipherText!!, originalKey)
    }

    private fun decryptMsg(cipherText: ByteArray, secret: SecretKey): String{
        try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, secret)
            return String(cipher.doFinal(cipherText))
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