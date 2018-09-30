package kz.rakymzhan.flickkotlinapp.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import kz.rakymzhan.flickkotlinapp.data.repository.OnRepositoryReadyCallback
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity

class PhotoViewModel(private val repositoryImpl: PhotoRepositoryImpl) : ViewModel() {

    var photoModelsList = MutableLiveData<ArrayList<PhotoEntity>>()
    val isLoading = ObservableField(false)

    init {
        val onDataReadyCallback = object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<PhotoEntity>) {
                isLoading.set(false)
                photoModelsList.value = data
            }
        }
        repositoryImpl.getData(onDataReadyCallback)
    }

    companion object {
        inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
                }
    }

    fun refresh(){
        isLoading.set(true)
        repositoryImpl.refreshData(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<PhotoEntity>) {
                isLoading.set(false)
                photoModelsList.value = data
            }
        })
    }

}