package kz.rakymzhan.flickkotlinapp.presentation.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kz.rakymzhan.flickkotlinapp.R
import kz.rakymzhan.flickkotlinapp.data.repository.PhotoRepositoryImpl
import kz.rakymzhan.flickkotlinapp.databinding.ActivityMainBinding
import kz.rakymzhan.flickkotlinapp.presentation.viewmodel.PhotoViewModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private val repositoryImpl: PhotoRepositoryImpl by inject()
    private lateinit var photoViewModel: PhotoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        photoViewModel = PhotoViewModel(this, repositoryImpl, binding)
    }
}
