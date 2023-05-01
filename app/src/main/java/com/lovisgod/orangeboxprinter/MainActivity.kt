package com.lovisgod.orangeboxprinter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.isw.moneymaster.utility.PrinterUtil
import com.lovisgod.orangeboxprinter.Printer.PrinterHelper
import com.lovisgod.orangeboxprinter.Printer.PrinterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), PrinterState {
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.print_btn)


        btn.setOnClickListener {
            printerTest()
        }
    }

    private fun printerTest() = runBlocking {
        withContext(Dispatchers.IO) {
            val bitmap = PrinterUtil.generateBitmap()
            if (bitmap != null) {
                PrinterHelper.printBitmap(bitmap, this@MainActivity)
            } else {
                println("bitmap is null")
            }
        }

    }

    override fun onStart(status: String) {
        println(status.toString())
    }

    override fun onFinish(status: Int) {
        println("printing done ::::: status is ====== ${status}")
    }

}