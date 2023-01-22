package com.android.erwin.challenge4gamesuit.model

import android.widget.ImageView

abstract class User {

    lateinit var name : String
    var player : Int = -1
    lateinit var choice : ArrayList<ImageView>
}