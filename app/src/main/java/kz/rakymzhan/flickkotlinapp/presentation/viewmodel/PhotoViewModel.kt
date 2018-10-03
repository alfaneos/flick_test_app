package kz.rakymzhan.flickkotlinapp.presentation.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.databinding.ActivityMainBinding
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.domain.usecase.OnDataReadyCallback
import kz.rakymzhan.flickkotlinapp.presentation.adapter.PhotoRecyclerViewAdapter


class PhotoViewModel(activity: AppCompatActivity, private val repositoryImpl: PhotoRepositoryImpl, binding: ActivityMainBinding)
    : ViewModel(), OnDataReadyCallback, PhotoRecyclerViewAdapter.OnItemClickListener {

    var photoRecyclerViewAdapter: PhotoRecyclerViewAdapter
    var photoModelsList = MutableLiveData<List<PhotoEntity>>()
    val isLoading = ObservableField<Boolean>()


    val photoEntityObserver = Observer<List<PhotoEntity>> { t -> photoModelsList.postValue(t) }


    init {
        photoRecyclerViewAdapter = PhotoRecyclerViewAdapter(this.photoModelsList.value
                ?: listOf(), this)

        repositoryImpl.getLiveData().observe(activity, photoEntityObserver)
        binding.viewModel = this
        binding.photoRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.photoRV.adapter = photoRecyclerViewAdapter
        binding.executePendingBindings()

        repositoryImpl.getData(this)

        photoModelsList.observe(activity,
                Observer<List<PhotoEntity>>{it?.let {
                    photoRecyclerViewAdapter.setData(it)
                }})
    }

    override fun onDataReady(data: List<PhotoEntity>) {
        photoModelsList.postValue(data)
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

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}