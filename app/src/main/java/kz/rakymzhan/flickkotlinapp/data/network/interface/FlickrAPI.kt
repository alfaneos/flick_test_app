package kz.rakymzhan.flickkotlinapp.data.network.`interface`

import io.reactivex.Observable
import kz.rakymzhan.flickkotlinapp.data.common.Configs
import kz.rakymzhan.flickkotlinapp.data.network.model.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrAPI {

    @GET("services/rest")
    fun getGalleryPhotos(@Query("method") method: String = Configs.REQUEST_POPULAR,
               @Query("api_key") api_key: String = Configs.API_KEY,
               @Query("gallery_id") gallery_id: String = Configs.GALLERY_ID,
               @Query("nojsoncallback") callback: Int = Configs.CALLBACK,
               @Query("format") format: String = Configs.RESPONSE_FORMAT): Observable<GalleryResponse>

    @GET("services/rest")
    fun getPhotosBySearch(@Query("method") method: String = Configs.REQUEST_SEARCH,
                      @Query("api_key") api_ket: String = Configs.API_KEY,
                      @Query("text") text: String,
                      @Query("format") format: String = Configs.RESPONSE_FORMAT,
                      @Query("nojsoncallback") callback: Int = Configs.CALLBACK): Observable<GalleryResponse>
}