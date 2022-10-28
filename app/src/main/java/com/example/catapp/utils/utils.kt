package com.example.catapp.utils

import android.app.Activity
import android.widget.Toast

fun Activity.shortToast(string: String){
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Activity.longToast(string: String){
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}
