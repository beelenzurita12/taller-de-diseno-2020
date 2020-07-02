package com.example.taller_de_diseno_2020.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.taller_de_diseno_2020.R

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
fun notImplementedButton(context: Context){
    Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show()
}