package com.lovisgod.orangeboxprinter

import android.app.Application
import com.pos.device.SDKManager
import com.pos.device.SDKManagerCallback

class OrangeBoxApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeOrangeBox()
    }

    fun initializeOrangeBox() {
       try {
           SDKManager.init(this.applicationContext, OrangeBoxCallBackManager())
       } catch (e: Exception) {
           println("wahala ti sele")
           println("error is :::::: ${e.message.toString()}")
       }
    }


    class OrangeBoxCallBackManager: SDKManagerCallback {
        override fun onFinish() {
            println("sdk init is finished")
        }

    }
}