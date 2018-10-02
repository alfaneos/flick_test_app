package kz.rakymzhan.flickkotlinapp.data.network.`interface`

import io.reactivex.Observable
import kz.rakymzhan.flickkotlinapp.data.network.model.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrAPI {

    @GET("services/rest")
    fun getGalleryPhotos(@Query("method") method: String,
               @Query("api_key") api_key: String,
               @Query("gallery_id") gallery_id: String,
               @Query("nojsoncallback") callback: Int,
               @Query("format") format: String): Observable<GalleryResponse>

}