package com.example.evilk.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService: Service() {
    override fun onBind(intent: Intent?): IBinder {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}