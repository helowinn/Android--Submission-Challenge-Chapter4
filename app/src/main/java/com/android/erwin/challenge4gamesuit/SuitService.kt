package com.android.erwin.challenge4gamesuit

enum class SuitService (val index : Int) {
    BATU(0),
    KERTAS(1),
    GUNTING(2);

    companion object {
        fun getValueFromIndex(index: Int) = values()[index]
    }
}