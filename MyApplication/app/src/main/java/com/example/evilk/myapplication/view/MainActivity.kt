package com.example.evilk.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.evilk.myapplication.R
import com.example.evilk.myapplication.model.HeatingModel
import com.example.evilk.myapplication.presenters.MainPresenter
import com.example.evilk.myapplication.presenters.MainPresenterImpl
import com.example.evilk.myapplication.view.MainView
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter:MainPresenter
    var temperature_view: TextView? = null
    var progressBar: ProgressBar? = null
    var batteryButton:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //MainActivityUI().setContentView(this)
        createLayout()
        var heatingModel = HeatingModel()
        presenter = MainPresenterImpl(heatingModel,this)
        presenter.refreshTemperatureValue()
    }

    fun createLayout(){
        relativeLayout{
            temperature_view = textView(){
                onClick{
                    refreshTemperatureValue()
                }
                textSize = 60f
                gravity = Gravity.CENTER
            }

            progressBar = progressBar{
                setVisibility(View.GONE)
                gravity = Gravity.CENTER
            }

            button("ON"){
                onClick{
                    changeBatteryCondition()
                }
                gravity = Gravity.BOTTOM
            }.lparams(){
                alignParentBottom()
                centerHorizontally()
            }
        }
    }

    fun refreshTemperatureValue(){
        temperature_view?.setText("");
        presenter.refreshTemperatureValue()
    }

    override fun updateTemperatureValue(newValue: Int) {
        temperature_view?.setText(newValue.toString() + " \u2103")
    }

    override fun showProgressBar(){
        progressBar?.setVisibility(View.VISIBLE);
    }

    override fun hideProgressBar(){
        progressBar?.setVisibility(View.GONE);
    }

    fun changeBatteryCondition(){
        var currentButtonState = batteryButton?.text.toString()
        presenter.changeBatteryCondition(currentButtonState)
    }

    override fun changeBatteryCondition(newValue: String){
        batteryButton?.setText(newValue)
    }
}
