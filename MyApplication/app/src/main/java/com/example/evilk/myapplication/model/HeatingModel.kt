package com.example.evilk.myapplication.model

import com.example.evilk.myapplication.presenters.MainPresenter
import java.util.*
import android.os.Handler
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by evilk on 02.11.2016.
 */

class HeatingAction{
    var action:String? = null
}

class  HeatingActionResult{
    var result:String? = null
}

class HeatingModel{
    var gson = Gson()

    fun GetCurrentTemperatureValue(presenterCallback:MainPresenter){
        var action = HeatingAction()
        action.action = "GetTemperature"
        var content = gson.toJson(action)
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")
        Fuel.post("http://smart.nazaryev.ru").body(content).responseString{request, response, result->
            when(result){
                is Result.Failure->{
                }
                is Result.Success->{
                    onActionSuccess(result.get(), presenterCallback)
                }
            }
        }
    }

    fun onActionSuccess(result:String, presenterCallback:MainPresenter){
        var data = gson.fromJson<HeatingActionResult>(result)
        var temperature = data.result?.toInt()?:-1
        presenterCallback.updateTemperatureValue(temperature)
    }
}