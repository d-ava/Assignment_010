package com.example.assignment_010.extensions

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(txt: String){
    Snackbar.make(this, txt, Snackbar.LENGTH_SHORT).show()
}

fun View.showToast(txt: String){
    Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()
}