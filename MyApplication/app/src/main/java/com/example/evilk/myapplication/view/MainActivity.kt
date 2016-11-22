package com.example.evilk.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.evilk.myapplication.R
import com.example.evilk.myapplication.model.HeatingModel
import com.example.evilk.myapplication.presenters.MainPresenter
import com.example.evilk.myapplication.presenters.MainPresenterImpl
import com.example.evilk.myapplication.view.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.custom.style

class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter:MainPresenter
    var temperature_view: TextView? = null
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
            backgroundColor = Color.rgb(245,245,245)
            temperature_view = textView(){
                onClick{ refreshTemperatureValue()}
                textSize = 60f
                gravity = Gravity.CENTER
            }
            imageView(R.drawable.thermometer, 1){
                bottomPadding=dip(30)
                scaleX = 1.5F
                scaleY = 1.5F
            }.lparams{
                centerHorizontally()
                topOf(R.id.status_title)
            }

            textView{
                id = R.id.status_title
                text="Heating Status"
                bottomPadding=dip(15)
            }.lparams{
                centerHorizontally()
                topOf(R.id.bottom_layout_buttons)
            }

            linearLayout {
                id=R.id.bottom_layout_buttons
                gravity=Gravity.BOTTOM
                orientation = LinearLayout.HORIZONTAL

                radioGroup {
                    id = R.id.button_group
                    orientation = LinearLayout.HORIZONTAL

                    setOnCheckedChangeListener { radioGroup, i ->onGroupCheckedChange(radioGroup, i)}
                    toggleButton{
                        id=R.id.button_on
                        configureToggleButton(this, "ON")
                    }
                    toggleButton{
                        id = R.id.button_off
                        configureToggleButton(this, "OFF")
                    }
                    toggleButton{
                        id = R.id.button_auto
                        configureToggleButton(this, "AUTO")
                    }
                }
            }.lparams{
                alignParentBottom()
                centerHorizontally()
            }

        }
    }

    fun onGroupCheckedChange(radioGroup:RadioGroup, i: Int){
        for(j in 0..radioGroup.childCount-1){
            val view:ToggleButton = radioGroup.getChildAt(j) as ToggleButton
            val isChecked = view.id == i
            view.isChecked = isChecked
            val backgroundColor = if(isChecked)  Color.rgb(1,186,242) else
                Color.rgb(186,203,211)
            view.background.setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY)
        }
    }

    fun onToggle(button:ToggleButton){
        button.isChecked = true
        (button.parent as _RadioGroup).check(button.id)
    }

    fun refreshTemperatureValue(){
        presenter.refreshTemperatureValue()
    }

    fun configureToggleButton(button:ToggleButton, text:String){
        button.textColor = Color.WHITE
        button.background.setColorFilter(Color.rgb(186,203,211), PorterDuff.Mode.MULTIPLY)
        button.text=text
        button.textOn=text
        button.textOff=text
        button.onClick{onToggle(button)}

    }

    override fun updateTemperatureValue(newValue: Int) {
        temperature_view?.setText(newValue.toString() + " \u2103")
    }
}
