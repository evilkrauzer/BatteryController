package com.example.evilk.myapplication.presenters

/**
 * Created by evilk on 02.11.2016.
 */
interface MainPresenter{
    fun refreshTemperatureValue()
    fun updateTemperatureValue(temperature:Int)
    fun changeBatteryCondition(currentState: String)
}