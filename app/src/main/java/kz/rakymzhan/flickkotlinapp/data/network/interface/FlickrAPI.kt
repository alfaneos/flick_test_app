package kz.rakymzhan.flickkotlinapp.data.network.`interface`

import io.reactivex.Observable
import kz.rakymzhan.flickkotlinapp.data.network.model.GalleryResponse
import kz.rakymzhan.flickkotlinapp.presentation.model.PhotoModel
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrAPI {

    @GET("services/rest")
    fun getGalleryPhotos(@Query("method") method: String,
               @Query("api_key") api_key: String,
               @Query("gallery_id") gallery_id: String,
               @Query("nojsoncallback") callback: Int,
               @Query("format") format: String): Observable<GalleryResponse>


    companion object Factory {
        fun create(): FlickrAPI {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.flickr.com/")
                    .build()

            return retrofit.create(FlickrAPI::class.java)
        }
    }
}