package com.demo.newappdemo.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun snackBar(view: View?, context: Context?, message: String?) {
        if (message != null && view != null) {
            val imm =
                context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            val snackbar =
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            val snackTextView =
                snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView

            snackTextView.maxLines = 4
            snackbar.show()
        }
    }


    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun parseDate(selectedDate: String, currentFormat: String, requiredFormat: String): String {

        val dt = SimpleDateFormat(currentFormat, Locale.US)
        var day = ""
        try {
            val date = dt.parse(selectedDate)
            val dt1 = SimpleDateFormat(requiredFormat, Locale.getDefault())
            day = dt1.format(date)
        } catch (e: ParseException) {
            Timber.e(e)
        }
        return day
    }
}