package com.herald.watchwise.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import kotlin.math.pow

/**
 * This object holds helper methods that are commonly used.
 * the first one returns an approximation to the first nearest decimal point.
 * the second one opens the youtube trailer video url.
 */
object HelperMethods {


    fun roundToDecimalPlaces(value: Float,place:Int):Float{

        return (((value+(0.5/10.0.pow(place)))*10.0.pow(place)).toInt())/10.0.pow(place).toFloat()
    }

    fun openYoutubeLink(context: Context, youtubeID: String) {
        if (youtubeID.isBlank()){
            Toast.makeText(context, "No trailer was found", Toast.LENGTH_SHORT).show()
        }else{
            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.youtube.com/watch?v=$youtubeID"))
            context.startActivity(intentApp)
        }
    }
}