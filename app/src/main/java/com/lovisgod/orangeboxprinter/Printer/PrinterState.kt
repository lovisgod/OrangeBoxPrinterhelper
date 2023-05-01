package com.lovisgod.orangeboxprinter.Printer

interface PrinterState {
    fun onStart(status: String)
    fun onFinish(status: Int)
}