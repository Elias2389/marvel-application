package com.ae.marvelapplication.utils

import android.view.View

/**
 * Extension function to show any view
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Extension function to hide any view
 */
fun View.hide() {
    this.visibility = View.GONE
}