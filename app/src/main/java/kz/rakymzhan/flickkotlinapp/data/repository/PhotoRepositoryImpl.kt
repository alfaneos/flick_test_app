package kz.rakymzhan.flickkotlinapp.data.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.rakymzhan.flickkotlinapp.data.common.Configs
import kz.rakymzhan.flickkotlinapp.data.db.PhotoDatabase
import kz.rakymzhan.flickkotlinapp.data.network.client.PhotoNetworkClient
import kz.rakymzhan.flickkotlinapp.data.network.model.GalleryResponse
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.domain.usecase.OnDataReadyCallback

class PhotoRepositoryImpl(private val photoDatabase: PhotoDatabase) : PhotoRepository{

    init {
        getDataFromServer(null)
    }

    fun getData(onDataReadyCallback: OnDataReadyCallback){
        Observable.just(photoDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db -> onDataReadyCallback.onDataReady(photoDatabase.photoDAO().getAllPhotos().value ?: listOf())}
    }

    fun getLiveData() : LiveData<List<PhotoEntity>>{
        return photoDatabase.photoDAO().getAllPhotos()
    }


    @SuppressLint("CheckResult")
    fun getDataFromServer(onDataReadyCallback: OnDataReadyCallback?) {

        PhotoNetworkClient().getFlickApi().getGalleryPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    onDataReadyCallback?.onDataReady(result.photos.photo)
                    saveDataToLocalDB(result)
                },         { error ->
                    error.printStackTrace()
                })
    }

    fun saveDataToLocalDB(galleryResponse: GalleryResponse){
        Observable.just(photoDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db -> db.photoDAO().saveAll(galleryResponse.photos.photo) }
    }

    fun refreshData(onDataReadyCallback: OnDataReadyCallback){
        onDataReadyCallback.onDataReady(photoDatabase.photoDAO().getAllPhotos().value ?: listOf())
    }

}


interface PhotoRepository

