package com.example.evilk.myapplication.model

import com.example.evilk.myapplication.presenters.MainPresenter
import java.util.*
import android.os.Handler
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by evilk on 02.11.2016.
 */

class HeatingModel{
    fun GetCurrentTemperatureValue(presenterCallback:MainPresenter){
        Handler().postDelayed({
            var rnd = Random();
            var rangeMin = 0
            var rangeMax = 40
            var newValue = rangeMin + (rangeMax - rangeMin) * rnd.nextInt(rangeMax - rangeMin)+rangeMin
            presenterCallback.updateTemperatureValue(newValue)
        }, 2000)

//        doAsync {
//            val url = URL("http://jsonplaceholder.typicode.com/posts/1")
//            val json = url.readText()
//            val gson = Gson()
//            val person = gson.fromJson<Person>(json)
//            uiThread{
//                presenterCallback.updateTemperatureValue()
//            }
 //       }
    }
}