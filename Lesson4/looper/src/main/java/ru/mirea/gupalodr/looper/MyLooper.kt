package ru.mirea.gupalodr.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


class MyLooper(private val mainThreadHandler: Handler) : Thread() {
    lateinit var mHandler: Handler

    override fun run() {
        Log.d("MyLooper", "run")
        Looper.prepare()
        mHandler = object: Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                val data = msg.data.getString("KEY")
                val age = msg.data.getInt("AGE")
                val work = msg.data.getString("WORK")
                Log.d("MyLooper get message:", data ?: "")

                val count = data?.length
                val message = Message()
                val bundle = Bundle()
                bundle.putString("result",
                    String.format("The number of letters in the word %s is %d \n" +
                            "age: %d, work: %s",data,count,age,work)
                )

                message.data = bundle
                mainThreadHandler.sendMessage(message)
                mainThreadHandler.postDelayed({mainThreadHandler.sendMessage(message)},
                    (age * 100).toLong()
                )
            }
        }
        Looper.loop()
    }
}