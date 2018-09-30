package kz.rakymzhan.flickkotlinapp.presentation.model

import android.os.Handler

class PhotoModel {

    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
    }
}

interface OnDataReadyCallback {
    fun onDataReady(data : String)
}