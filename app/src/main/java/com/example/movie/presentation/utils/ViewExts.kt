package com.example.movie.presentation.utils

import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso



fun ImageView.loadImage(url: String, placeHolder: Int = R.drawable.vikings) {
    Picasso.get().load(url).placeholder(placeHolder).into(this)
}


inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        var timer: CountDownTimer? = null
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            timer?.cancel()
            timer = object : CountDownTimer(1000, 200) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    if (!newText.isNullOrEmpty()) {
                        listener(newText)
                    }
                }
            }.start()
            return true
        }
    })
}

fun Fragment.showSnackBar(view: View, message: String) {
    Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_LONG
    ).setAction("ok") {
    }.show()
}


