package kz.rakymzhan.flickkotlinapp.domain.usecase

import kz.rakymzhan.flickkotlinapp.domain.entity.UserSearchEntity

interface OnSearchDataReadyCallback {
    fun onSearchDataReadyCallback(data : List<UserSearchEntity>)
}