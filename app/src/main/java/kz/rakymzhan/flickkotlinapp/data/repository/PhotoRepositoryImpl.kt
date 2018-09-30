package kz.rakymzhan.flickkotlinapp.data.repository

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.rakymzhan.flickkotlinapp.data.common.Configs
import kz.rakymzhan.flickkotlinapp.data.db.PhotoDatabase
import kz.rakymzhan.flickkotlinapp.data.network.`interface`.FlickrAPI
import kz.rakymzhan.flickkotlinapp.data.network.model.GalleryResponse
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity

class PhotoRepositoryImpl(private val photoDatabase: PhotoDatabase) : PhotoRepository{

    init {
        getDataFromServer(null)
    }

    fun getData(onDataReadyCallback: OnRepositoryReadyCallback){
        onDataReadyCallback.onDataReady(photoDatabase.photoDAO().getAllPhotos())
    }


    @SuppressLint("CheckResult")
    fun getDataFromServer(onDataReadyCallback: OnRepositoryReadyCallback?) {
        FlickrAPI.create().getGalleryPhotos(api_key = Configs.API_KEY, method = Configs.FLICKR_API_METHOD,
                 gallery_id = Configs.GALLERY_ID, format = Configs.RESPONSE_FORMAT,
                 callback = Configs.CALLBACK)
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
        photoDatabase.photoDAO().saveAll(galleryResponse.photos.photo)
    }

    fun refreshData(onDataReadyCallback: OnRepositoryReadyCallback){
        onDataReadyCallback.onDataReady(photoDatabase.photoDAO().getAllPhotos())
    }

}


interface OnRepositoryReadyCallback {
    fun onDataReady(data : ArrayList<PhotoEntity>)
}

interface PhotoRepository

