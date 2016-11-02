package com.example.evilk.myapplication.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.example.evilk.myapplication.MainActivity
import org.jetbrains.anko.*
import com.example.evilk.myapplication.R
import com.example.evilk.myapplication.R.*

class MainActivityUI: AnkoComponent<MainActivity> {
    override  fun createView(ui: AnkoContext<MainActivity>) = with(ui){
        verticalLayout{
            button("Refresh"){
                onClick{

                }
            }
        }
    }
}