package com.lovisgod.orangeboxprinter.Printer

import android.content.Context
import android.graphics.Bitmap
import com.pos.device.SDKManager
import com.pos.device.printer.PrintTask
import com.pos.device.printer.Printer
import com.pos.device.printer.PrinterCallback

object PrinterHelper {
    val printer  = Printer.getInstance()
    fun printBitmap(bitmap: Bitmap, printerState: PrinterState) {
        if (printer != null) {
            val printerTask = PrintTask()
            printerTask.setPrintBitmap(bitmap)
            printerState.onStart("printing started")
            printer.startPrint(printerTask, OrangeBoxPrinterCallback(printerState, printer))
        }
    }



     class OrangeBoxPrinterCallback(val printerState: PrinterState, val printer: Printer): PrinterCallback {
        override fun onResult(p0: Int, p1: PrintTask?) {
            printerState.onFinish(p0)
            printer.reset()
        }

    }
}