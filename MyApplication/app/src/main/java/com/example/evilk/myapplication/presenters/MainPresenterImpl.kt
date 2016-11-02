package com.example.evilk.myapplication.presenters

import com.example.evilk.myapplication.model.HeatingModel
import com.example.evilk.myapplication.view.MainView

/**
 * Created by evilk on 02.11.2016.
 */
class MainPresenterImpl(val heatingModel:HeatingModel, val mainView:MainView): MainPresenter{

    override fun refreshTemperatureValue() {
        mainView.showProgressBar()
        var temperature = heatingModel.GetCurrentTemperatureValue(this)
    }

    override fun updateTemperatureValue(temperature:Int) {
        mainView.updateTemperatureValue(temperature)
        mainView.hideProgressBar()
    }

    override fun changeBatteryCondition(currentState:String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}