package ir.misterdeveloper.aramkadeapplication.util

import android.content.Context
import android.widget.Toast


fun Context.errorToast(message: String, length: Int = Toast.LENGTH_LONG) {
    val toast = Toast.makeText(this, message, length)
    toast.show()
}