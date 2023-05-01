package com.isw.moneymaster.utility

import android.graphics.Bitmap

import android.graphics.Matrix

import com.lovisgod.orangeboxprinter.Printer.PrinterTestUtils.CombBitmap
import com.lovisgod.orangeboxprinter.Printer.PrinterTestUtils.GenerateBitmap

object PrinterUtil {

    /**
     * @param bitmap the Bitmap to be scaled
     * @param threshold the maxium dimension (either width or height) of the scaled bitmap
     * @param isNecessaryToKeepOrig is it necessary to keep the original bitmap? If not recycle the original bitmap to prevent memory leak.
     */
    fun getScaledDownBitmap(bitmap: Bitmap, threshold: Int = 150, isNecessaryToKeepOrig: Boolean = false): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        var newWidth = width
        var newHeight = height
        if (width > height && width > threshold) {
            newWidth = threshold
            newHeight = (height * newWidth.toFloat() / width).toInt()
        }
        if (width in (height + 1)..threshold) {
            //the bitmap is already smaller than our required dimension, no need to resize it
            return bitmap
        }
        if (width < height && height > threshold) {
            newHeight = threshold
            newWidth = (width * newHeight.toFloat() / height).toInt()
        }
        if (height in (width + 1)..threshold) {
            //the bitmap is already smaller than our required dimension, no need to resize it
            return bitmap
        }
        if (width == height && width > threshold) {
            newWidth = threshold
            newHeight = newWidth
        }
        return if (width == height && width <= threshold) {
            //the bitmap is already smaller than our required dimension, no need to resize it
            bitmap
        } else getResizedBitmap(bitmap, newWidth, newHeight, isNecessaryToKeepOrig)
    }

    private fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int, isNecessaryToKeepOrig: Boolean): Bitmap {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)
        // "RECREATE" THE NEW BITMAP
        val resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
        if (!isNecessaryToKeepOrig) {
            bm.recycle()
        }
        return resizedBitmap
    }



    internal object StringUtils {

        @JvmOverloads
        fun center(s: String?, size: Int, pad: Char = ' ', newLine: Boolean = false): String? {
            if (s == null || size <= s.length)
                return s

            val sb = StringBuilder(size)
            for (i in 0 until (size - s.length) / 2) {
                sb.append(pad)
            }
            sb.append(s)
            while (sb.length < size) {
                sb.append(pad)
            }

            if (newLine) sb.append("\n")
            return sb.toString()
        }
    }

    // screen character length
      val SCREEN_LARGE_LENGTH = 24
      val SCREEN_NORMAL_LENGTH = 32

    var LARGE_FONT = 25
    var NORMAL_FONT = 18

    fun getTransStatus(status: String) : String {
        return if (status == "00") "Successful" else "Failed"
    }

     fun generateBitmap(): Bitmap? {

         val combBitmap = CombBitmap()


         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 getTransStatus("00"),
                 36,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )
         combBitmap.addBitmap(GenerateBitmap.generateGap(20))

         combBitmap.addBitmap(GenerateBitmap.generateLine(2))

         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "#500.00",
                 40,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )

         combBitmap.addBitmap(GenerateBitmap.generateLine(2))
         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "Terminal ID:",
                 "22444556", 18, true, false
             )
         )

         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "Merchant ID:",
                 "sample merchant",
                 18,
                 true,
                 false
             )
         )

         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "Transaction Type",
                 "Cash-out",
                 18,
                 true,
                 false
             )
         )

         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "-----------------------------------------",
                 20,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )

         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "this is description",
                 40,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )

         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "-----------------------------------------",
                 20,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )
         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "Entry Mode:::::: CONTACT",
                 20,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )
         combBitmap.addBitmap(
             GenerateBitmap.str2Bitmap(
                 "-----------------------------------------",
                 20,
                 GenerateBitmap.AlignEnum.CENTER,
                 true,
                 false
             )
         )

         return combBitmap.combBitmap

     }

}