package com.example.evilk.myapplication.view

/**
 * Created by evilk on 02.11.2016.
 */

interface  MainView{
    fun updateTemperatureValue(newValue: Int)
    fun hideProgressBar()
    fun showProgressBar()
    fun changeBatteryCondition(newValue: String)
}