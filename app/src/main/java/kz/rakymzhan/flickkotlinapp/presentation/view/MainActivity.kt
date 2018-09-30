package kz.rakymzhan.flickkotlinapp.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kz.rakymzhan.flickkotlinapp.R
import kz.rakymzhan.flickkotlinapp.data.network.`interface`.FlickrAPI
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.databinding.ActivityMainBinding
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.presentation.adapter.PhotoRecyclerViewAdapter
import kz.rakymzhan.flickkotlinapp.presentation.viewmodel.PhotoViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), PhotoRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val repositoryImpl: PhotoRepositoryImpl by inject()
    private val flickrApi: FlickrAPI by inject()
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoRecyclerViewAdapter: PhotoRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         photoViewModel = ViewModelProviders.of(this,
                PhotoViewModel.viewModelFactory { PhotoViewModel(repositoryImpl)})
                 .get(PhotoViewModel::class.java)

        photoRecyclerViewAdapter = PhotoRecyclerViewAdapter(photoViewModel.photoModelsList.value
                ?: arrayListOf(), this)
        photoViewModel.photoModelsList.observe(this,
                Observer<ArrayList<PhotoEntity>>{it?.let {
                    photoRecyclerViewAdapter.setData(it)
                }})


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = photoViewModel
        binding.executePendingBindings()

        binding.photoRV.layoutManager = LinearLayoutManager(this)
        binding.photoRV.adapter = photoRecyclerViewAdapter
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
