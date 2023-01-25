package com.cimot.newson.utils

import android.os.Build
import android.text.Html
import android.widget.TextView

object Extension {
    fun TextView.textFromHtml(htmlString:String?){
        text= if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT)
        }else{
            Html.fromHtml(htmlString)
        }
    }
}