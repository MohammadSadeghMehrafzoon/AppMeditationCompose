package ir.misterdeveloper.aramkadeapplication.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

    Log.v("error", "Error->" + throwable.message)

}
