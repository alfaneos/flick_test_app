package kz.rakymzhan.flickkotlinapp.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.lapism.searchview.Search
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.databinding.ActivityMainBinding
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.domain.entity.UserSearchEntity
import kz.rakymzhan.flickkotlinapp.domain.usecase.OnDataReadyCallback
import kz.rakymzhan.flickkotlinapp.domain.usecase.OnSearchDataReadyCallback
import kz.rakymzhan.flickkotlinapp.presentation.adapter.PhotoRecyclerViewAdapter
import kz.rakymzhan.flickkotlinapp.presentation.adapter.SearchAdapter
import kz.rakymzhan.flickkotlinapp.presentation.view.PhotoFragment

class PhotoViewModel(val activity: AppCompatActivity, private val repositoryImpl: PhotoRepositoryImpl, var binding: ActivityMainBinding)
    : ViewModel(), OnDataReadyCallback, OnSearchDataReadyCallback, PhotoRecyclerViewAdapter.OnItemClickListener
    , SearchAdapter.OnItemClickListener, Search.OnQueryTextListener, PhotoFragment.OnFragmentInteractionListener {

    var photoRecyclerViewAdapter: PhotoRecyclerViewAdapter
    var userSearchAdapter: SearchAdapter
    var photoModelsList = MutableLiveData<List<PhotoEntity>>()
    val isLoading = ObservableField<Boolean>()
    var searchItemsList = MutableLiveData<List<UserSearchEntity>>()

    val photoEntityObserver = Observer<List<PhotoEntity>> { t -> photoModelsList.postValue(t) }
    val userEntityObserver = Observer<List<UserSearchEntity>> { t -> searchItemsList.postValue(t) }


    init {
        photoRecyclerViewAdapter = PhotoRecyclerViewAdapter(this.photoModelsList.value
                ?: listOf(), this)

        userSearchAdapter = SearchAdapter(this.searchItemsList.value?: listOf(), this)

        repositoryImpl.getLiveData().observe(activity, photoEntityObserver)
        repositoryImpl.getLastSearches().observe(activity, userEntityObserver)
        binding.viewModel = this
        binding.photoRV.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.photoRV.adapter = photoRecyclerViewAdapter

        binding.searchView.adapter = userSearchAdapter
        binding.searchView.setOnQueryTextListener(this)
        binding.executePendingBindings()

        repositoryImpl.getPhotoData(this)
        repositoryImpl.getSearchData(this)

        photoModelsList.observe(activity,
                Observer<List<PhotoEntity>>{it?.let {
                    photoRecyclerViewAdapter.setData(it)
                }})
        searchItemsList.observe(activity,
                Observer<List<UserSearchEntity>>{it?.let {
                    userSearchAdapter.setData(it)
                }})
        binding.searchView.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) binding.photoRV.visibility = View.GONE
                else binding.photoRV.visibility = View.VISIBLE
        }
        binding.searchView.setOnOpenCloseListener(object: Search.OnOpenCloseListener{
            override fun onOpen() {
                binding.photoRV.visibility = View.GONE
            }

            override fun onClose() {
                binding.photoRV.visibility = View.VISIBLE
            }

        })

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


    override fun onItemClick(entity: PhotoEntity) {
        entity.parseUrl()?.let {url ->
            val fragmentManafer = activity.supportFragmentManager
            val fragmentTransaction = fragmentManafer.beginTransaction()
            fragmentTransaction.replace(binding.frame.id, PhotoFragment.newInstance(url))
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onSearchDataReadyCallback(data: List<UserSearchEntity>) {
        searchItemsList.value = data
    }

    override fun onSearchClicked(position: Int) {
        searchItemsList.value?.get(position)?.let { repositoryImpl.saveUseSearch(it, this) }
    }

    override fun onQueryTextSubmit(query: CharSequence?): Boolean {
        query?.let {
            repositoryImpl.saveUseSearch(UserSearchEntity(id = null, text = it.toString()), this)
            return true
        } ?: return false
    }

    override fun onQueryTextChange(newText: CharSequence?) {
        Log.d("text changed to", newText.toString())
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}