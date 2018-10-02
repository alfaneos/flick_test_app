package kz.rakymzhan.flickkotlinapp.data.network.client

import io.reactivex.schedulers.Schedulers
import kz.rakymzhan.flickkotlinapp.data.common.Configs
import kz.rakymzhan.flickkotlinapp.data.network.`interface`.FlickrAPI
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class PhotoNetworkClient {

    fun getFlickApi() : FlickrAPI{
        val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        return Retrofit.Builder()
                .baseUrl(Configs.BASE_PHOTO_URL)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(FlickrAPI::class.java)
    }
}