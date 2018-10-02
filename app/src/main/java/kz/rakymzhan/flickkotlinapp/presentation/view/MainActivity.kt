package kz.rakymzhan.flickkotlinapp.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kz.rakymzhan.flickkotlinapp.R
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity
import kz.rakymzhan.flickkotlinapp.presentation.viewmodel.PhotoViewModel
import org.koin.android.ext.android.inject
import kz.rakymzhan.flickkotlinapp.databinding.ActivityMainBinding
import kz.rakymzhan.flickkotlinapp.presentation.adapter.PhotoRecyclerViewAdapter


class MainActivity : AppCompatActivity(), PhotoRecyclerViewAdapter.OnItemClickListener{

    private lateinit var binding: ActivityMainBinding

    private val repositoryImpl: PhotoRepositoryImpl by inject()
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photoRecyclerViewAdapter: PhotoRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         photoViewModel = ViewModelProviders.of(this,
                PhotoViewModel.viewModelFactory { PhotoViewModel(repositoryImpl)})
                 .get(PhotoViewModel::class.java)

        photoRecyclerViewAdapter = PhotoRecyclerViewAdapter(photoViewModel.photoModelsList.value
                ?: listOf(), this)

        photoViewModel.photoModelsList.observe(this,
                Observer<List<PhotoEntity>>{it?.let {
                    photoRecyclerViewAdapter.setData(it)
                }})

        repositoryImpl.getLiveData().observe(this, photoViewModel.photoEntityObserver)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = photoViewModel
        binding.executePendingBindings()
        binding.photoRV.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.photoRV.adapter = photoRecyclerViewAdapter
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}
