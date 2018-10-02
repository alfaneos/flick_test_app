package kz.rakymzhan.flickkotlinapp.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.domain.usecase.OnDataReadyCallback


class PhotoViewModel(private val repositoryImpl: PhotoRepositoryImpl) : ViewModel() , OnDataReadyCallback {

    var photoModelsList = MutableLiveData<List<PhotoEntity>>()
    val isLoading = ObservableField<Boolean>()

    val photoEntityObserver = object : Observer<List<PhotoEntity>>{
        override fun onChanged(t: List<PhotoEntity>?) {
            photoModelsList.postValue(t)
        }
    }


    init {
        repositoryImpl.getData(this)
    }

    override fun onDataReady(data: List<PhotoEntity>) {
        photoModelsList.postValue(data)
    }

    companion object {
        inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
                }
    }


    fun refresh(){
        isLoading.set(true)
        repositoryImpl.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: List<PhotoEntity>) {
                isLoading.set(false)
                photoModelsList.value = data
            }
        })
    }

}