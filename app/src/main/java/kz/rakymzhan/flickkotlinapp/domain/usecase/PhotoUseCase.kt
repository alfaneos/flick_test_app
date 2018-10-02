package kz.rakymzhan.flickkotlinapp.domain.usecase

import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity


interface OnDataReadyCallback {
    fun onDataReady(data : List<PhotoEntity>)
}